package fr.eni.encheres.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.eni.encheres.bo.AppRole;

//This is an Interface. No need Annotation here.
//Long: Type of AppRole ID.
public interface AppRoleRepository extends CrudRepository<AppRole, Long> {

	//La plupart des requêtes n'ont pas encore été testées
	// select by roleId
	AppRole findByRoleId(Long roleId);
	
	// select by roleName
	AppRole findByRoleName(String roleName);
	
	// select roleNames by user id 			TESTE OK
	@Query("SELECT ur.appRole.roleName FROM UserRole ur WHERE ur.utilisateur.noUtilisateur = ?1")
	List<String> findRoleNameByNoUtilisateur(Long noUtilisateur);
	
	// select by user id
	@Query("SELECT ur.appRole FROM UserRole ur WHERE ur.utilisateur.noUtilisateur = ?1")
	List<AppRole> findByNoUtilisateur(Long noUtilisateur);

	// select by user pseudo
	@Query("SELECT ur.appRole FROM UserRole ur WHERE ur.utilisateur.pseudo = ?1")
	List<AppRole> findByPseudo(String pseudo);

	// selectAll
	List<AppRole> findAll();

	// delete
	void delete(AppRole appRole);

	// deleteById
	void deleteById(Long roleId);

	// save : update et create ----------------------- à revoir !
	<S extends AppRole> S save(S entity);

	// appRole de cet ID existe ?
	boolean existsById(Long roleId);

	// nb total d'entité dans une table
	long count();

}
