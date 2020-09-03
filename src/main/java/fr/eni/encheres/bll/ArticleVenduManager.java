package fr.eni.encheres.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.eni.encheres.bo.ArticleVendu;
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

	public ArticleVendu ajouterArticleVendu(ArticleVenduForm articleForm) throws Exception {
		ArticleVendu nouvelArticle = null;
		try {
			nouvelArticle = new ArticleVendu(articleForm.getNoArticle(), articleForm.getNomArticle().trim(),
					articleForm.getDescription().trim(), articleForm.getDateDebutEncheres(),
					articleForm.getDateFinEncheres(), articleForm.getPrixInital(), articleForm.getPrixVente(),
					articleForm.getUtilisateur(), articleForm.getCategorie());
			nouvelArticle = this.articleVenduRep.save(nouvelArticle);
		} catch (Exception e) {
			throw e;
		}
		return nouvelArticle;
	}
	
	public List<ArticleVendu> selectionnerTousArticleVendus() throws Exception {
		List<ArticleVendu> listeArticles = null;
		try {
			listeArticles = (List<ArticleVendu>) this.articleVenduRep.findAll();

		} catch (Exception e) {
			throw e;
		}
		return listeArticles;
	}
	
	public List<ArticleVendu> selectionnerArticleVendusEncheresOuvertes() throws Exception {
		List<ArticleVendu> listeArticles = null;
		try {
			listeArticles = (List<ArticleVendu>) this.articleVenduRep.findEncheresOuvertes();

		} catch (Exception e) {
			throw e;
		}
		return listeArticles;
	}
	public List<ArticleVendu> selectionnerArticleVendusMesEncheresEncours(Long noUtilisateur) throws Exception {
		List<ArticleVendu> listeArticles = null;
		try {
			listeArticles = (List<ArticleVendu>) this.articleVenduRep.findMesEncheresEncours(noUtilisateur);

		} catch (Exception e) {
			throw e;
		}
		return listeArticles;
	}
	public List<ArticleVendu> selectionnerArticleVendusMesEncheresRemportees(Long noUtilisateur) throws Exception {
		List<ArticleVendu> listeArticles = null;
		try {
			listeArticles = (List<ArticleVendu>) this.articleVenduRep.findMesEncheresRemportees(noUtilisateur);

		} catch (Exception e) {
			throw e;
		}
		return listeArticles;
	}
	
	public List<ArticleVendu> selectionnerArticleVendusEncheresOuvertesMesEncheresEncours() throws Exception {
		List<ArticleVendu> listeArticles = null;
		try {
			listeArticles = (List<ArticleVendu>) this.articleVenduRep.findEncheresOuvertes();

		} catch (Exception e) {
			throw e;
		}
		return listeArticles;
	}
	public List<ArticleVendu> selectionnerArticleVendusEncheresOuvertesMesEncheresRemportees(Long noUtilisateur) throws Exception {
		List<ArticleVendu> listeArticles = null;
		try {
			listeArticles = (List<ArticleVendu>) this.articleVenduRep.findEncheresOuvertesMesEncheresRemportees(noUtilisateur);

		} catch (Exception e) {
			throw e;
		}
		return listeArticles;
	}
	public List<ArticleVendu> selectionnerArticleVendusMesEncheresEncoursMesEncheresRemportees(Long noUtilisateur) throws Exception {
		List<ArticleVendu> listeArticles = null;
		try {
			listeArticles = (List<ArticleVendu>) this.articleVenduRep.findMesEncheresEncoursMesEncheresRemportees(noUtilisateur);

		} catch (Exception e) {
			throw e;
		}
		return listeArticles;
	}
	
	public List<ArticleVendu> selectionnerArticleVendusMesVentesEnCours(Long noUtilisateur) throws Exception {
		List<ArticleVendu> listeArticles = null;
		try {
			listeArticles = (List<ArticleVendu>) this.articleVenduRep.findMesVentesEnCours(noUtilisateur);

		} catch (Exception e) {
			throw e;
		}
		return listeArticles;
	}
	public List<ArticleVendu> selectionnerArticleVendusMesVentesNonDebutees(Long noUtilisateur) throws Exception {
		List<ArticleVendu> listeArticles = null;
		try {
			listeArticles = (List<ArticleVendu>) this.articleVenduRep.findMesVentesNonDebutees(noUtilisateur);

		} catch (Exception e) {
			throw e;
		}
		return listeArticles;
	}
	public List<ArticleVendu> selectionnerArticleVendusMesVentesTerminees(Long noUtilisateur) throws Exception {
		List<ArticleVendu> listeArticles = null;
		try {
			listeArticles = (List<ArticleVendu>) this.articleVenduRep.findMesVentesTerminees(noUtilisateur);

		} catch (Exception e) {
			throw e;
		}
		return listeArticles;
	}

	public List<ArticleVendu> selectionnerArticleVendusMesVentesEnCoursMesVentesNonDebutees(Long noUtilisateur) throws Exception {
		List<ArticleVendu> listeArticles = null;
		try {
			listeArticles = (List<ArticleVendu>) this.articleVenduRep.findMesVentesEnCoursMesVentesNonDebutees(noUtilisateur);

		} catch (Exception e) {
			throw e;
		}
		return listeArticles;
	}
	public List<ArticleVendu> selectionnerArticleVendusMesVentesEnCoursMesVentesTerminees(Long noUtilisateur) throws Exception {
		List<ArticleVendu> listeArticles = null;
		try {
			listeArticles = (List<ArticleVendu>) this.articleVenduRep.findMesVentesEnCoursMesVentesTerminees(noUtilisateur);

		} catch (Exception e) {
			throw e;
		}
		return listeArticles;
	}
	public List<ArticleVendu> selectionnerArticleVendusMesVentesNonDebuteesMesVentesTerminees(Long noUtilisateur) throws Exception {
		List<ArticleVendu> listeArticles = null;
		try {
			listeArticles = (List<ArticleVendu>) this.articleVenduRep.findMesVentesNonDebuteesMesVentesTerminees(noUtilisateur);

		} catch (Exception e) {
			throw e;
		}
		return listeArticles;
	}
	
	public ArticleVendu selectionnerArticleVendu(Long noArticle) throws Exception {
		ArticleVendu article = null;
		try {
			article = articleVenduRep.findByNoArticle(noArticle);
		} catch (Exception e) {
			throw e;
		}
		return article;
	}
	
	public ArticleVendu updateArticleVendu(ArticleVenduForm articleForm) throws Exception {
		ArticleVendu newArticleVendu = null; 
		try {
			newArticleVendu = new ArticleVendu(articleForm.getNoArticle(), articleForm.getNomArticle().trim(),
					articleForm.getDescription().trim(), articleForm.getDateDebutEncheres(),
					articleForm.getDateFinEncheres(), articleForm.getPrixInital(), articleForm.getPrixVente(),
					articleForm.getUtilisateur(), articleForm.getCategorie());
			newArticleVendu = this.articleVenduRep.save(newArticleVendu);
		} catch (Exception e) {
			throw e;
		}
		return newArticleVendu; 
	}
	
	public void supprimerArticleVendu (Long noArticle) throws Exception {
		try {
			retraitRep.deleteById(noArticle);
			articleVenduRep.deleteById(noArticle);
		} catch (Exception e) {
			throw e;
		}
	}

	public void supprimerArticle(ArticleVendu articleVendu) {
		articleVenduRep.delete(articleVendu);
	}

	
}
