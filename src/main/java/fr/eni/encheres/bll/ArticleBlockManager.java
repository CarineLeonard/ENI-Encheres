package fr.eni.encheres.bll;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ch.qos.logback.core.joran.conditional.IfAction;
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
	
	public List<ArticleBlock> selectionnerTousArticleBlocks(HttpServletRequest request) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerTousArticleVendus();
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle(), request);
				articleBlocks.add(articleBlock);
			}
			
		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	

	public ArticleBlock selectionnerArticleBlockById(Long id, HttpServletRequest request) throws Exception {
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
			
//		    File dir = new File(request.getServletContext().getRealPath("/upload"));
//	    	if (!dir.exists()) {
//	    		dir.mkdirs();
//	    	}
//		    for(File file : dir.listFiles()) {
//		        if(file.getName().startsWith(String.valueOf(id +"_")))
//					articleBlock.setImage(file.getCanonicalPath());
//		    }
		} catch (Exception e) {
			throw e;
		}
		return articleBlock;
	}
	
	public List<ArticleBlock> selectionnerArticleBlocksEncheresOuvertes(Long categorie, String string, HttpServletRequest request) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusEncheresOuvertes(categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle(), request);
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksMesEncheresEncours(Long noUtilisateur, Long categorie, String string, HttpServletRequest request) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesEncheresEncours(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle(), request);
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksMesEncheresRemportees(Long noUtilisateur, Long categorie, String string, HttpServletRequest request) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesEncheresRemportees(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle(), request);
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	
	public List<ArticleBlock> selectionnerArticleBlocksEncheresOuvertesMesEncheresEncours(Long categorie, String string, HttpServletRequest request) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusEncheresOuvertesMesEncheresEncours(categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle(), request); //TODO kesskya ?
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksEncheresOuvertesMesEncheresRemportees(Long noUtilisateur, Long categorie, String string, HttpServletRequest request) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusEncheresOuvertesMesEncheresRemportees(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle(), request);
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksMesEncheresEncoursMesEncheresRemportees(Long noUtilisateur, Long categorie, String string, HttpServletRequest request) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesEncheresEncoursMesEncheresRemportees(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle(), request);
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	
	public List<ArticleBlock> selectionnerArticleBlocksMesVentesEnCours(Long noUtilisateur, Long categorie, String string, HttpServletRequest request) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesVentesEnCours(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle(), request);
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksMesVentesNonDebutees(Long noUtilisateur, Long categorie, String string, HttpServletRequest request) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesVentesNonDebutees(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle(), request);
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksMesVentesTerminees(Long noUtilisateur, Long categorie, String string, HttpServletRequest request) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesVentesTerminees(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle(), request);
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}

	public List<ArticleBlock> selectionnerArticleBlocksMesVentesEnCoursMesVentesNonDebutees(Long noUtilisateur, Long categorie, String string, HttpServletRequest request) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesVentesEnCoursMesVentesNonDebutees(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle(), request);
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksMesVentesEnCoursMesVentesTerminees(Long noUtilisateur, Long categorie, String string, HttpServletRequest request) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesVentesEnCoursMesVentesTerminees(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle(), request);
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksMesVentesNonDebuteesMesVentesTerminees(Long noUtilisateur, Long categorie, String string, HttpServletRequest request) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusMesVentesNonDebuteesMesVentesTerminees(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle(), request);
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}
	public List<ArticleBlock> selectionnerArticleBlocksToutesMesVentes(Long noUtilisateur, Long categorie, String string, HttpServletRequest request) throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerArticleVendusToutesMesVentes(noUtilisateur, categorie, string);
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = selectionnerArticleBlockById(art.getNoArticle(), request);
				articleBlocks.add(articleBlock);
			}

		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}

}
