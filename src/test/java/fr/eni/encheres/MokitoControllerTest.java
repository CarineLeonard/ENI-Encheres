package fr.eni.encheres;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.controller.rest.UtilisateurController;
import fr.eni.encheres.dao.UtilisateurRepository;

public class MokitoControllerTest {
	
	
	@InjectMocks
	private UtilisateurController userController;
	
	@Mock
	private UtilisateurRepository userRepository; 
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test 
	public void testFindByPseudo() {
//		Utilisateur u = Mockito.mock(Utilisateur.class); 
//		u.setPseudo("dbuser");
//		when(this.userRepository.findByPseudo("dbuser")).thenReturn(u);				// bug
//		
//		Utilisateur user = userController.getUser("dbuser");
//		verify(this.userRepository).findByPseudo("dbuser");
//		
//		assertEquals("dbuser", user.getPseudo());
	}
	// https://stackabuse.com/how-to-test-a-spring-boot-application/ 
}
