package fr.eni.encheres.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.eni.encheres.bo.Enchere;

public interface EnchereRepository extends CrudRepository<Enchere, Long> {

	@Query("SELECT e FROM Enchere e WHERE e.dateEnchere = (SELECT MAX(e.dateEnchere) FROM Enchere e WHERE e.enchereId.articleVendu.noArticle=?1) AND e.enchereId.articleVendu.noArticle=?1")
	public Enchere findBestByNoArticle(Long noArticle);
}
