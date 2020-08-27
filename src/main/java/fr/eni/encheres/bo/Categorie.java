package fr.eni.encheres.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="CATEGORIES") 
public @Data class Categorie { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="no_categorie", nullable=false)
    private Long noCategorie;
 
	@Column(name="libelle", length = 30, nullable=false)
    private String libelle;
	
}
