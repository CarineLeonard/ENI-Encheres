package fr.eni.encheres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.services.EnchereForm;

@Component
public class EnchereValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == EnchereForm.class;
	}
	
	@Autowired
	EnchereManager enchereManager;
	@Autowired
	UtilisateurManager utilisateurManager;

	@Override
	public void validate(Object target, Errors errors) {
		EnchereForm enchereForm = (EnchereForm) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "montantEnchere", "NotEmpty.EnchereForm.montant");
		
		if (enchereForm.getArticleVendu().getUtilisateur() == enchereForm.getUtilisateur()) {
			errors.rejectValue("montantEnchere", "Account.EnchereForm.user");
		}
		
		if (enchereForm.getMontantEnchere() > enchereForm.getUtilisateur().getCredit()) {
			errors.rejectValue("montantEnchere", "Account.EnchereForm.credit");
		}

		if (!errors.hasErrors()) {
			Enchere meilleureOffre = enchereManager.selectionnerMeilleureEnchere(enchereForm.getArticleVendu().getNoArticle());
			if (meilleureOffre != null && enchereForm.getMontantEnchere() <= meilleureOffre.getMontantEnchere()) {
				errors.rejectValue("montantEnchere", "Amount.EnchereForm.montant");
			} else if (enchereForm.getMontantEnchere() < enchereForm.getArticleVendu().getPrixInital()) {
				errors.rejectValue("montantEnchere", "Amount.EnchereForm.montant");
			}
		}
	}
}
