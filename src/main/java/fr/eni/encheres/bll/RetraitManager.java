package fr.eni.encheres.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.eni.encheres.bo.AppRole;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dao.ArticleVenduRepository;
import fr.eni.encheres.dao.RetraitRepository;

@Component
public class RetraitManager {
	
	@Autowired
	RetraitRepository retraitRep; 
	
	@Autowired
	ArticleVenduRepository articleVenduRep; 
	
public Retrait ajouterRetrait(ArticleVendu article) throws Exception {
		Retrait retrait = null;
		try {
			retrait = new Retrait();
			retrait = retraitRep.save(retrait);
		} catch (Exception e) {
			throw e;
		}
		return retrait;
	} 
	
}
