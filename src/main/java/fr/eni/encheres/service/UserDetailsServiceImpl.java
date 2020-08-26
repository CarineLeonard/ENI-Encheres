package fr.eni.encheres.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.AppRoleRepository;
import fr.eni.encheres.dao.UtilisateurRepository;
 
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;
 
    // TODO - remplacer le speudo de la méthode par identifiant , là et donc là où on l'appelle 
    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        Utilisateur utilisateur = this.utilisateurRepository.findByPseudo(pseudo);
 
        // find by pseudo 
        
        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé !" + pseudo);
            throw new UsernameNotFoundException("Utilisateur  " + pseudo + " n'a pas été trouvé dans la base de données.");
        }
        
        // find by email 
        
        // un des deux doit etre bon  
 
        System.out.println("Utilisateur trouvé : " + utilisateur);
 
        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = this.appRoleRepository.findRoleNameByNoUtilisateur(utilisateur.getNoUtilisateur());
 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
 
        UserDetails userDetails = (UserDetails) new User(utilisateur.getPseudo(), //
                utilisateur.getMotDePasse(), grantList);
 
        return userDetails;
    }

}
