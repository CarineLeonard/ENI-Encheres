package fr.eni.encheres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.services.EnchereForm;
import fr.eni.encheres.services.RetraitForm;

@Component
public class EnchereValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == RetraitForm.class;
	}
	
	@Autowired
	EnchereManager enchereManager;

	@Override
	public void validate(Object target, Errors errors) {
		EnchereForm enchereForm = (EnchereForm) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "montantEnchere", "NotEmpty.EnchereForm.montant");

		if (!errors.hasErrors()) {
			Enchere meilleureOffre = enchereManager.selectionnerMeilleureEnchere(enchereForm.getArticleVendu().getNoArticle());
			if (meilleureOffre != null && enchereForm.getMontantEnchere() <= meilleureOffre.getMontantEnchere()) {
				errors.rejectValue("montantEnchere", "Size.EnchereForm.montant");
			}
		}
	}
}
