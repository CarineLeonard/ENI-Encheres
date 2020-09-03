package fr.eni.encheres.services;

import fr.eni.encheres.bo.Categorie;
import lombok.Data;

public @Data class RechercheForm {

	 private Categorie categorie;
	 private String recherche;
	 
	 private boolean radio;
	 
	 private boolean achatsOuvertes;
	 private boolean achatsEnCours;
	 private boolean achatsRemportees;
	 
	 private boolean ventesEnCours;
	 private boolean ventesNonDebutees;
	 private boolean ventesTerminees;
}
