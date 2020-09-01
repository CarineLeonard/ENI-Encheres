package fr.eni.encheres.bll;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.eni.encheres.bo.ArticleBlock;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.dao.ArticleVenduRepository;
import fr.eni.encheres.dao.CategorieRepository;
import fr.eni.encheres.dao.EnchereRepository;
import fr.eni.encheres.dao.RetraitRepository;
import fr.eni.encheres.dao.UtilisateurRepository;

@Component
public class ArticleBlockManager {

	@Autowired
	ArticleVenduRepository articleVenduRepository;

	@Autowired
	CategorieRepository categorieRepository;

	@Autowired
	EnchereRepository enchereRepository;

	@Autowired
	RetraitRepository retraitRepository;

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Autowired
	CategorieManager categorieManager;

	@Autowired
	ArticleVenduManager articleVenduManager;
	
	public List<ArticleBlock> selectionnerTousArticleBlocks() throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yy");
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduManager.selectionnerTousArticleVendus();
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = new ArticleBlock();
				articleBlock.setNoArticle(art.getNoArticle());
				articleBlock.setNomArticle(art.getNomArticle());
				articleBlock.setDescription(art.getDescription());
				articleBlock.setMeilleureOffre(art.getPrixInital()); //TODO Faire le lien avec la meilleur enchere sur l'article
				articleBlock.setMiseAPrix(art.getPrixInital());
				articleBlock.setDateFinEncheres(dateFormater.format( art.getDateFinEncheres() ));
				//articleBlock.setRetrait(retrait); //TODO Faire le lien avec le retrait de l'article
				articleBlock.setPseudoVendeur(art.getUtilisateur().getPseudo());
				//articleBlock.setImage(image); //TODO Là ya du boulot
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
			ArticleVendu art = this.articleVenduManager.selectionnerArticleVendu(id);
			
			articleBlock = new ArticleBlock();
			articleBlock.setNoArticle(art.getNoArticle());
			articleBlock.setNomArticle(art.getNomArticle());
			articleBlock.setDescription(art.getDescription());
			articleBlock.setCategorie(art.getCategorie().getLibelle());
			articleBlock.setMeilleureOffre(art.getPrixInital()); //TODO Faire le lien avec la meilleur enchere sur l'article
			articleBlock.setMiseAPrix(art.getPrixInital());
			articleBlock.setDateFinEncheres(dateFormater.format( art.getDateFinEncheres() ));
			//articleBlock.setRetrait(retrait); //TODO Faire le lien avec le retrait de l'article
			articleBlock.setPseudoVendeur(art.getUtilisateur().getPseudo());
			//articleBlock.setImage(image); //TODO Là ya du boulot
			
		} catch (Exception e) {
			throw e;
		}
		return articleBlock;
	}

}
