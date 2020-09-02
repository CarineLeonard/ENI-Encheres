package fr.eni.encheres.bo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ENCHERES") 
public @Data class Enchere {
	
	@EmbeddedId
	private EnchereId enchereId;
    
	@Column(name="date_enchere", nullable=false)
    private LocalDateTime dateEnchere;
	
	@Column(name="montant_enchere", nullable=false)
    private int montantEnchere;
	

}
