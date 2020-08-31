package fr.eni.encheres.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.encheres.bo.UserRole;
import fr.eni.encheres.bo.Utilisateur;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

	// deleteById
	@Transactional
	void deleteByUtilisateur(Utilisateur utilisateur);
}
