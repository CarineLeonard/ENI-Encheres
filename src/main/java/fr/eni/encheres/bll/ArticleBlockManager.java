package fr.eni.encheres.bll;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.eni.encheres.bo.ArticleBlock;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.RetraitId;
import fr.eni.encheres.dao.CategorieRepository;

@Component
public class ArticleBlockManager {

	@Autowired
	ArticleVenduManager articleVenduManager;

	@Autowired
	CategorieRepository categorieRepository;

	@Autowired
	CategorieManager categorieManager;

	@Autowired
	RetraitManager retraitManager;
	
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
			ArticleVendu art = this.articleVenduManager.selectionnerArticleVendu(id);
			
			articleBlock = new ArticleBlock();
			articleBlock.setNoArticle(art.getNoArticle());
			articleBlock.setNomArticle(art.getNomArticle());
			articleBlock.setDescription(art.getDescription());
			articleBlock.setCategorie(art.getCategorie().getLibelle());
			articleBlock.setMeilleureOffre(art.getPrixInital()); //TODO Faire le lien avec la meilleur enchere sur l'article
			articleBlock.setMiseAPrix(art.getPrixInital());
			articleBlock.setDateFinEncheres(dateFormater.format( art.getDateFinEncheres() ));
			
			Retrait retrait = retraitManager.selectionnerRetrait(new RetraitId(art));
			articleBlock.setRetrait(retrait);
			
			articleBlock.setPseudoVendeur(art.getUtilisateur().getPseudo());
			//articleBlock.setImage(image); //TODO Là ya du boulot
			
		} catch (Exception e) {
			throw e;
		}
		return articleBlock;
	}

}
