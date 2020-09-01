package fr.eni.encheres.dao;

import org.springframework.data.repository.CrudRepository;

import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleVenduRepository extends CrudRepository<ArticleVendu, Long> {

	ArticleVendu findByNoArticle(Long noArticle);

}
