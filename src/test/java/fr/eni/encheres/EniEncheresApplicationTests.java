package fr.eni.encheres;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import fr.eni.encheres.controller.MainController;
import fr.eni.encheres.dao.UtilisateurRepository;



@RunWith(SpringRunner.class)
@SpringBootTest
class EniEncheresApplicationTests {
	
	@Autowired
	private UtilisateurRepository utilisateurrepo ; 
	
	@Test
	void contextLoads() {
			
		
		
	}


	
	
	
	
	
}
