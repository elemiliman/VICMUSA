package org.vicmusa.church.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.vicmusa.church.dto.UserEditAccountForm;
import org.vicmusa.church.dto.UserEditNotificationForm;
import org.vicmusa.church.dto.UserEditProfileForm;
import org.vicmusa.church.entities.User;
import org.vicmusa.church.entities.UserNotification;
import org.vicmusa.church.services.UserService;
import org.vicmusa.church.utilities.MyUtility;
import org.vicmusa.church.validators.UserEditAccountFormValidator;

@Controller
@RequestMapping("/users") /*Because all the resource paths are common*/
public class UserController {

	private UserService userService;
	private UserEditAccountFormValidator userEditAccountFormValidator;
	
	/**
	 * Constructor based injection
	 */
	@Autowired
	UserController(UserService userService, UserEditAccountFormValidator userEditAccountFormValidator){
		this.userService = userService;
		this.userEditAccountFormValidator = userEditAccountFormValidator;
	}
	
	/**
	 * User edit account info form validation binder
	 */
	@InitBinder("userEditAccountForm")
	protected void initUserEditAccount(WebDataBinder binder){
		binder.setValidator(userEditAccountFormValidator);
	}
	
	/**
     * Gets the verification code from the URL sent to the new user 
     * Passes the verification code to the user service to verify the code
     * After sending the verification code the user will be logged out
     */
	@RequestMapping("/{verificationCode}/verify")
	public String verify(@PathVariable("verificationCode") String verificationCode, RedirectAttributes redirectAttributes,
			HttpServletRequest request) throws ServletException{
		
		userService.verify(verificationCode);
		MyUtility.flash(redirectAttributes, "success", "verificationSuccess");
		request.logout();
		
		return "redirect:/";
	}
	
	/**
     * Resends the verification code if user did not receive or 
     * if user have deleted the email that has the verification code
     * After sending the verification the user will be logged out
     */
	@RequestMapping("/resend-verification-mail")
	public String redendVerification(RedirectAttributes redirectAttributes, HttpServletRequest request) throws ServletException{
		
		userService.resendVerificationCode();
		
		request.logout();
		
		MyUtility.flash(redirectAttributes, "warning", "resendVerificationCode");
		
		return "redirect:/";
	}
	
	/**
     * Displays user profile by using path variable(userid) to find user info from the database
     * using a service method called finOne(userid)
     * Then add an attribute named user(name of the user class in camel case) to the model which 
     * will be passed to the JSP for display
     */
	@RequestMapping(value = "/{userName}")
    public String getByUserName(@PathVariable("userName") String userName, Model model) {
		
		String pageTitle = "Profile Settings";
		model.addAttribute("PageTitle", pageTitle);
    	model.addAttribute("user", userService.findOneUser(userName));
	  	return "user";
    }
	
	/**
     * Controller method for displaying the user profile to  be edited
     */
	@RequestMapping(value = "/{userName}/profile")
    public String editProfile(@PathVariable("userName") String userName, Model model) {
    	
		User user = userService.findOneUser(userName);
		UserEditProfileForm form = new UserEditProfileForm();
		form.setFirstName(user.getFirstName());
		form.setLastName(user.getLastName());
		String profilePicture = "";
		if(user.getUserAddress() != null) {
			form.setGender(user.getUserAddress().getGender());
			form.setDob(user.getUserAddress().getDob());
			form.setAddressLineOne(user.getUserAddress().getAddressLineOne());
			form.setAddressLineTwo(user.getUserAddress().getAddressLineTwo());
			form.setCity(user.getUserAddress().getCity());
			form.setState(user.getUserAddress().getState());
			form.setZipCode(user.getUserAddress().getZipCode());
			form.setCountry(user.getUserAddress().getCountry());
			form.setRoles(user.getRoles());
			profilePicture = user.getUserAddress().getProfilePicture();
		}
		String pageTitle = "Your Profile";
		model.addAttribute("PageTitle", pageTitle);
		model.addAttribute("profilePicture", profilePicture);
    	model.addAttribute("userEditProfileForm", form);
    	
		return "user-edit-profile";

    }
	/**
     * Controller method for editing user profile by calling update user service method
     */
	@RequestMapping(value = "/{userName}/profile", method = RequestMethod.POST)
	public String editProfile(@PathVariable("userName") String userName, @ModelAttribute("userEditProfileForm") @Valid UserEditProfileForm userEditProfileForm,
			@RequestParam("file")MultipartFile multipartFile, BindingResult result, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()){
			return "user-edit-profile";
		}

		userService.updateProfile(userName, userEditProfileForm, multipartFile);
		
		MyUtility.flash(redirectAttributes, "success", "editSuccessful");

		return "redirect:/users/{userName}/profile";
	}
	
	/**
     * Controller method for displaying the user account information to  be edited
     */
	@RequestMapping(value = "/{userName}/account")
	public String editAccount(@PathVariable("userName") String userName, Model model) {
		
		String pageTitle = "Account Setting";
		model.addAttribute("PageTitle", pageTitle);
		
		model.addAttribute("userEditAccountForm", new UserEditAccountForm());
		
		return "user-edit-account";
	}
	
	/**
     * Controller method for editing user account by calling editAccount service method
     */
	@RequestMapping(value = "/{userName}/account", method = RequestMethod.POST)
	public String editAccount(@PathVariable("userName") String userName, @ModelAttribute("userEditAccountForm") @Valid UserEditAccountForm userEditAccountForm,
			BindingResult result, RedirectAttributes redirectAttributes){
		
		if(result.hasErrors()){
			return "user-edit-account";
		}
		
		userService.updateAccount(userName, userEditAccountForm, result);
		
		MyUtility.flash(redirectAttributes, "success", "editSuccessful");
		
		return "redirect:/users/{userName}/account";
	}
	
	/**
     * Controller method for editing user notifications by calling editNotification service method
     */
	@RequestMapping(value = "/{userName}/notification")
	public String editNotification(@PathVariable("userName") String userName, Model model){
		
		User user = userService.findOneUser(userName);
		UserEditNotificationForm form = new UserEditNotificationForm();
		UserNotification userNotification = new UserNotification();
		
		if(!user.getUserNotification().isEmpty()){
			userNotification = (UserNotification) user.getUserNotification().iterator().next();
			form.setBlogUpdates(userNotification.isBlogUpdates());
			form.setEventUpdates(userNotification.isEventUpdates());
			form.setSermonUpdates(userNotification.isSermonUpdates());
			form.setWeeklyUpdates(userNotification.isWeeklyUpdates());
			
		}
		String pageTitle = "Notification Setting";
		model.addAttribute("PageTitle", pageTitle);
		model.addAttribute("userEditNotificationForm", form);
		
		return "user-edit-notification";
	}
	
	@RequestMapping(value = "/{userName}/notification", method = RequestMethod.POST)
	public String editNotification(@PathVariable("userName") String userName, @ModelAttribute("userEditNotificationForm") UserEditNotificationForm userEditNotificationForm,
			RedirectAttributes redirectAttributes){
		
		userService.updateNotification(userName, userEditNotificationForm);
		
		return "redirect:/users/{userName}/notification";
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String findAllUsers(Pageable pageable,  Model model, HttpServletRequest request){
		
		String pageTitle = "Users";
		model.addAttribute("PageTitle", pageTitle);
		
		int startPage = 0;
	    int recordsPerPage = 20;
	    int fromIndex = 0;
		int toIndex = 0;
		
		Page<User> allUsers = userService.findAllUsers(pageable);
		
		if(request.getParameter("offset")  != null){
			startPage = Integer.parseInt(request.getParameter("offset"));
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}else{
			fromIndex = Math.abs(startPage - 0);
			toIndex = (recordsPerPage + startPage);
		}
		
		if(toIndex > allUsers.getContent().size()){
			toIndex = allUsers.getContent().size();
			model.addAttribute("allUsers", allUsers.getContent().subList(fromIndex , toIndex));
		}else{
			model.addAttribute("allUsers", allUsers.getContent().subList(fromIndex , toIndex));
		}
		
		model.addAttribute("count", allUsers.getTotalElements() - 10);
		
		model.addAttribute("offset", startPage);
		
		return "all-users";
	}
	
}
