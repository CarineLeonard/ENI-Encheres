package fr.eni.encheres.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.eni.encheres.entity.Utilisateur;

//This is an Interface. No need Annotation here.
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long>{ // Long: Type of Utilisateur ID.
	
	Utilisateur findByNoUtilisateur (Long noUtilisateur); 
	
	List<Utilisateur> findByPseudoLike (String pseudo) ;
	
	@Query("SELECT coalesce(max(u.noUtilisateur), 0) FROM Utilisateur u")
    Long getMaxNoUtilisateur();
	

}
