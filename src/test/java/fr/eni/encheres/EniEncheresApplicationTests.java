package fr.eni.encheres;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import fr.eni.encheres.dao.UtilisateurRepository;
import fr.eni.encheres.entity.Utilisateur;



@RunWith(SpringRunner.class)
@DataJpaTest
class EniEncheresApplicationTests {

	@Autowired
	private TestEntityManager entityManager; 
	
	@Autowired
	private UtilisateurRepository utilisateurRepository; 
	
	@Test
	void contextLoads() {
		}

	@Test 
	public void whenFindByPseudo_thenReturnPseudo() {
		
		// when
		Utilisateur found = utilisateurRepository.findByPseudo("dbuser") ; 
		
		// then
		assertThat(found.getPseudo()).isEqualTo("dbuser"); 
		
			}

	
	
	
	
	
}
