package org.vicmusa.church.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.vicmusa.church.dto.UserEditAccountForm;

@Component
public class UserEditAccountFormValidator extends LocalValidatorFactoryBean {

	@Override
	public boolean supports(Class<?> clazz)  {
		return clazz.isAssignableFrom(UserEditAccountForm.class);
	}
	
	@Override
	public void validate(Object obj, Errors errors, final Object... validationHints) {
		
		super.validate(obj, errors, validationHints);
		
		if (!errors.hasErrors()) {
			UserEditAccountForm userEditAccountForm = (UserEditAccountForm) obj;
			if (!userEditAccountForm.getPassword().equals(userEditAccountForm.getRetypePassword()))
				errors.reject("passwordsDoNotMatch");
		}
	}
}
