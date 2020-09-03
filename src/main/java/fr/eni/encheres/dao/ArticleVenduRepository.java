package fr.eni.encheres.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleVenduRepository extends CrudRepository<ArticleVendu, Long> {

	ArticleVendu findByNoArticle(Long noArticle);

	@Query("SELECT a FROM ArticlesVendu a WHERE a.dateDebutEncheres >= CAST(NOW() AS DATE) AND a.dateFinEncheres < CAST(NOW() AS DATE) ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findEncheresOuvertes();
	@Query("SELECT e.enchereId.articleVendu FROM Encheres e WHERE e.enchereId.articleVendu.dateDebutEncheres >= CAST(NOW() AS DATE) AND e.enchereId.articleVendu.dateFinEncheres < CAST(NOW() AS DATE) AND e.utilisateur.noUtilisateur=?1 ORDER BY e.enchereId.articleVendu.dateFinEncheres DESC")
	List<ArticleVendu> findMesEncheresEncours(Long noUtilisateur);
	@Query("SELECT e.enchereId.articleVendu FROM Encheres e WHERE e.enchereId.articleVendu.dateFinEncheres >= CAST(NOW() AS DATE) AND e.utilisateur.noUtilisateur=?1 AND e.montantEnchere=(SELECT MAX(ee.montantEnchere) FROM Encheres ee WHERE ee.enchereId.articleVendu=e.enchereId.articleVendu) ORDER BY e.enchereId.articleVendu.dateFinEncheres DESC")
	List<ArticleVendu> findMesEncheresRemportees(Long noUtilisateur);

	@Query("SELECT a FROM ArticlesVendu a JOIN Encheres e WHERE (a.dateDebutEncheres >= CAST(NOW() AS DATE) AND a.dateFinEncheres < CAST(NOW() AS DATE)) OR (e.enchereId.articleVendu.dateFinEncheres >= CAST(NOW() AS DATE) AND e.utilisateur.noUtilisateur=?1 AND e.montantEnchere=(SELECT MAX(ee.montantEnchere) FROM Encheres ee WHERE ee.enchereId.articleVendu=e.enchereId.articleVendu)) ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findEncheresOuvertesMesEncheresRemportees(Long noUtilisateur);
	@Query("SELECT e.enchereId.articleVendu FROM Encheres e WHERE e.enchereId.articleVendu.dateDebutEncheres >= CAST(NOW() AS DATE) AND (e.enchereId.articleVendu.dateFinEncheres < CAST(NOW() AS DATE) OR e.montantEnchere=(SELECT MAX(ee.montantEnchere) FROM Encheres ee WHERE ee.enchereId.articleVendu=e.enchereId.articleVendu)) AND e.utilisateur.noUtilisateur=?1 ORDER BY e.enchereId.articleVendu.dateFinEncheres DESC")
	List<ArticleVendu> findMesEncheresEncoursMesEncheresRemportees(Long noUtilisateur);

	@Query("SELECT a FROM ArticlesVendu a WHERE a.dateDebutEncheres >= CAST(NOW() AS DATE) AND a.dateFinEncheres < CAST(NOW() AS DATE) AND a.utilisateur.noUtilisteur=?1 ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findMesVentesEnCours(Long noUtilisateur);
	@Query("SELECT a FROM ArticlesVendu a WHERE a.dateDebutEncheres < CAST(NOW() AS DATE) AND a.utilisateur.noUtilisteur=?1 ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findMesVentesNonDebutees(Long noUtilisateur);
	@Query("SELECT a FROM ArticlesVendu a WHERE a.dateFinEncheres >= CAST(NOW() AS DATE) AND a.utilisateur.noUtilisteur=?1 ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findMesVentesTerminees(Long noUtilisateur);

	@Query("SELECT a FROM ArticlesVendu a WHERE a.dateFinEncheres < CAST(NOW() AS DATE) AND a.utilisateur.noUtilisteur=?1 ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findMesVentesEnCoursMesVentesNonDebutees(Long noUtilisateur);
	@Query("SELECT a FROM ArticlesVendu a WHERE a.dateDebutEncheres >= CAST(NOW() AS DATE) AND a.utilisateur.noUtilisteur=?1 ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findMesVentesEnCoursMesVentesTerminees(Long noUtilisateur);
	@Query("SELECT a FROM ArticlesVendu a WHERE a.dateDebutEncheres < CAST(NOW() AS DATE) AND a.dateFinEncheres >= CAST(NOW() AS DATE) AND a.utilisateur.noUtilisteur=?1 ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findMesVentesNonDebuteesMesVentesTerminees(Long noUtilisateur);
	@Query("SELECT a FROM ArticlesVendu a WHERE a.utilisateur.noUtilisteur=?1 ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findToutesMesVentes(Long noUtilisateur);
}
