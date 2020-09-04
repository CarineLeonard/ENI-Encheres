package fr.eni.encheres.bo;

import java.util.Base64;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class ArticleBlock {

	private Long noArticle;
	private String nomArticle;
	private String description;
	private String categorie;
	private Enchere meilleureOffre;
	private int miseAPrix;
	private String dateFinEncheres;
	private Retrait retrait;
	private String pseudoVendeur;
	private String image;
	
	
}
