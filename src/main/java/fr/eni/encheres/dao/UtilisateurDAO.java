package fr.eni.encheres.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.encheres.entity.Utilisateur;

// configure la classe comme un DAO du Spring + gère les transactions 
@Repository
@Transactional
public class UtilisateurDAO {
 
	// gestion de spring securiity / côté login 
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
    
    // gestion de spring validation / création d'utilisateur 
 // Config in WebSecurityConfig
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    private static final Map<Long, Utilisateur> USERS_MAP = new HashMap<>();
 
 
   // selectUserByPseudo
    public Utilisateur selectUserByPseudo(String pseudo) {
        Collection<Utilisateur> appUsers = USERS_MAP.values();
        Utilisateur unUtilisateur=null; 
        for (Utilisateur u : appUsers) {
            if (u.getPseudo().equals(pseudo)) {
            	unUtilisateur = u;
            } 
        }
        return unUtilisateur;
	    }
 
    // selectUserByEmail
    public Utilisateur selectUserByEmail(String email) {
        Collection<Utilisateur> appUsers = USERS_MAP.values();
        Utilisateur unUtilisateur=null; 
        for (Utilisateur u : appUsers) {
            if (u.getEmail().equals(email)) {
                unUtilisateur = u;
            }
        }
        return unUtilisateur;
    }
 
    // selectAllUsers() 
    public List<Utilisateur> selectAllUsers() {
        List<Utilisateur> list = new ArrayList<>();
        list.addAll(USERS_MAP.values());
        return list;
    }
 
    // insertUser(forl) 
    public Utilisateur insertUser(UtilisateurForm form) {
        String encrytedPassword = this.passwordEncoder.encode(form.getPassword());
 
        Utilisateur user = new Utilisateur(userId, form.getUserName(), //
                form.getFirstName(), form.getLastName(), false, //
                form.getGender(), form.getEmail(), form.getCountryCode(), //
                encrytedPassword);
 
        USERS_MAP.put(userId, user);
        return user;
    }
    
}
