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
	
	public List<ArticleBlock> selectionnerTousArticleBlocks() throws Exception {
		List<ArticleBlock> articleBlocks = new ArrayList<ArticleBlock>();
		SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yy");
		try {
			List<ArticleVendu> listeArticles = (List<ArticleVendu>) this.articleVenduRepository.findAll();
			
			for(ArticleVendu art : listeArticles) {
				ArticleBlock articleBlock = new ArticleBlock();
				articleBlock.setNoArticle(art.getNoArticle());
				articleBlock.setNomArticle(art.getNomArticle());
				articleBlock.setDescription(art.getDescription());
				articleBlock.setPrix(art.getPrixInital());
				articleBlock.setDateFinEncheres(dateFormater.format( art.getDateFinEncheres() ));
				articleBlock.setPseudoVendeur(art.getUtilisateur().getPseudo());
				articleBlocks.add(articleBlock);
			}
			
		} catch (Exception e) {
			throw e;
		}
		return articleBlocks;
	}

}
