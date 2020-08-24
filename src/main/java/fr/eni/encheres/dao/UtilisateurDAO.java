package fr.eni.encheres.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.encheres.entity.Utilisateur;

// configure la classe comme un DAO du Spring + gère les transactions 
@Repository
@Transactional
public class UtilisateurDAO {
 
	// injection 
    @Autowired
    private EntityManager entityManager;
 
    public Utilisateur findUserAccount(String pseudo) {
        try {
            String sql = "Select e from " + Utilisateur.class.getName() + " e " // pour obtenir le nom du tableau 
                    + " Where e.pseudo = :pseudo ";
 
            Query query = entityManager.createQuery(sql, Utilisateur.class);
            query.setParameter("pseudo", pseudo);
 
            return (Utilisateur) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
}
