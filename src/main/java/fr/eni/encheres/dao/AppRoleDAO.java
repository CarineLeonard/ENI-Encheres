package fr.eni.encheres.dao;

import java.util.List;
 
import javax.persistence.EntityManager;
import javax.persistence.Query;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.encheres.entity.UserRole;
 
// indique que c'est un Bean du Dao + qu'on peut faire des transactions 
@Repository
@Transactional
public class AppRoleDAO {
 
	// injecte l'entityManager 
    @Autowired
    private EntityManager entityManager;
 
    public List<String> getRoleNames(Long noUtilisateur) {
        String sql = "Select ur.appRole.roleName from " + UserRole.class.getName() + " ur " //
                + " where ur.utilisateur.noUtilisateur = :noUtilisateur ";
        
        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("noUtilisateur", noUtilisateur);
        return query.getResultList();
    }
 
}