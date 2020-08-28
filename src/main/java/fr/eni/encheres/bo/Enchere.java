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
@Table(name="ENCHERES") 
public @Data class Enchere {
	
	@EmbeddedId
	private EnchereId enchereId;
    
	@Column(name="date_enchere", nullable=false)
    private LocalDate dateEnchere;
	
	@Column(name="montant_enchere", nullable=false)
    private int montantEnchere;
	

}
