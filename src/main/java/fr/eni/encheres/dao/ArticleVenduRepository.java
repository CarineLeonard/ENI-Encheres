package fr.eni.encheres.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;

public interface ArticleVenduRepository extends CrudRepository<ArticleVendu, Long> {

	ArticleVendu findByNoArticle(Long noArticle);

	@Query("SELECT a FROM ArticleVendu a WHERE"
			+ " a.dateDebutEncheres <= CAST(current_date() AS LocalDate)"
			+ " AND a.dateFinEncheres > CAST(current_date() AS LocalDate)"
//			+ " AND a.categorie.noCategorie LIKE '?1'"
			+ " AND a.nomArticle LIKE '?2'"
			+ " ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findEncheresOuvertes(String categorie, String string);
	@Query("SELECT e.enchereId.articleVendu FROM Enchere e WHERE"
			+ " e.enchereId.articleVendu.dateDebutEncheres <= CAST(current_date() AS LocalDate)"
			+ " AND e.enchereId.articleVendu.dateFinEncheres > CAST(current_date() AS LocalDate)"
			+ " AND e.enchereId.utilisateur.noUtilisateur=?1"
			+ " AND e.enchereId.articleVendu.categorie.noCategorie LIKE '?2' AND e.enchereId.articleVendu.nomArticle LIKE '%?3%'"
			+ " ORDER BY e.enchereId.articleVendu.dateFinEncheres DESC")
	List<ArticleVendu> findMesEncheresEncours(Long noUtilisateur, String categorie, String string);
	@Query("SELECT e.enchereId.articleVendu FROM Enchere e WHERE"
			+ " e.enchereId.articleVendu.dateFinEncheres <= CAST(current_date() AS LocalDate)"
			+ " AND e.enchereId.utilisateur.noUtilisateur=?1"
			+ " AND e.montantEnchere=(SELECT MAX(ee.montantEnchere) FROM Enchere ee WHERE ee.enchereId.articleVendu=e.enchereId.articleVendu)"
			+ " AND e.enchereId.articleVendu.categorie.noCategorie LIKE '?2' AND e.enchereId.articleVendu.nomArticle LIKE '%?3%'"
			+ " ORDER BY e.enchereId.articleVendu.dateFinEncheres DESC")
	List<ArticleVendu> findMesEncheresRemportees(Long noUtilisateur, String categorie, String string);

	@Query("SELECT a FROM ArticleVendu a JOIN Enchere e WHERE"
			+ " (a.dateDebutEncheres <= CAST(current_date() AS LocalDate)"
			+ " AND a.dateFinEncheres > CAST(current_date() AS LocalDate))"
			+ " OR (e.enchereId.articleVendu.dateFinEncheres <= CAST(current_date() AS LocalDate)"
				+ " AND e.enchereId.utilisateur.noUtilisateur=?1"
				+ " AND e.montantEnchere=(SELECT MAX(ee.montantEnchere) FROM Enchere ee WHERE ee.enchereId.articleVendu=e.enchereId.articleVendu))"
			+ " AND a.categorie.noCategorie LIKE '?2' AND a.nomArticle LIKE '%?3%'"
			+ " ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findEncheresOuvertesMesEncheresRemportees(Long noUtilisateur, String categorie, String string);
	@Query("SELECT e.enchereId.articleVendu FROM Enchere e WHERE"
			+ " e.enchereId.articleVendu.dateDebutEncheres <= CAST(current_date() AS LocalDate)"
			+ " AND (e.enchereId.articleVendu.dateFinEncheres > CAST(current_date() AS LocalDate)"
				+ " OR e.montantEnchere=(SELECT MAX(ee.montantEnchere) FROM Enchere ee WHERE ee.enchereId.articleVendu=e.enchereId.articleVendu))"
			+ " AND e.enchereId.utilisateur.noUtilisateur=?1"
			+ " AND e.enchereId.articleVendu.categorie.noCategorie LIKE '?2' AND e.enchereId.articleVendu.nomArticle LIKE '%?3%'"
			+ " ORDER BY e.enchereId.articleVendu.dateFinEncheres DESC")
	List<ArticleVendu> findMesEncheresEncoursMesEncheresRemportees(Long noUtilisateur, String categorie, String string);

	@Query("SELECT a FROM ArticleVendu a WHERE"
			+ " a.dateDebutEncheres <= CAST(current_date() AS LocalDate)"
			+ " AND a.dateFinEncheres > CAST(current_date() AS LocalDate)"
			+ " AND a.utilisateur.noUtilisateur=?1"
			+ " AND a.categorie.noCategorie LIKE '?2' AND a.nomArticle LIKE '%?3%'"
			+ " ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findMesVentesEnCours(Long noUtilisateur, String categorie, String string);
	@Query("SELECT a FROM ArticleVendu a WHERE"
			+ " a.dateDebutEncheres > CAST(current_date() AS LocalDate)"
			+ " AND a.utilisateur.noUtilisateur=?1"
			+ " AND a.categorie.noCategorie LIKE '?2' AND a.nomArticle LIKE '%?3%'"
			+ " ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findMesVentesNonDebutees(Long noUtilisateur, String categorie, String string);
	@Query("SELECT a FROM ArticleVendu a WHERE"
			+ " a.dateFinEncheres <= CAST(current_date() AS LocalDate)"
			+ " AND a.utilisateur.noUtilisateur=?1"
			+ " AND a.categorie.noCategorie LIKE '?2' AND a.nomArticle LIKE '%?3%'"
			+ " ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findMesVentesTerminees(Long noUtilisateur, String categorie, String string);

	@Query("SELECT a FROM ArticleVendu a WHERE"
			+ " a.dateFinEncheres > CAST(current_date() AS LocalDate)"
			+ " AND a.utilisateur.noUtilisateur=?1"
			+ " AND a.categorie.noCategorie LIKE '?2' AND a.nomArticle LIKE '%?3%'"
			+ " ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findMesVentesEnCoursMesVentesNonDebutees(Long noUtilisateur, String categorie, String string);
	@Query("SELECT a FROM ArticleVendu a WHERE"
			+ " a.dateDebutEncheres <= CAST(current_date() AS LocalDate)"
			+ " AND a.utilisateur.noUtilisateur=?1"
			+ " AND a.categorie.noCategorie LIKE '?2' AND a.nomArticle LIKE '%?3%'"
			+ " ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findMesVentesEnCoursMesVentesTerminees(Long noUtilisateur, String categorie, String string);
	@Query("SELECT a FROM ArticleVendu a WHERE"
			+ " a.dateDebutEncheres > CAST(current_date() AS LocalDate)"
			+ " OR a.dateFinEncheres <= CAST(current_date() AS LocalDate)"
			+ " AND a.utilisateur.noUtilisateur=?1"
			+ " AND a.categorie.noCategorie LIKE '?2' AND a.nomArticle LIKE '%?3%'"
			+ " ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findMesVentesNonDebuteesMesVentesTerminees(Long noUtilisateur, String categorie, String string);
	@Query("SELECT a FROM ArticleVendu a WHERE"
			+ " a.utilisateur.noUtilisateur=?1"
			+ " AND a.categorie.noCategorie LIKE '?2' AND a.nomArticle LIKE '%?3%'"
			+ " ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findToutesMesVentes(Long noUtilisateur, String categorie, String string);
}
