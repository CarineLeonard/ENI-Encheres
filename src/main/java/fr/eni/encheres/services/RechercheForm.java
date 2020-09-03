package fr.eni.encheres.services;

import fr.eni.encheres.bo.Categorie;
import lombok.Data;

public @Data class RechercheForm {

	 private Categorie categorie;
	 private String recherche;
	 
	 private boolean achats;
	 private boolean achatsOuvertes;
	 private boolean achatsEnCours;
	 private boolean achatsRemportees;
	 
	 private boolean ventes;
	 private boolean ventesEnCours;
	 private boolean ventesNonDebutees;
	 private boolean ventesTerminees;
}
