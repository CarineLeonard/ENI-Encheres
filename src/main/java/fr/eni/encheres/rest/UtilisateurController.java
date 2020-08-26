package fr.eni.encheres.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.UtilisateurRepository;

@RestController
public class UtilisateurController {

	@Autowired
	private UtilisateurRepository userRepository;

	@Secured("ROLE_USER")
	@GetMapping("/userInfo2")
	public Utilisateur getUser(@RequestParam(value = "pseudo", defaultValue= "") String pseudo) {
		Utilisateur user = this.userRepository.findByPseudo(pseudo);
		return user;
	}
}
