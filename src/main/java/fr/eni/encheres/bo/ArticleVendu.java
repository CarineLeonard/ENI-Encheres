package fr.eni.encheres.bo;

import java.time.LocalDate;

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

import lombok.Data;

@Entity
@Table(name="ARTICLES_VENDUS") 
public @Data class ArticleVendu { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="no_article", nullable=false)
    private Long noArticle;
	
	@Column(name="nom_article", length = 30, nullable=false)
    private Long nomArticle;
	
	@Column(name="description", length = 300, nullable=false)
    private Long description;
	
	@Column(name="date_debut_encheres", nullable=false)
    private LocalDate dateDebutEncheres;
	
	@Column(name="date_fin_encheres", nullable=false)
    private LocalDate dateFinEncheres;
	
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
