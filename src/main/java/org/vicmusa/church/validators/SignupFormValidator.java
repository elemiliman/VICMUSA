package org.vicmusa.church.validators;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.vicmusa.church.dto.SignupForm;
import org.vicmusa.church.entities.User;
import org.vicmusa.church.repositories.UserRepository;

@Component
public class SignupFormValidator extends LocalValidatorFactoryBean{

	private UserRepository userRepository;
	
	@Resource
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public boolean supports(Class<?> clazz){
		return clazz.isAssignableFrom(SignupForm.class);
	}
	
	@Override
	public void validate(Object obj, Errors errors, final Object... validationHints){
		
		super.validate(obj, errors, validationHints);
		
		if (!errors.hasErrors()) {
			SignupForm signupForm = (SignupForm) obj;
			User email = userRepository.findByEmail(signupForm.getEmail());
			User userName = userRepository.findByUserName(signupForm.getUserName());
			if (email != null){
				errors.rejectValue("email", "emailNotUnique");
			}
			if (userName != null){
				errors.rejectValue("userName", "userNameNotUnique");
			}
		}
	}
}
