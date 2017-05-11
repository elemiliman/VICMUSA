package org.vicmusa.church.services;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.vicmusa.church.dto.ForgotPasswordForm;
import org.vicmusa.church.dto.ResetPasswordForm;
import org.vicmusa.church.dto.SignupForm;
import org.vicmusa.church.entities.UserAddress;
import org.vicmusa.church.entities.UserNotification;
import org.vicmusa.church.entities.User;
import org.vicmusa.church.entities.User.Role;
import org.vicmusa.church.mail.MailSender;
import org.vicmusa.church.repositories.UserAddressRepository;
import org.vicmusa.church.repositories.UserRepository;
import org.vicmusa.church.utilities.MyUtility;
import org.vicmusa.church.utilities.S3FileUploader;
import org.vicmusa.church.dto.UserDetailsImpl;
import org.vicmusa.church.dto.UserEditAccountForm;
import org.vicmusa.church.dto.UserEditNotificationForm;
import org.vicmusa.church.dto.UserEditProfileForm;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UserServiceImpl implements UserService, UserDetailsService {

	private static final Log log = LogFactory.getLog(UserServiceImpl.class);
	
	private UserRepository userRepository;
	private UserAddressRepository userAddressRepository;
	private PasswordEncoder passwordEncoder;
	private MailSender mailSender;
	private S3FileUploader s3fileUploader;
	
	/**
	 * Constructor based injection
	 */
	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserAddressRepository userAddressRepository, 
			PasswordEncoder passwordEncoder, MailSender mailSender, S3FileUploader s3fileUploader) {
		this.mailSender = mailSender;
		this.s3fileUploader = s3fileUploader;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userAddressRepository = userAddressRepository;
	}
	
	/**
	 * Saves new user info to the database and email's the new user a verification URL to very email
	 * Roles back database operation if an exception occurs during saving the user.
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void signup(SignupForm signupForm) {
		User user = new User();
		user.setEmail(signupForm.getEmail());
		user.setUserName(signupForm.getUserName());
		user.setFirstName(signupForm.getFirstName());
		user.setLastName(signupForm.getLastName());
		user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
		user.getRoles().add(Role.UNVERIFIED);
		user.setVerificationCode(RandomStringUtils.randomAlphanumeric(User.RANDOM_CODE_LENGTH));
		userRepository.save(user);
		
		// This section of the code only executes when the new user has been successfully save to the database
		TransactionSynchronizationManager.registerSynchronization(
			    new TransactionSynchronizationAdapter() {
			        @Override
			        public void afterCommit() {
			    		try {
			    			String verifyLink = MyUtility.hostUrl() + "/users/" + user.getVerificationCode() + "/verify";
			    			String vicmUrl = MyUtility.hostUrl();
			    			String name = user.getFirstName()+ " " + user.getLastName();
			    			mailSender.send(user.getEmail(), MyUtility.getMessage("verifySubject"), MyUtility.getMessage("verifyEmail", name, vicmUrl, verifyLink));
			    			log.info("Verification mail to " + user.getEmail() + " queued.");
						} catch (MessagingException e) {
							log.error(ExceptionUtils.getStackTrace(e));
						}
			        }
		    });
	}
	
	/**
	 * Checks if user exist else it throws an error
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if(user == null){
			throw new UsernameNotFoundException(username);
		}
		return new UserDetailsImpl(user);
	}
	
	/**
	 * Verifies new user email by extracting the verification code from the URL sent to the new user email.
	 * Compares verification from the URL with that one stored in the database as at the time to code was sent.
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void verify(String verificationCode) {
		String loggedInUserId = MyUtility.getSessionUser().getUserName();
		User user = userRepository.findByUserName(loggedInUserId);
		
		MyUtility.validate(user.getRoles().contains(Role.UNVERIFIED), "alreadyVerified");
		MyUtility.validate(user.getVerificationCode().equals(verificationCode), "incorrect", "verification code");
		
		user.getRoles().remove(Role.UNVERIFIED);
		user.setVerificationCode(null);
		userRepository.save(user);
	}
	
	/**
	 * Re-sends verification code to new user email
	 */
	public void resendVerificationCode(){
		String loggedInUserId = MyUtility.getSessionUser().getUserName();
		User user = userRepository.findByUserName(loggedInUserId);
		try {
			String verifyLink = MyUtility.hostUrl() + "/users/" + user.getVerificationCode() + "/verify";
			String name = user.getFirstName()+ "" + user.getLastName();
			String vicmUrl = MyUtility.hostUrl();
			mailSender.send(user.getEmail(), MyUtility.getMessage("verifySubject"), MyUtility.getMessage("verifyEmail", name, vicmUrl, verifyLink));
			log.info("Verification mail to " + user.getEmail() + " queued.");
		} catch (MessagingException e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
	/**
	 * Get the email id of the user that have forgotten their password from the forgotPassword JSP form
	 * Uses the email id to find the user in the database
	 * From the user object we set a forgot password code(16 characters long alphanumeric random string)
	 * Save the user back to the database
	 * Send the user the forgot password code embedded in a URL just like the verification code
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void forgotPassword(ForgotPasswordForm form) {
		
		final User user = userRepository.findByEmail(form.getEmail());
		final String forgotPasswordCode = RandomStringUtils.randomAlphanumeric(User.RANDOM_CODE_LENGTH);
		
		user.setForgotPasswordCode(forgotPasswordCode);
		final User savedUser = userRepository.save(user);
		
		TransactionSynchronizationManager.registerSynchronization(
			    new TransactionSynchronizationAdapter() {
			        @Override
			        public void afterCommit() {
			        	try {
							mailForgotPasswordLink(savedUser);
						} catch (MessagingException e) {
							log.error(ExceptionUtils.getStackTrace(e));
						}
			        }

		    });				

	}

	/**
	 * Sends forgot password code to user email
	 */
	private void mailForgotPasswordLink(User user) throws MessagingException {
		
		String forgotPasswordLink =  MyUtility.hostUrl() + "/reset-password/" + user.getForgotPasswordCode();
		String name = user.getFirstName()+ "" + user.getLastName();
		mailSender.send(user.getEmail(), MyUtility.getMessage("forgotPasswordSubject"),
				MyUtility.getMessage("forgotPasswordEmail", name, forgotPasswordLink));
	}
	
	/**
	 * Finds the forgot password code stored in the user filed
	 * Uses the forget password code to identify and reset user password
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void resetPassword(String forgotPasswordCode,
			ResetPasswordForm resetPasswordForm,
			BindingResult result) {
		
		User user = userRepository.findByForgotPasswordCode(forgotPasswordCode);
		if (user == null)
			result.reject("invalidForgotPassword");
		
		if (result.hasErrors())
			return;
		
		user.setForgotPasswordCode(null);
		user.setPassword(passwordEncoder.encode(resetPasswordForm.getPassword().trim()));
		userRepository.save(user);
	}
	
	/**
	 * Obtains the logged in userid from the session and compares it with the userid
	 * passed from the path variable in the UserController
	 * Checks if user is not loggedin and if user is not admin so as to hide user email id
	 */
	@Override
	public User findOneUser(String userName) {
		User loggedIn = MyUtility.getSessionUser();
		User user = userRepository.findByUserName(userName);
		if(loggedIn == null || loggedIn.getUserId() != user.getUserId() && !loggedIn.isAdmin()){
			user.setEmail("Confidential");
		}
		return user;
	}
	
	/**
	 * Updates user profile from the info gotten from the userController Profile Edit Form fields
	 * by getting the user id from the user loggedin session and compare it with the userid
	 * passed from the path variable
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void updateProfile(String userName, UserEditProfileForm userEditProfileForm, MultipartFile multipartFile) {
		
		User loggedIn = MyUtility.getSessionUser();
		MyUtility.validate(loggedIn.isAdmin() || loggedIn.getUserName().equals(userName), "noPermissions");
		User user = userRepository.findByUserName(userName);
		UserAddress userAddress = new UserAddress();
		String profilePicture = null;
		String bucketName = "vicmutil";
		
		user.setFirstName(userEditProfileForm.getFirstName());
		user.setLastName(userEditProfileForm.getLastName());
		
		if (user.getUserAddress() != null) {
			user.getUserAddress().setGender(userEditProfileForm.getGender());
			user.getUserAddress().setGender(userEditProfileForm.getGender());
			user.getUserAddress().setDob(userEditProfileForm.getDob());
			user.getUserAddress().setAddressLineOne(userEditProfileForm.getAddressLineOne());
			user.getUserAddress().setAddressLineTwo(userEditProfileForm.getAddressLineTwo());
			user.getUserAddress().setCity(userEditProfileForm.getCity());
			user.getUserAddress().setState(userEditProfileForm.getState());
			user.getUserAddress().setZipCode(userEditProfileForm.getZipCode());
			user.getUserAddress().setCountry(userEditProfileForm.getCountry());
			
			if (!multipartFile.isEmpty()) {
				profilePicture = s3fileUploader.uploadSingleFile(multipartFile, bucketName);
				user.getUserAddress().setProfilePicture(profilePicture);
			}
		}else{
			userAddress.setGender(userEditProfileForm.getGender());
			userAddress.setGender(userEditProfileForm.getGender());
			userAddress.setDob(userEditProfileForm.getDob());
			userAddress.setAddressLineOne(userEditProfileForm.getAddressLineOne());
			userAddress.setAddressLineTwo(userEditProfileForm.getAddressLineTwo());
			userAddress.setCity(userEditProfileForm.getCity());
			userAddress.setState(userEditProfileForm.getState());
			userAddress.setZipCode(userEditProfileForm.getZipCode());
			userAddress.setCountry(userEditProfileForm.getCountry());
			
			if (!multipartFile.isEmpty()) {
				profilePicture = s3fileUploader.uploadSingleFile(multipartFile, bucketName);
				userAddress.setProfilePicture(profilePicture);
			}
			
			user.setUserAddress(userAddress);
			userAddressRepository.save(userAddress);
		}
		
		if (loggedIn.isAdmin()){
			user.setRoles(userEditProfileForm.getRoles());
		}
		
		userRepository.save(user);
	}
	
	/**
	 * Updates user account from the info gotten from the userController Edit Account Form fields
	 * by getting the user id from the user loggedin session and compare it with the userid
	 * passed from the path variable.
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void updateAccount(String userName, UserEditAccountForm userEditAccountForm, BindingResult result) {
		
		User loggedIn = MyUtility.getSessionUser();
		MyUtility.validate(loggedIn.isAdmin() || loggedIn.getUserName().equals(userName), "noPermissions");
		User user = userRepository.findByUserName(userName);
		
		if(!passwordEncoder.matches(userEditAccountForm.getOldPassword(), user.getPassword())){
			result.reject("invalidPassword");
		}
		
		if (result.hasErrors()){
			return;
		}
		
		user.setPassword(passwordEncoder.encode(userEditAccountForm.getPassword().trim()));
		userRepository.save(user);
	}
	
	/**
	 * Updates user notification from the info gotten from the userController Edit Notification fields
	 * by getting the user id from the user loggedin session and compare it with the userid
	 * passed from the path variable.
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void updateNotification(String userName, UserEditNotificationForm userEditNotificationForm) {
		
		User loggedIn = MyUtility.getSessionUser();
		MyUtility.validate(loggedIn.isAdmin() || loggedIn.getUserName().equals(userName), "noPermissions");
		User user = userRepository.findByUserName(userName);
		
		UserNotification userNotification = new UserNotification();
		
		userNotification.setBlogUpdates(userEditNotificationForm.isBlogUpdates());
		userNotification.setEventUpdates(userEditNotificationForm.isEventUpdates());
		userNotification.setSermonUpdates(userEditNotificationForm.isSermonUpdates());
		userNotification.setWeeklyUpdates(userEditNotificationForm.isWeeklyUpdates());
		
		List<UserNotification> list = new ArrayList<UserNotification>();
		
		if(!user.getUserNotification().isEmpty()){
			list.add(userNotification);
			user.setUserNotification(list);
			
		}else{
			user.getUserNotification().add(userNotification);
		}
		
		userRepository.save(user);
	}
	
	/**
	 * Find returns all users
	 */
	@Override
	public Page<User> findAllUsers(Pageable pageable) {
		
		return userRepository.findAll(pageable);
	}
	
	@Override
	public Long count(){
		return userRepository.count();
	}
}
