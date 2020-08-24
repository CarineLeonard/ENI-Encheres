package fr.eni.encheres.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "User_Role", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "USER_ROLE_UK", columnNames = { "no_utilisateur", "Role_Id" }) })
public class UserRole {
 
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;
 
    // @ManyToOne à expliquer ! 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_utilisateur", nullable = false)
    private Utilisateur utilisateur;
 
    // @Joincolumn : pour faire appel à la colonne d'une autre classe (les fk!)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Role_Id", nullable = false)
    private AppRole appRole;
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
 
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
 
    public AppRole getAppRole() {
        return appRole;
    }
 
    public void setAppRole(AppRole appRole) {
        this.appRole = appRole;
    }

}
