package fr.eni.encheres.dao;



import org.springframework.data.repository.CrudRepository;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.RetraitId;

public interface RetraitRepository extends CrudRepository<Retrait, Long> {

	Retrait findByRetraitId(RetraitId retraitId);



	}
