package org.vicmusa.church.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.vicmusa.church.dto.ForgotPasswordForm;
import org.vicmusa.church.dto.ResetPasswordForm;
import org.vicmusa.church.dto.SignupForm;
import org.vicmusa.church.dto.UserEditAccountForm;
import org.vicmusa.church.dto.UserEditNotificationForm;
import org.vicmusa.church.dto.UserEditProfileForm;
import org.vicmusa.church.entities.User;

public interface UserService {

	public abstract void signup(SignupForm signupForm);

	public abstract void verify(String verificationCode);
	
	public abstract void resendVerificationCode();

	public abstract void forgotPassword(ForgotPasswordForm forgotPasswordForm);

	public abstract void resetPassword(String forgotPasswordCode, ResetPasswordForm resetPasswordForm,
			BindingResult result);

	public abstract User findOneUser(String userName);

	public abstract void updateProfile(String userName, UserEditProfileForm userEditProfileForm, MultipartFile multipartFile);

	public abstract void updateAccount(String userName, UserEditAccountForm userEditAccountForm,
			BindingResult result);

	public abstract void updateNotification(String userName, UserEditNotificationForm userEditNotificationForm);

	public abstract Page<User> findAllUsers(Pageable pageable);

	public abstract Long count();
}
