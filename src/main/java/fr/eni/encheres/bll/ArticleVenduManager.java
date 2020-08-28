package fr.eni.encheres.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.eni.encheres.bo.AppRole;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dao.ArticleVenduRepository;
import fr.eni.encheres.dao.CategorieRepository;
import fr.eni.encheres.dao.EnchereRepository;
import fr.eni.encheres.dao.RetraitRepository;
import fr.eni.encheres.dao.UtilisateurRepository;
import fr.eni.encheres.services.ArticleVenduForm;

@Component 
public class ArticleVenduManager {
	
	@Autowired
	ArticleVenduRepository articleVenduRep; 
	
	@Autowired
	CategorieRepository categorieRep; 
	
	@Autowired
	EnchereRepository enchereRep; 
	
	@Autowired
	RetraitRepository retraitRep; 
	
	@Autowired
	UtilisateurRepository utilisateurRep; 
	
	@Autowired
	CategorieManager categorieManage; 
	
	@Autowired
	RetraitManager retraitManage ; 
	
	
	public ArticleVendu ajouterArticleVendu(ArticleVenduForm articleForm) throws Exception {
		
		ArticleVendu nouvelArticle = null; 
		try {
			nouvelArticle = new ArticleVendu(articleForm.getNoArticle(), 
												articleForm.getNomArticle().trim(),
												articleForm.getDescription().trim(),
												articleForm.getDateDebutEncheres(),
												articleForm.getDateFinEncheres(),
												articleForm.getPrixInital(),
												articleForm.getPrixVente(),
												articleForm.getUtilisateur(), 
												articleForm.getCategorie() ); 
				nouvelArticle = this.articleVenduRep.save(nouvelArticle);
				
			// TODO - appel des autres classes : à revoir 
			//Iterable<Categorie> listCategorie = categorieRep.findAll() ; 
		
			//Retrait retrait = retraitManage.ajouterRetrait(nouvelArticle);
						
		} catch (Exception e) {
			throw  e;
		}
		return nouvelArticle;
		
	}
	
	
	
	
}
