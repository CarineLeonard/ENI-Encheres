package fr.eni.encheres.bo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Table(name = "UTILISATEURS", //
		uniqueConstraints = { //
				@UniqueConstraint(name = "utilisateur_uk", columnNames = "pseudo") })
public @Data class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Column(name = "mot_de_passe", length = 128, nullable = false)
	private String motDePasse;

	@Column(name = "credit", nullable = false)
	private int credit = 0;

	@Column(name = "actif", length = 1, nullable = false)
	private boolean actif = true;

	/**
	 * 
	 */
	public Utilisateur() {
	}

	/**
	 * @param noUtilisateur
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param code_postal
	 * @param ville
	 * @param motDePasse
	 * @param credit
	 * @param actif
	 */
	public Utilisateur(Long noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String code_postal, String ville, String motDePasse, int credit, boolean actif) {
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.actif = actif;
	}

}
