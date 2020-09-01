package fr.eni.encheres.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import fr.eni.encheres.services.RetraitForm;

@Component
public class RetraitValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == RetraitForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		RetraitForm retraitForm = (RetraitForm) target; 
		
		// Check the fields of RetraitForm.
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rue", "NotEmpty.RetraitForm.rue");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code_postal", "NotEmpty.RetraitForm.code_postal");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ville", "NotEmpty.RetraitForm.ville");
		
		if (!errors.hasErrors()) {
			// rue
			if (retraitForm.getRue().trim().length() > 50) {
				errors.rejectValue("rue", "Size.RetraitForm.rue");
			}
			// codepostal
			if (retraitForm.getCode_postal().trim().length() > 10) {
				errors.rejectValue("code_postal", "Size.RetraitForm.code_postal");
			}
			// ville
			if (retraitForm.getVille().trim().length() > 30) {
				errors.rejectValue("ville", "Size.RetraitForm.ville");
			}
		}
	}
}
