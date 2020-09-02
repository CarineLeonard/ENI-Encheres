package fr.eni.encheres.services;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;
import lombok.Data;

public @Data class EnchereForm {

	private ArticleVendu articleVendu;
	private @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dateEnchere;
	private int montantEnchere;
	private Utilisateur utilisateur; 
	 
}
