package fr.eni.encheres.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.encheres.entity.Utilisateur;

// configure la classe comme un DAO du Spring + gère les transactions 
@Repository
@Transactional
public class AppUserDAO {
 
	// injection 
    @Autowired
    private EntityManager entityManager;
 
    public Utilisateur findUserAccount(String userName) {
        try {
            String sql = "Select e from " + Utilisateur.class.getName() + " e " //
                    + " Where e.userName = :userName ";
 
            Query query = entityManager.createQuery(sql, Utilisateur.class);
            query.setParameter("userName", userName);
 
            return (Utilisateur) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
}
