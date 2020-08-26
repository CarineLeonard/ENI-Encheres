package fr.eni.encheres.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import fr.eni.encheres.bo.UtilisateurBO;
import fr.eni.encheres.dao.UtilisateurRepository;
import fr.eni.encheres.entity.Utilisateur;
import fr.eni.encheres.form.UtilisateurForm;

@Component 
public class UtilisateurValidator implements Validator {

	 // common-validator library.
    private EmailValidator emailValidator = EmailValidator.getInstance();
	
    @Autowired
    private UtilisateurRepository utilisateurRepository; 
	
 // The classes are supported by this validator.
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == UtilisateurForm.class;
    }

	@Override
	public void validate(Object target, Errors errors) {
		UtilisateurForm utilisateurForm = (UtilisateurForm) target; 
		
		// Check the fields of UtilisateurForm.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"pseudo", "NotEmpty.UtilisateurForm.pseudo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nom", "NotEmpty.UtilisateurForm.nom"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prenom", "NotEmpty.UtilisateurForm.prenom"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.UtilisateurForm.email"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rue", "NotEmpty.UtilisateurForm.rue"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code_postal", "NotEmpty.UtilisateurForm.code_postal"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ville", "NotEmpty.UtilisateurForm.ville"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "motDePasse", "NotEmpty.UtilisateurForm.mot_de_passe"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.UtilisateurForm.confirmPassword"); 
		
       if (!this.emailValidator.isValid(utilisateurForm.getEmail())) {
            // Invalid email.
            errors.rejectValue("email", "Pattern.appUserForm.email");
        } else if (utilisateurForm.getNoUtilisateur() == null) {
        	Utilisateur dbUser = utilisateurRepository.findByEmail(utilisateurForm.getEmail());
        	// AppUser dbUser = appUserDAO.findAppUserByEmail(appUserForm.getEmail());
            if (dbUser != null) {
                // Email has been used by another account.
                errors.rejectValue("email", "Duplicate.appUserForm.email");
            }
        }
          if (!errors.hasFieldErrors("pseudo")) {
            	Utilisateur dbUser = utilisateurRepository.findByPseudo(utilisateurForm.getPseudo());
            	// AppUser dbUser = appUserDAO.findAppUserByUserName(appUserForm.getUserName());
                if (dbUser != null) {
                    // pseudo is not available.
                    errors.rejectValue("pseudo", "Duplicate.UtilisateurForm.pseudo");
                }
            }
     
            if (!errors.hasErrors()) {
                if (!utilisateurForm.getConfirmPassword().equals(utilisateurForm.getMotDePasse())) {
                    errors.rejectValue("confirmPassword", "Match.appUserForm.confirmPassword");
                }
            }
	}  

}
