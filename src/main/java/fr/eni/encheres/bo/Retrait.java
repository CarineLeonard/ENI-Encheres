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

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Entity
@Table(name="RETRAIT") 
public @Data class Retrait implements Serializable {
	

	@NonNull
	@Id
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "no_article", nullable = false)
    private ArticleVendu articleVendu;
 
	@Column(name="rue", length = 30, nullable=false)
    private String rue;
	
	@Column(name="code_postal", length = 30, nullable=false)
    private String code_postal;
	
	@Column(name="ville", length = 30, nullable=false)
    private String ville;
	
}
