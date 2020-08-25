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

import fr.eni.encheres.bo.UtilisateurBO;
import fr.eni.encheres.entity.Utilisateur;

// configure la classe comme un DAO du Spring + gère les transactions 
@Repository
@Transactional
public class UtilisateurDAO {
	
	private static final String SQL_SelectByID = "";
	private static final String SQL_SelectByPseudo = ""; 
	private static final String SQL_SelectAll = ""; 
	private static final String SQL_Update = ""; 
	private static final String SQL_Insert = ""; 
	private static final String SQL_Delete = ""; 
	
	 // Config in WebSecurityConfig
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    private static final Map<Long, Utilisateur> USERS_MAP = new HashMap<>();
	
	// injection de entity manager
    @Autowired
    private EntityManager entityManager;
    
     
    /**
	 * Constructor empty 
	 */
	public UtilisateurDAO() {
	}

	// select by id (entity) 
	public Utilisateur findByNoUtilisateur (Long noUtilisateur) {
		return this.entityManager.find(Utilisateur.class, noUtilisateur); 
	}
	
	// select all (utilisateurs) 
	public List<UtilisateurBO> listUtilisateurBO() {
		String sql = "Select new " + UtilisateurBO.class.getName() + "(u.noUtilisateur, u. ....) from " + Utilisateur.class.getName() + " u "; 
		Query query = entityManager.createQuery(sql, UtilisateurBO.class);
		return query.getResultList(); 
	}
	
	// select by pseudo 
	public Utilisateur findByPseudo (String pseudo) {
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
 
	// gestion de spring security / côté login 
    public Utilisateur findUserAccount(String pseudo) {
        try {
            String sql = "Select u from " + Utilisateur.class.getName() + " u " // pour obtenir le nom du tableau 
                    + " Where u.pseudo = :pseudo ";
 
            Query query = entityManager.createQuery(sql, Utilisateur.class);
            query.setParameter("pseudo", pseudo);
 
            return (Utilisateur) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
	// insert (utilisateur) 
	public void addUtilisateur(Utilisateur utilisateur) {
		
		
		
	}
	
     
    // insertUser(form) 
    public Utilisateur insertUser(UtilisateurForm form) {
        String encrytedPassword = this.passwordEncoder.encode(form.getPassword());
 
        Utilisateur user = new Utilisateur(userId, form.getUserName(), //
                form.getFirstName(), form.getLastName(), false, //
                form.getGender(), form.getEmail(), form.getCountryCode(), //
                encrytedPassword);
 
        USERS_MAP.put(userId, user);
        return user;
    }
    
	
	// update 
	
	
	// delete 
    
    
    
}
