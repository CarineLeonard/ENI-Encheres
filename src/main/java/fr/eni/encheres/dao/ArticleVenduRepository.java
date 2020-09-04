package fr.eni.encheres.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleVenduRepository extends CrudRepository<ArticleVendu, Long> {

	ArticleVendu findByNoArticle(Long noArticle);

	@Query("SELECT a FROM ArticleVendu a WHERE"
			+ " a.dateDebutEncheres <= CAST(current_date() AS LocalDate)"
			+ " AND a.dateFinEncheres > CAST(current_date() AS LocalDate)"
			+ " AND (:categorie IS null OR a.categorie.noCategorie=:categorie)"
			+ " AND (:string IS null OR a.nomArticle LIKE %:string%)"
			+ " ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findEncheresOuvertes(@Param("categorie") Long categorie, @Param("string") String string);
	@Query("SELECT e.enchereId.articleVendu FROM Enchere e WHERE"
			+ " e.enchereId.articleVendu.dateDebutEncheres <= CAST(current_date() AS LocalDate)"
			+ " AND e.enchereId.articleVendu.dateFinEncheres > CAST(current_date() AS LocalDate)"
			+ " AND (:noUtilisateur IS null OR e.enchereId.utilisateur.noUtilisateur=:noUtilisateur)"
			+ " AND (:categorie IS null OR e.enchereId.articleVendu.categorie.noCategorie=:categorie)"
			+ " AND (:string IS null OR e.enchereId.articleVendu.nomArticle LIKE %:string%)"
			+ " ORDER BY e.enchereId.articleVendu.dateFinEncheres DESC")
	List<ArticleVendu> findMesEncheresEncours(@Param("noUtilisateur") Long noUtilisateur, @Param("categorie") Long categorie, @Param("string") String string);
	@Query("SELECT e.enchereId.articleVendu FROM Enchere e WHERE"
			+ " e.enchereId.articleVendu.dateFinEncheres <= CAST(current_date() AS LocalDate)"
			+ " AND (:noUtilisateur IS null OR e.enchereId.utilisateur.noUtilisateur=:noUtilisateur)"
			+ " AND e.montantEnchere=(SELECT MAX(ee.montantEnchere) FROM Enchere ee WHERE ee.enchereId.articleVendu=e.enchereId.articleVendu)"
			+ " AND (:categorie IS null OR e.enchereId.articleVendu.categorie.noCategorie=:categorie)"
			+ " AND (:string IS null OR e.enchereId.articleVendu.nomArticle LIKE %:string%)"
			+ " ORDER BY e.enchereId.articleVendu.dateFinEncheres DESC")
	List<ArticleVendu> findMesEncheresRemportees(@Param("noUtilisateur") Long noUtilisateur, @Param("categorie") Long categorie, @Param("string") String string);

	@Query("SELECT a FROM ArticleVendu a JOIN Enchere e WHERE"
			+ " (a.dateDebutEncheres <= CAST(current_date() AS LocalDate)"
			+ " AND a.dateFinEncheres > CAST(current_date() AS LocalDate))"
			+ " OR (e.enchereId.articleVendu.dateFinEncheres <= CAST(current_date() AS LocalDate)"
				+ " AND (:noUtilisateur IS null OR e.enchereId.utilisateur.noUtilisateur=:noUtilisateur)"
				+ " AND e.montantEnchere=(SELECT MAX(ee.montantEnchere) FROM Enchere ee WHERE ee.enchereId.articleVendu=e.enchereId.articleVendu))"
			+ " AND (:categorie IS null OR a.categorie.noCategorie=:categorie)"
			+ " AND (:string IS null OR a.nomArticle LIKE %:string%)"
			+ " ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findEncheresOuvertesMesEncheresRemportees(@Param("noUtilisateur") Long noUtilisateur, @Param("categorie") Long categorie, @Param("string") String string);
	@Query("SELECT e.enchereId.articleVendu FROM Enchere e WHERE"
			+ " e.enchereId.articleVendu.dateDebutEncheres <= CAST(current_date() AS LocalDate)"
			+ " AND (e.enchereId.articleVendu.dateFinEncheres > CAST(current_date() AS LocalDate)"
				+ " OR e.montantEnchere=(SELECT MAX(ee.montantEnchere) FROM Enchere ee WHERE ee.enchereId.articleVendu=e.enchereId.articleVendu))"
				+ " AND (:noUtilisateur IS null OR e.enchereId.utilisateur.noUtilisateur=:noUtilisateur)"
			+ " AND (:categorie IS null OR e.enchereId.articleVendu.categorie.noCategorie=:categorie)"
			+ " AND (:string IS null OR e.enchereId.articleVendu.nomArticle LIKE %:string%)"
			+ " ORDER BY e.enchereId.articleVendu.dateFinEncheres DESC")
	List<ArticleVendu> findMesEncheresEncoursMesEncheresRemportees(@Param("noUtilisateur") Long noUtilisateur, @Param("categorie") Long categorie, @Param("string") String string);

	@Query("SELECT a FROM ArticleVendu a WHERE"
			+ " a.dateDebutEncheres <= CAST(current_date() AS LocalDate)"
			+ " AND a.dateFinEncheres > CAST(current_date() AS LocalDate)"
			+ " AND (:noUtilisateur IS null OR a.utilisateur.noUtilisateur=:noUtilisateur)"
			+ " AND (:categorie IS null OR a.categorie.noCategorie=:categorie)"
			+ " AND (:string IS null OR a.nomArticle LIKE %:string%)"
			+ " ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findMesVentesEnCours(@Param("noUtilisateur") Long noUtilisateur, @Param("categorie") Long categorie, @Param("string") String string);
	@Query("SELECT a FROM ArticleVendu a WHERE"
			+ " a.dateDebutEncheres > CAST(current_date() AS LocalDate)"
			+ " AND (:noUtilisateur IS null OR a.utilisateur.noUtilisateur=:noUtilisateur)"
			+ " AND (:categorie IS null OR a.categorie.noCategorie=:categorie)"
			+ " AND (:string IS null OR a.nomArticle LIKE %:string%)"
			+ " ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findMesVentesNonDebutees(@Param("noUtilisateur") Long noUtilisateur, @Param("categorie") Long categorie, @Param("string") String string);
	@Query("SELECT a FROM ArticleVendu a WHERE"
			+ " a.dateFinEncheres <= CAST(current_date() AS LocalDate)"
			+ " AND (:noUtilisateur IS null OR a.utilisateur.noUtilisateur=:noUtilisateur)"
			+ " AND (:categorie IS null OR a.categorie.noCategorie=:categorie)"
			+ " AND (:string IS null OR a.nomArticle LIKE %:string%)"
			+ " ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findMesVentesTerminees(@Param("noUtilisateur") Long noUtilisateur, @Param("categorie") Long categorie, @Param("string") String string);

	@Query("SELECT a FROM ArticleVendu a WHERE"
			+ " a.dateFinEncheres > CAST(current_date() AS LocalDate)"
			+ " AND (:noUtilisateur IS null OR a.utilisateur.noUtilisateur=:noUtilisateur)"
			+ " AND (:categorie IS null OR a.categorie.noCategorie=:categorie)"
			+ " AND (:string IS null OR a.nomArticle LIKE %:string%)"
			+ " ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findMesVentesEnCoursMesVentesNonDebutees(@Param("noUtilisateur") Long noUtilisateur, @Param("categorie") Long categorie, @Param("string") String string);
	@Query("SELECT a FROM ArticleVendu a WHERE"
			+ " a.dateDebutEncheres <= CAST(current_date() AS LocalDate)"
			+ " AND (:noUtilisateur IS null OR a.utilisateur.noUtilisateur=:noUtilisateur)"
			+ " AND (:categorie IS null OR a.categorie.noCategorie=:categorie)"
			+ " AND (:string IS null OR a.nomArticle LIKE %:string%)"
			+ " ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findMesVentesEnCoursMesVentesTerminees(@Param("noUtilisateur") Long noUtilisateur, @Param("categorie") Long categorie, @Param("string") String string);
	@Query("SELECT a FROM ArticleVendu a WHERE"
			+ " a.dateDebutEncheres > CAST(current_date() AS LocalDate)"
			+ " OR a.dateFinEncheres <= CAST(current_date() AS LocalDate)"
			+ " AND (:noUtilisateur IS null OR a.utilisateur.noUtilisateur=:noUtilisateur)"
			+ " AND (:categorie IS null OR a.categorie.noCategorie=:categorie)"
			+ " AND (:string IS null OR a.nomArticle LIKE %:string%)"
			+ " ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findMesVentesNonDebuteesMesVentesTerminees(@Param("noUtilisateur") Long noUtilisateur, @Param("categorie") Long categorie, @Param("string") String string);
	@Query("SELECT a FROM ArticleVendu a WHERE"
			+ " (:noUtilisateur IS null OR a.utilisateur.noUtilisateur=:noUtilisateur)"
			+ " AND (:categorie IS null OR a.categorie.noCategorie=:categorie)"
			+ " AND (:string IS null OR a.nomArticle LIKE %:string%)"
			+ " ORDER BY a.dateFinEncheres DESC")
	List<ArticleVendu> findToutesMesVentes(@Param("noUtilisateur") Long noUtilisateur, @Param("categorie") Long categorie, @Param("string") String string);
}
