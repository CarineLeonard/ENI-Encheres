package fr.eni.encheres.services;

import fr.eni.encheres.bo.ArticleVendu;
import lombok.Data;

public @Data class RetraitForm {

	private ArticleVendu articleVendu;
	private String rue;
	private String code_postal;
	private String ville;

	 
}
