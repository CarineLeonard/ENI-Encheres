package fr.eni.encheres.bo;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="CATEGORIES") 
public @Data class Enchere {
	
	// TODO - si possible se passer de l'id rajouter et pk : noUtilisateur + noCategorie
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="no_enchere", nullable=false)
    private Long noEnchere;
	
	// lien FK entre les deux tables : c'est ici qu'on apelle un champ d'une autre table donc lien ici ! 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_utilisateur", nullable = false)
    private Utilisateur utilisateur;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_article", nullable = false)
    private ArticleVendu articleVendu;
    
	@Column(name="date_enchere", nullable=false)
    private LocalDate dateEnchere;
	
	@Column(name="montant_enchere", nullable=false)
    private int montantEnchere;
	

}
