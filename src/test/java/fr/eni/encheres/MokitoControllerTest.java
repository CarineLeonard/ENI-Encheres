package fr.eni.encheres;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.eni.encheres.dao.UtilisateurRepository;
import fr.eni.encheres.entity.Utilisateur;

public class MokitoControllerTest {
	
	
	@InjectMocks
	private UserControllerTest userController; 
	
	@Mock
	private UtilisateurRepository userRepository; 
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test 
	public void testFindById() {
		Utilisateur u = new Utilisateur(); 
		u.setNoUtilisateur(1l);
		// when(userRepository.findById(1l)).thenReturn(u);				// bug
		
		Utilisateur user = userController.get(1L);
		verify(userRepository).findById(1l);
		
		assertEquals(1l, user.getNoUtilisateur().longValue());
	}
	// https://stackabuse.com/how-to-test-a-spring-boot-application/ 
}
