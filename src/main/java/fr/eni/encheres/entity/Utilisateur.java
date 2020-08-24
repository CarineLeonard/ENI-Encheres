package fr.eni.encheres.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "UTILISATEURS", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "utilisateur_uk", columnNames = "pseudo") })
public class Utilisateur {
 
    @Id
    @GeneratedValue
    @Column(name = "no_utilisateur", nullable = false)
    private Long noUtilisateur;
 
    @Column(name = "pseudo", length = 30, nullable = false)
    private String pseudo;
 
    @Column(name = "nom", length = 30, nullable = false)
    private String nom;
    
    @Column(name = "prenom", length = 30, nullable = false)
    private String prenom;
    
    @Column(name = "email", length = 30, nullable = false)
    private String email;
    
    @Column(name = "telephone", length = 15, nullable = true)
    private String telephone;
    
    @Column(name = "rue", length = 50, nullable = false)
    private String rue;
    
    @Column(name = "code_postal", length = 30, nullable = false)
    private String code_postal;
    
    @Column(name = "ville", length = 30, nullable = false)
    private String ville;
    
    @Column(name = "mot_de_passe", length = 30, nullable = false)
    private String motDePasse;
    
    @Column(name = "credit", length = 30, nullable = false)
    private String credit;
 
    @Column(name = "actif", length = 1, nullable = false)
    private boolean actif;

    
    
}
