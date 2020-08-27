package fr.eni.encheres.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.eni.encheres.bo.AppRole;
import fr.eni.encheres.dao.AppRoleRepository;
import fr.eni.encheres.dao.UserRoleRepository;

@Component
public class AppRoleManager {
	
	@Autowired
	private AppRoleRepository appRoleRepository;
	
	public AppRole ajouterAppRole(String roleName) throws Exception {
		AppRole appRole = null;
		try {
			appRole = new AppRole(roleName);
			appRole = appRoleRepository.save(appRole);
		} catch (Exception e) {
			throw e;
		}
		
		return appRole;
	}
	
	public List<AppRole> selectionnerAppRolesByPseudo(String pseudo) throws Exception {
		List<AppRole> listAppRole = appRoleRepository.findByPseudo(pseudo);
		return listAppRole;
	}
}
