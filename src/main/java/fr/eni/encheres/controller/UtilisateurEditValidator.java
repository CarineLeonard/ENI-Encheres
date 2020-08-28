package fr.eni.encheres.controller;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.UtilisateurRepository;
import fr.eni.encheres.services.UtilisateurForm;

@Component
public class UtilisateurEditValidator implements Validator {

	// common-validator library.
	private EmailValidator emailValidator = EmailValidator.getInstance();

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// The classes are supported by this validator.
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == UtilisateurForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		UtilisateurForm utilisateurForm = (UtilisateurForm) target;

		// Check the fields of UtilisateurForm.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pseudo", "NotEmpty.UtilisateurForm.pseudo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nom", "NotEmpty.UtilisateurForm.nom");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prenom", "NotEmpty.UtilisateurForm.prenom");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.UtilisateurForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rue", "NotEmpty.UtilisateurForm.rue");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code_postal", "NotEmpty.UtilisateurForm.code_postal");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ville", "NotEmpty.UtilisateurForm.ville");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "motDePasse", "NotEmpty.UtilisateurForm.mot_de_passe");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.UtilisateurForm.confirmPassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "NotEmpty.UtilisateurForm.currentPassword"); // message à ajouter
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Utilisateur currentUser = utilisateurRepository.findByPseudo(authentication.getName());

		// email
		if (!this.emailValidator.isValid(utilisateurForm.getEmail())) {
			// Invalid email.
			errors.rejectValue("email", "Pattern.UtilisateurForm.email");
		} else {
			Utilisateur dbUser = utilisateurRepository.findByEmail(utilisateurForm.getEmail());
			if (dbUser != null && dbUser.getNom() != currentUser.getNom()) {
				// Email has been used by another account.
				errors.rejectValue("email", "Duplicate.UtilisateurForm.email");
			}
		}

		// currentPassword
		if (!errors.hasFieldErrors("currentPassword")) {
//			utilisateurForm.setCurrentPassword(passwordEncoder.encode(utilisateurForm.getMotDePasse()));
			if ( !passwordEncoder.matches(utilisateurForm.getCurrentPassword(), currentUser.getMotDePasse()) ) {
				System.out.println(utilisateurForm.getCurrentPassword() + " " + currentUser.getMotDePasse());
				errors.rejectValue("currentPassword", "NotMatching.UtilisateurForm.currentPassword");
			}
		}

		// pseudo
		if (!errors.hasFieldErrors("pseudo")) {
			Utilisateur dbUser = utilisateurRepository.findByPseudo(utilisateurForm.getPseudo());
			if (dbUser != null && dbUser.getNom() != currentUser.getNom()) {
				// pseudo is not available.
				errors.rejectValue("pseudo", "Duplicate.UtilisateurForm.pseudo");
			}
		}

		// autres
		if (!errors.hasErrors()) {
			// password
			if (!utilisateurForm.getConfirmPassword().equals(utilisateurForm.getMotDePasse())) {
				errors.rejectValue("confirmPassword", "Match.UtilisateurForm.confirmPassword");
			} else if (utilisateurForm.getMotDePasse().trim().length() > 30) {
				errors.rejectValue("motDePasse", "Size.UtilisateurForm.mot_de_passe");
			}
			
			if (utilisateurForm.getPseudo().trim().length() > 30) {
				errors.rejectValue("pseudo", "Size.UtilisateurForm.pseudo");
			}

			if (!utilisateurForm.getPseudo().matches("^[a-zA-Z0-9]*$")) {
				errors.rejectValue("pseudo", "Pattern.UtilisateurForm.pseudo");
			}

			// nom
			if (utilisateurForm.getNom().trim().length() > 30) {
				errors.rejectValue("nom", "Size.UtilisateurForm.nom");
			}

			// prenom
			if (utilisateurForm.getPrenom().trim().length() > 30) {
				errors.rejectValue("prenom", "Size.UtilisateurForm.prenom");
			}

			// email
			if (utilisateurForm.getEmail().trim().length() > 30) {
				errors.rejectValue("email", "Size.UtilisateurForm.email");
			}

			// telephone
			if (utilisateurForm.getTelephone().trim().length() > 15) {
				errors.rejectValue("nom", "Size.UtilisateurForm.telephone");
			}
			// rue
			if (utilisateurForm.getRue().trim().length() > 50) {
				errors.rejectValue("rue", "Size.UtilisateurForm.rue");
			}
			// codepostal
			if (utilisateurForm.getCode_postal().trim().length() > 10) {
				errors.rejectValue("code_postal", "Size.UtilisateurForm.code_postal");
			}
			// ville
			if (utilisateurForm.getVille().trim().length() > 30) {
				errors.rejectValue("ville", "Size.UtilisateurForm.ville");
			}

		}

	}

}
