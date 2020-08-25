package fr.eni.encheres.rest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.encheres.bo.UtilisateurBO;

@RestController
public class UtilisateurController {

	@Secured("ROLE_USER")
	@GetMapping("/userInfo2")
	public UtilisateurBO getUser(@RequestParam(value = "pseudo", defaultValue= "") String pseudo) {
		return new UtilisateurBO(pseudo);
	}
}
