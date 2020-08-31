package fr.eni.encheres.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.eni.encheres.bo.UserRole;
import fr.eni.encheres.bo.Utilisateur;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

	List<UserRole> findByUtilisateur(Utilisateur utilisateur);

}
