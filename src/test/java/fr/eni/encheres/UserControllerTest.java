package fr.eni.encheres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.encheres.dao.UtilisateurRepository;
import fr.eni.encheres.entity.Utilisateur;

@RestController
@RequestMapping("api/v1")
public class UserControllerTest {
	
	@Autowired
	private UtilisateurRepository utilisateurRepo ; 
	
	@RequestMapping(value ="user/{noUtilisateur}", method = RequestMethod.GET)
	public Utilisateur get(@PathVariable Long noUtilisateur) {
		return utilisateurRepo.findByNoUtilisateur(noUtilisateur);
	}

}
