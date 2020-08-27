package fr.eni.encheres.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import fr.eni.encheres.bo.AppRole;
import fr.eni.encheres.bo.UserRole;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.AppRoleRepository;
import fr.eni.encheres.dao.UserRoleRepository;
import fr.eni.encheres.dao.UtilisateurRepository;
import fr.eni.encheres.services.UtilisateurForm;

@Component
public class UtilisateurManager {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private AppRoleRepository appRoleRepository;
	
	@Autowired
	private AppRoleManager appRoleManager;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public Utilisateur ajouterUtilisateur(UtilisateurForm utilisateurForm) throws Exception {
		// TODO valider les paramètres avant de créer l'utilisateur ?
		Utilisateur nouvelUtilisateur = null;
		try {
			nouvelUtilisateur = new Utilisateur(utilisateurForm.getNoUtilisateur(),
															utilisateurForm.getPseudo().trim(),
															utilisateurForm.getNom().trim().toUpperCase(),
															utilisateurForm.getPrenom().trim(),
															utilisateurForm.getEmail().trim(),
															utilisateurForm.getTelephone().trim(),
															utilisateurForm.getRue().trim(),
															utilisateurForm.getCode_postal().trim(),
															utilisateurForm.getVille().trim().toUpperCase(),
															utilisateurForm.getMotDePasse(),
															utilisateurForm.getCredit(),
															utilisateurForm.isActif());
			nouvelUtilisateur.setMotDePasse(passwordEncoder.encode(nouvelUtilisateur.getMotDePasse()));
			nouvelUtilisateur = this.utilisateurRepository.save(nouvelUtilisateur);

			AppRole userAppRole = appRoleRepository.findByRoleName("ROLE_USER");
			if (userAppRole == null) {
				userAppRole = appRoleManager.ajouterAppRole("ROLE_USER");
			}
	
			UserRole nouvelUserRole = new UserRole(nouvelUtilisateur, userAppRole);
			nouvelUserRole = userRoleRepository.save(nouvelUserRole);
		} catch (Exception e) {
			throw e;
		}
		return nouvelUtilisateur;
	}
	
	public Utilisateur selectionnerUtilisateur(String pseudo) throws Exception {
		Utilisateur utilisateur = null;
		try {
			utilisateur = utilisateurRepository.findByPseudo(pseudo);
		} catch (Exception e) {
			throw e;
		}
		return utilisateur;
	}

	public Utilisateur updateUtilisateur(UtilisateurForm utilisateurForm) throws Exception {
		// TODO valider les paramètres avant de créer l'utilisateur ?
		Utilisateur nouvelUtilisateur = null;
		try {
			nouvelUtilisateur = new Utilisateur(utilisateurForm.getNoUtilisateur(),
															utilisateurForm.getPseudo().trim(),
															utilisateurForm.getNom().trim().toUpperCase(),
															utilisateurForm.getPrenom().trim(),
															utilisateurForm.getEmail().trim(),
															utilisateurForm.getTelephone().trim(),
															utilisateurForm.getRue().trim(),
															utilisateurForm.getCode_postal().trim(),
															utilisateurForm.getVille().trim().toUpperCase(),
															utilisateurForm.getMotDePasse(),
															utilisateurForm.getCredit(),
															utilisateurForm.isActif());
			nouvelUtilisateur.setMotDePasse(passwordEncoder.encode(nouvelUtilisateur.getMotDePasse()));
			nouvelUtilisateur = this.utilisateurRepository.save(nouvelUtilisateur);
		} catch (Exception e) {
			throw e;
		}
		return nouvelUtilisateur;
	}
	
}
