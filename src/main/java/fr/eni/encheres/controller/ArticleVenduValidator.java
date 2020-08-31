package fr.eni.encheres.controller;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.ArticleVenduRepository;
import fr.eni.encheres.dao.UtilisateurRepository;
import fr.eni.encheres.services.ArticleVenduForm;

@Component
public class ArticleVenduValidator implements Validator{

	@Autowired
	private ArticleVenduRepository articleVenduRep ;
	
	@Autowired
	private UtilisateurRepository utilisateurRep;

	// The classes are supported by this validator.
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == ArticleVenduForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		ArticleVenduForm articleForm = (ArticleVenduForm) target; 
		
		// Check the fields of UtilisateurForm.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomArticle", "NotEmpty.ArticleVenduForm.nomArticle");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty.ArticleVenduForm.description");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateDebutEncheres", "NotEmpty.ArticleVenduForm.dateDebutEncheres");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateFinEncheres", "NotEmpty.ArticleVenduForm.dateFinEncheres");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categorie", "NotEmpty.ArticleVenduForm.categorie");
		
		// vérifier connexion 
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Utilisateur currentUser = utilisateurRep.findByPseudo(authentication.getName());
		
		// autres 
		if (!errors.hasErrors()) { 
			if (articleForm.getNomArticle().trim().length() > 30) {
				errors.rejectValue("nomArticle", "Size.ArticleVenduForm.nomArticle");
			}
			if (articleForm.getDescription().trim().length() > 300) {
				errors.rejectValue("nomArticle", "Size.ArticleVenduForm.description");
			}
			Long millis = System.currentTimeMillis();
			Date dateactuelle = new Date (millis); 
			
			if (articleForm.getDateDebutEncheres().compareTo(dateactuelle) > 0) {
				errors.rejectValue("dateDebutEncheres", "Pattern.ArticleVenduForm.dateDebutEncheres"); 
			}
			if (articleForm.getDateFinEncheres().compareTo(dateactuelle) < 0 || articleForm.getDateFinEncheres().compareTo(articleForm.getDateDebutEncheres()) < 0 ) {
				errors.rejectValue("dateDebutEncheres", "Pattern.ArticleVenduForm.dateDebutEncheres"); 
			}
			if (articleForm.getPrixVente() < articleForm.getPrixInital()) {
				errors.rejectValue("prixVente", "Pattern.ArticleVenduForm.prixVente");
			}
			
			if (articleForm.getPrixInital()<0) {
				errors.rejectValue("prixVente", "Pattern.ArticleVenduForm.prixInitial");
			}
		}

	
		
	}
	

}
