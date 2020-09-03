package fr.eni.encheres.bll;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.eni.encheres.bo.ArticleBlock;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.RetraitId;

@Component
public class ArticleBlockManager {

	@Autowired
	ArticleVenduManager articleVenduManager;

	@Autowired
	RetraitManager retraitManager; 

	@Autowired
	EnchereManager enchereManager;
	
	public List<ArticleBlock> selectionnerTousArticleBlocks() throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerTousArticleVendus();
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}
			
		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	
	public ArticleBlock selectionnerArticleBlockById(Long id) throws Exception {
		ArticleBlock articleBlock = null;
		SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy");
		try {
			ArticleVendu art = articleVenduManager.selectionnerArticleVendu(id);
			
			articleBlock = new ArticleBlock();
			articleBlock.setNoArticle(art.getNoArticle());
			articleBlock.setNomArticle(art.getNomArticle());
			articleBlock.setDescription(art.getDescription());
			articleBlock.setCategorie(art.getCategorie().getLibelle());
			Enchere meilleureEnchere = enchereManager.selectionnerMeilleureEnchere(art.getNoArticle());
			if (meilleureEnchere != null) {
				articleBlock.setMeilleureOffre(meilleureEnchere);
			}
			articleBlock.setMiseAPrix(art.getPrixInital());
			articleBlock.setDateFinEncheres(dateFormater.format( art.getDateFinEncheres() ));
			articleBlock.setRetrait(retraitManager.selectionnerRetrait(new RetraitId(art)));
			articleBlock.setPseudoVendeur(art.getUtilisateur().getPseudo());
			//articleBlock.setImage(image); //TODO Là ya du boulot
			
		} catch (Exception e) {
			throw e;
		}
		return articleBlock;
	}
	
	public List<ArticleBlock> selectionnerArticleBlocksEncheresOuvertes(String categorie, String string) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusEncheresOuvertes(categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksMesEncheresEncours(Long noUtilisateur, String categorie, String string) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesEncheresEncours(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksMesEncheresRemportees(Long noUtilisateur, String categorie, String string) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesEncheresRemportees(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	
	public List<ArticleBlock> selectionnerArticleBlocksEncheresOuvertesMesEncheresEncours(String categorie, String string) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusEncheresOuvertesMesEncheresEncours(categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle()); //TODO kesskya ?
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksEncheresOuvertesMesEncheresRemportees(Long noUtilisateur, String categorie, String string) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusEncheresOuvertesMesEncheresRemportees(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksMesEncheresEncoursMesEncheresRemportees(Long noUtilisateur, String categorie, String string) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesEncheresEncoursMesEncheresRemportees(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	
	public List<ArticleBlock> selectionnerArticleBlocksMesVentesEnCours(Long noUtilisateur, String categorie, String string) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesVentesEnCours(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksMesVentesNonDebutees(Long noUtilisateur, String categorie, String string) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesVentesNonDebutees(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksMesVentesTerminees(Long noUtilisateur, String categorie, String string) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesVentesTerminees(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}

	public List<ArticleBlock> selectionnerArticleBlocksMesVentesEnCoursMesVentesNonDebutees(Long noUtilisateur, String categorie, String string) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesVentesEnCoursMesVentesNonDebutees(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksMesVentesEnCoursMesVentesTerminees(Long noUtilisateur, String categorie, String string) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesVentesEnCoursMesVentesTerminees(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksMesVentesNonDebuteesMesVentesTerminees(Long noUtilisateur, String categorie, String string) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesVentesNonDebuteesMesVentesTerminees(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksToutesMesVentes(Long noUtilisateur, String categorie, String string) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusToutesMesVentes(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}

}
