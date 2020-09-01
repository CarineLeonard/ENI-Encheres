package fr.eni.encheres.services;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import lombok.Data;

public @Data class ArticleVenduForm {
	
	//@Autowired
	//AppConfig appConfig;
	
	
	 private Long noArticle;
	 private String nomArticle;
	 private String description;
	 private @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateDebutEncheres;
	 private @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFinEncheres;
	 private int prixInital;
	 private Integer prixVente;
	 private Utilisateur utilisateur;
	 private Categorie categorie; 
	 private String rue;
	 private String code_postal;
	 private String ville;

	 // TODO - Udload de la photo : stockage où ? 
	 
}
