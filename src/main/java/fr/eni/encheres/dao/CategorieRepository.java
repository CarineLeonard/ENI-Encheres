package fr.eni.encheres.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.eni.encheres.bo.Categorie;


public interface CategorieRepository extends CrudRepository<Categorie, Long> {
	
	// select by id : liste 
	List<Categorie> findByNoCategorie (Long noCategorie);

	// select by libelle 
	Categorie findByLibelle(String libelle); 
	
}
