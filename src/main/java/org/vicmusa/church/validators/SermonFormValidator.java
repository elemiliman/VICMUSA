package org.vicmusa.church.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.vicmusa.church.dto.SermonForm;


@Component
public class SermonFormValidator extends LocalValidatorFactoryBean {

	@Override
	public boolean supports(Class<?> clazz){
		return clazz.isAssignableFrom(SermonForm.class);
	}
	
	@Override
	public void validate(Object obj, Errors errors, final Object... validationHints){
		
		super.validate(obj, errors, validationHints);
		
		if (!errors.hasErrors()) {
			SermonForm sermonForm = (SermonForm) obj;
			
		}
	}
}
