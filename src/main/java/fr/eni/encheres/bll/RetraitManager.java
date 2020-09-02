package fr.eni.encheres.bll;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.eni.encheres.bo.AppRole;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.RetraitId;
import fr.eni.encheres.dao.ArticleVenduRepository;
import fr.eni.encheres.dao.RetraitRepository;
import fr.eni.encheres.services.ArticleVenduForm;

@Component
public class RetraitManager {
	
	@Autowired
	RetraitRepository retraitRep; 
	
	@Autowired
	ArticleVenduRepository articleVenduRep; 

public Retrait ajouterRetrait(RetraitId newRetraitId, ArticleVenduForm articleForm) {
	Retrait retrait = null;
	try {
		retrait = new Retrait(newRetraitId, articleForm.getRue(), articleForm.getCode_postal(), articleForm.getVille());
		retrait = retraitRep.save(retrait);
	} catch (Exception e) {
		throw e;
	}
	return retrait;
}

public Retrait selectionnerRetrait(RetraitId retraitId) {
	Retrait retrait = null; 
	try {
		retrait = retraitRep.findByRetraitId(retraitId); 
	} catch (Exception e) {
		throw e;
	}
	return retrait;
}

public void supprimerRetrait(Retrait retrait) {
	retraitRep.delete(retrait);
	
}

public void updateRetrait(RetraitId retraitId, ArticleVenduForm articleForm) {
	Retrait retrait = null;
	try {
		retrait = new Retrait(retraitId, articleForm.getRue(), articleForm.getCode_postal(), articleForm.getVille());
		retrait = retraitRep.save(retrait);
	} catch (Exception e) {
		throw e;
	}
}

} 
	
