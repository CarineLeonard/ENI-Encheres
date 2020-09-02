package fr.eni.encheres.bll;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.EnchereId;
import fr.eni.encheres.dao.EnchereRepository;
import fr.eni.encheres.services.EnchereForm;

@Component
public class EnchereManager {
	
	@Autowired
	EnchereRepository enchereRepository;

	public Enchere ajouterEnchere(EnchereId newEnchereId, EnchereForm enchereForm) {
		Enchere enchere = null;
		try {
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
