package fr.eni.encheres.bo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="RETRAITS") 
public @Data class Retrait implements Serializable {
	

	@EmbeddedId
	private RetraitId retraitId;
	 
	@Column(name="rue", length = 50, nullable=false)
    private String rue;
	
	@Column(name="code_postal", length = 30, nullable=false)
    private String code_postal;
	
	@Column(name="ville", length = 30, nullable=false)
    private String ville;
	
}
