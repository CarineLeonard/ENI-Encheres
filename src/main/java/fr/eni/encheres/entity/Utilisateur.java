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
    
    @Column(name = "credit", nullable = false)
    private int credit;
 
    @Column(name = "actif", length = 1, nullable = false)
    private boolean actif;

	public Long getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(Long noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}
   
    
}
