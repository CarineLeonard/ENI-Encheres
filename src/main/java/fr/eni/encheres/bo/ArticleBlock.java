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
	private int prix;
	private String dateFinEncheres;
	private String pseudoVendeur;
	private Base64 image;
	
	
}
