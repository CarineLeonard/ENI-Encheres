package fr.eni.encheres.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

// déclare que c'est une classe Entity de JPA : la "copie" de notre BDD
// rappel : nous sommes en update auto 
@Entity
@Table(name = "APP_ROLE", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "APP_ROLE_UK", columnNames = "Role_Name") })
public class AppRole {
    
	//indicaion de la génération de l'ID automatique
    @Id
    @GeneratedValue				
    @Column(name = "Role_Id", nullable = false)
    private Long roleId;
    
    // @Colum : le nom et les attriubts de la colonnes 
    @Column(name = "Role_Name", length = 30, nullable = false)
    private String roleName;
 
    // la liste dees getters et setters 
    public Long getRoleId() {
        return roleId;
    }
 
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
 
    public String getRoleName() {
        return roleName;
    }
 
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
     
}
