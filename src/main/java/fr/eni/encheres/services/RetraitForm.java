package fr.eni.encheres.services;

import fr.eni.encheres.bo.ArticleVendu;
import lombok.Data;

public @Data class RetraitForm {

	private Long noArticle;
	private String rue;
	private String code_postal;
	private String ville;
	private ArticleVendu articleVendu;
	 
}
