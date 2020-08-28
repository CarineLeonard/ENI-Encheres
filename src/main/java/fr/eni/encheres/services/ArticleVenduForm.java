package fr.eni.encheres.services;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import lombok.Data;

public @Data class ArticleVenduForm {
	
	 private Long noArticle;
	 private String nomArticle;
	 private String description;
	 private LocalDate  dateDebutEncheres;
	 private LocalDate dateFinEncheres;
	 private int prixInital;
	 private int prixVente;
	 private Utilisateur utilisateur;
	 private Categorie categorie;
	 
	 // TODO - Udload de la photo : stockage où ? 
	 
}
