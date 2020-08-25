package fr.eni.encheres.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.eni.encheres.bo.UtilisateurBO;
import fr.eni.encheres.entity.Utilisateur;

//This is an Interface. No need Annotation here.
//Long: Type of Utilisateur ID.
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long>{ 
		
	// select by id
	Utilisateur findByNoUtilisateur (Long noUtilisateur); 
	
	//select by pseudo
	Utilisateur findByPseudo (String pseudo); 
	
	// selectAll
	private static final String SQL_ALL = "Select new " + UtilisateurBO.class.getName() + " FROM " + Utilisateur.class.getName() + "(u"
	
	@Query()
	Iterable<Utilisateur> findAll(); 
	
	// delete 
	void delete (Utilisateur utilisateur) ; 
	
	// deleteById 
	void deleteById (Long noUtilisateur) ; 
	
	// save : update et create  ----------------------- à revoir ! 
	<S extends Utilisateur> S save(S entity);

	// utilisateur de cet ID existe ? 
	boolean existsById (Long noUtilisateur);

	
	// nb total d'entité dans une table 
	long count(); 

	
	
	
	@Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);

	
	
}
