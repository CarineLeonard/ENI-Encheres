package fr.eni.encheres.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import fr.eni.encheres.bo.AppRole;
import fr.eni.encheres.bo.UserRole;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.AppRoleRepository;
import fr.eni.encheres.dao.UserRoleRepository;
import fr.eni.encheres.dao.UtilisateurRepository;
import fr.eni.encheres.form.UtilisateurForm;

@Component
public class UtilisateurManager {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private AppRoleRepository appRoleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Utilisateur ajouterUtilisateur(UtilisateurForm utilisateurForm) throws Exception {
		// TODO valider les paramètres avant de créer l'utilisateur ?
		Utilisateur nouvelUtilisateur = null;
		try {
			nouvelUtilisateur = new Utilisateur(utilisateurForm.getNoUtilisateur(),
															utilisateurForm.getPseudo(),
															utilisateurForm.getNom(),
															utilisateurForm.getPrenom(),
															utilisateurForm.getEmail(),
															utilisateurForm.getTelephone(),
															utilisateurForm.getRue(),
															utilisateurForm.getCode_postal(),
															utilisateurForm.getVille(),
															utilisateurForm.getMotDePasse(),
															utilisateurForm.getCredit(),
															utilisateurForm.isActif());
			nouvelUtilisateur.setMotDePasse(passwordEncoder.encode(nouvelUtilisateur.getMotDePasse()));
			nouvelUtilisateur = this.utilisateurRepository.save(nouvelUtilisateur);
			System.out.println(nouvelUtilisateur);
			AppRole userAppRole = appRoleRepository.findByRoleName("ROLE_USER");
	
			UserRole nouvelUserRole = new UserRole(nouvelUtilisateur, userAppRole);
			nouvelUserRole = userRoleRepository.save(nouvelUserRole);
		} catch (Exception e) {
			throw e;
		}
		return nouvelUtilisateur;
	}
	
}
