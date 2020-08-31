package fr.eni.encheres.bo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ARTICLES_VENDUS") 
public @Data class ArticleVendu { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="no_article", nullable=false)
    private Long noArticle;
	
	@Column(name="nom_article", length = 30, nullable=false)
    private String nomArticle;
	
	@Column(name="description", length = 300, nullable=false)
    private String description;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_debut_encheres", nullable=false)
    private Date dateDebutEncheres;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_fin_encheres", nullable=false)
    private Date dateFinEncheres;
	
	@Column(name="prix_initial", nullable=true)
    private int prixInital;
	
	@Column(name="prix_vente", nullable=true)
    private int prixVente;
	
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "no_utilisateur", nullable = false)
    private Utilisateur utilisateur;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "no_categorie", nullable = false)
    private Categorie categorie;	
	
}
