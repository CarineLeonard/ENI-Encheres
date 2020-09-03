package fr.eni.encheres.services;

import lombok.Data;

public @Data class RechercheForm {

	 private Long categorie;
	 private String recherche;
	 
	 private boolean radio;
	 
	 private boolean achatsOuvertes;
	 private boolean achatsEnCours;
	 private boolean achatsRemportees;
	 
	 private boolean ventesEnCours;
	 private boolean ventesNonDebutees;
	 private boolean ventesTerminees;
}
