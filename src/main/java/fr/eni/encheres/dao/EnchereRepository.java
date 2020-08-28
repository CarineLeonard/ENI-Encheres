package fr.eni.encheres.dao;

import org.springframework.data.repository.CrudRepository;

import fr.eni.encheres.bo.Enchere;

public interface EnchereRepository extends CrudRepository<Enchere, Long> {

}
