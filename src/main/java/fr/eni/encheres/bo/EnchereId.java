package fr.eni.encheres.bo;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class EnchereId implements Serializable{
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_utilisateur", nullable = false)
    private Utilisateur utilisateur;
    
	@ManyToOne(fetch = FetchType.LAZY)			// à lire :  many enchères - one article 
    @JoinColumn(name = "no_article", nullable = false)
    private ArticleVendu articleVendu;
	
}
