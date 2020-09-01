package fr.eni.encheres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.services.ArticleVenduForm;

@SpringBootApplication
public class EniEncheresApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(EniEncheresApplication.class, args);
	}

}
