package fr.eni.encheres.bll;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.EnchereId;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.EnchereRepository;
import fr.eni.encheres.services.EnchereForm;

@Component
public class EnchereManager {
	
	@Autowired
	EnchereRepository enchereRepository;
	
	@Autowired
	UtilisateurManager utilisateurManager;

	public Enchere ajouterEnchere(EnchereId newEnchereId, EnchereForm enchereForm) throws Exception {
		Enchere enchere = null;
		try {
			Enchere encherePrecedente = selectionnerMeilleureEnchere(newEnchereId.getArticleVendu().getNoArticle());
			if (encherePrecedente != null) {
				int montantPrecedent = encherePrecedente.getMontantEnchere();
				Utilisateur userPrecedent = encherePrecedente.getEnchereId().getUtilisateur();
				userPrecedent.setCredit(userPrecedent.getCredit() + montantPrecedent);
				utilisateurManager.updateUtilisateur(userPrecedent);
			}
			
			int montant = enchereForm.getMontantEnchere();
			Utilisateur nouvelUser = newEnchereId.getUtilisateur();
			nouvelUser.setCredit(nouvelUser.getCredit() - montant);
			utilisateurManager.updateUtilisateur(nouvelUser);
			
			enchere = new Enchere(newEnchereId, LocalDateTime.now(), enchereForm.getMontantEnchere());
			enchere = enchereRepository.save(enchere);
		} catch (Exception e) {
			throw e;
		}
		return enchere;
	}

	public Enchere selectionnerMeilleureEnchere(Long noArticle) {
		Enchere enchere = null;
		try {
			enchere = enchereRepository.findBestByNoArticle(noArticle);
		} catch (Exception e) {
			throw e;
		}
		return enchere;
	}

}
