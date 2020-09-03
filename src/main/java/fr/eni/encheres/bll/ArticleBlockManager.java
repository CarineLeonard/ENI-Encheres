package fr.eni.encheres.bll;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.eni.encheres.bo.ArticleBlock;
import fr.eni.encheres.bo.ArticleVendu;
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
	
	public List<ArticleBlock> selectionnerArticleBlocksEncheresOuvertes() throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusEncheresOuvertes();
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksMesEncheresEncours(Long noUtilisateur) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesEncheresEncours(noUtilisateur);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksMesEncheresRemportees(Long noUtilisateur) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesEncheresRemportees(noUtilisateur);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	
	public List<ArticleBlock> selectionnerArticleBlocksEncheresOuvertesMesEncheresEncours() throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusEncheresOuvertesMesEncheresEncours();
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksEncheresOuvertesMesEncheresRemportees(Long noUtilisateur) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusEncheresOuvertesMesEncheresRemportees(noUtilisateur);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksMesEncheresEncoursMesEncheresRemportees(Long noUtilisateur) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesEncheresEncoursMesEncheresRemportees(noUtilisateur);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	
	public List<ArticleBlock> selectionnerArticleBlocksMesVentesEnCours(Long noUtilisateur) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesVentesEnCours(noUtilisateur);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksMesVentesNonDebutees(Long noUtilisateur) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesVentesNonDebutees(noUtilisateur);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksMesVentesTerminees(Long noUtilisateur) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesVentesTerminees(noUtilisateur);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}

	public List<ArticleBlock> selectionnerArticleBlocksMesVentesEnCoursMesVentesNonDebutees(Long noUtilisateur) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesVentesEnCoursMesVentesNonDebutees(noUtilisateur);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksMesVentesEnCoursMesVentesTerminees(Long noUtilisateur) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesVentesEnCoursMesVentesTerminees(noUtilisateur);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle());
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksMesVentesNonDebuteesMesVentesTerminees(Long noUtilisateur) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesVentesNonDebuteesMesVentesTerminees(noUtilisateur);
			
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
