package fr.eni.encheres.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.UserDetailsServiceImpl;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.CategorieRepository;
import fr.eni.encheres.dao.UtilisateurRepository;
import fr.eni.encheres.services.ArticleVenduForm;
import fr.eni.encheres.services.UtilisateurForm;
import fr.eni.encheres.services.WebUtils;

// used to map web requests to Spring Controller methods.
@Controller
public class MainController {

	@Autowired
	private UtilisateurManager utilisateurManager;
	
	@Autowired
	private ArticleVenduManager articleVenduManager; 
	
	@Autowired
	private CategorieManager categorieManager; 
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	@Autowired
	private UtilisateurValidator utilisateurValidator;
	
	@Autowired
	private UtilisateurEditValidator utilisateurEditValidator;
	
	@Autowired
	private ArticleVenduValidator articleVenduValidator; 
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	 // Set a form validator
    @InitBinder("utilisateurForm")
    protected void initBinder(WebDataBinder dataBinder) {
       // Form target
       Object target = dataBinder.getTarget();
       if (target == null) {
          return;
       }
       System.out.println("Target=" + target);
  
       if (target.getClass() == UtilisateurForm.class) {
          dataBinder.setValidator(utilisateurValidator);
       }
       // ...
    }
    
    // répartition des accès au pages avec web security 
	 @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	    public String welcomePage(Model model) {
	        model.addAttribute("title_welcome", "Accueil");
	        model.addAttribute("titre_welcome", "Liste des enchères");
	        return "welcomePage";
	    }
	 
	    @RequestMapping(value = "/admin", method = RequestMethod.GET)
	    public String adminPage(Model model, Principal principal) {
	         
	        User loginedUser = (User) ((Authentication) principal).getPrincipal();
	 
	        String userInfo = WebUtils.toString(loginedUser);
	        model.addAttribute("userInfo", userInfo);
	         
	        return "adminPage";
	    }
	 
	    @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public String loginPage(Model model) {
	        model.addAttribute("title_login", "Se connecter");
	        model.addAttribute("titre_login", "Se connecter");
	        return "loginPage";
	    }
	 
	    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	    public String logoutSuccessfulPage(Model model) {
	        model.addAttribute("title_logout", "Déconnexion");
	        model.addAttribute("titre_logout", "Vous êtes bien déconnecté !");
	        return "logoutSuccessfulPage";
	    }
	 
	    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	    public String userInfo(@RequestParam(value = "pseudo", defaultValue= "") String pseudo, Model model, Principal principal) {
			model.addAttribute("title_userInfo", "Profil");
			
	    	if (pseudo.equals("")) {
	    		pseudo = principal.getName();
	    	}
	    	Utilisateur user = utilisateurRepository.findByPseudo(pseudo);
	        model.addAttribute("userPseudo", user.getPseudo());
	        model.addAttribute("userNom", user.getNom());
	        model.addAttribute("userPrenom", user.getPrenom());
	        model.addAttribute("userEmail", user.getEmail());
	        model.addAttribute("userTelephone", user.getTelephone());
	        model.addAttribute("userRue", user.getRue());
	        model.addAttribute("userCodePostal", user.getCode_postal());
	        model.addAttribute("userVille", user.getVille());
	        
	        return "userInfoPage";
	    }
	 
	    @RequestMapping(value = "/editInfo", method = RequestMethod.GET)
	    public String editInfo(Model model, Principal principal) {
	    	String pseudo = principal.getName();
	    	Utilisateur user = utilisateurRepository.findByPseudo(pseudo);
	        model.addAttribute("userPseudo", user.getPseudo());
	        model.addAttribute("userNom", user.getNom());
	        model.addAttribute("userPrenom", user.getPrenom());
	        model.addAttribute("userEmail", user.getEmail());
	        model.addAttribute("userTelephone", user.getTelephone());
	        model.addAttribute("userRue", user.getRue());
	        model.addAttribute("userCodePostal", user.getCode_postal());
	        model.addAttribute("userVille", user.getVille());
	        model.addAttribute("userCredits", user.getCredit());

			UtilisateurForm form = new UtilisateurForm();
			model.addAttribute("title_editInfo", "Modifier mon compte");
			model.addAttribute("titre_editInfo", "Modifier mon compte");
			model.addAttribute("utilisateurForm", form);
				        
	        return "editInfoPage";
	    }
	    
		@RequestMapping(value = "/editInfo", method = RequestMethod.POST)
		public String editInfo(Model model, Principal principal, //
				@ModelAttribute("utilisateurForm") UtilisateurForm utilisateurForm, //
				BindingResult result, //
				final RedirectAttributes redirectAttributes) {
			
			try {
				Utilisateur currentUser = utilisateurManager.selectionnerUtilisateur(principal.getName());
				utilisateurForm.setNoUtilisateur(currentUser.getNoUtilisateur());
				
			    utilisateurEditValidator.validate(utilisateurForm, result);
			} catch (Exception e) {
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
				model.addAttribute("title_editInfo", "Modifier mon compte");
				model.addAttribute("titre_editInfo", "Modifier mon compte");
				return "editInfoPage";
			}
			
			if (result.hasErrors()) {
				model.addAttribute("title_editInfo", "Modifier mon compte");
				model.addAttribute("titre_editInfo", "Modifier mon compte");
				return "editInfoPage";
			}
			
			Utilisateur newUser = null;
			
			
			try {
				newUser = utilisateurManager.updateUtilisateur(utilisateurForm);
			} catch (Exception e) {
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
				model.addAttribute("title_editInfo", "Modifier mon compte");
				model.addAttribute("titre_editInfo", "Modifier mon compte");
				return "editInfoPage";
			}
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetailsServiceImpl.loadUserByUsername(newUser.getPseudo()), newUser.getMotDePasse(), updatedAuthorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			redirectAttributes.addFlashAttribute("flashUser", newUser);
			
			return "redirect:/userInfo";
		}
	 
	    @RequestMapping(value = "/deleteAccount", method = RequestMethod.GET)
	    public String deleteAccount(Model model, Principal principal, //
				final RedirectAttributes redirectAttributes) {
			try {
		    	String pseudo = principal.getName();
		    	Utilisateur user = utilisateurRepository.findByPseudo(pseudo);
		    	utilisateurRepository.deleteById(user.getNoUtilisateur());
			} catch (Exception e) {
				System.out.println(e);
				redirectAttributes.addFlashAttribute("errorMessage", "Error: " + e.getMessage());
				return "redirect:/editInfo";
			}

			return "redirect:/logout";
	    }
	 
	    @RequestMapping(value = "/403", method = RequestMethod.GET)
	    public String accessDenied(Model model, Principal principal) {
	 
	        if (principal != null) {
	            User loginedUser = (User) ((Authentication) principal).getPrincipal();
	 
	            String userInfo = WebUtils.toString(loginedUser);
	 
	            model.addAttribute("userInfo", userInfo);
	 
	            String message = "Salut " + principal.getName() //
	                    + "<br> vous n'avez pas la premission d'accéder à cette page!";
	            model.addAttribute("message", message);
	 
	        }
	 
	        return "403Page";
	    }
	    
	    // Show Register page.
	    @RequestMapping(value = "/register", method = RequestMethod.GET)
	    public String viewRegister(Model model) {
	  
	       UtilisateurForm form = new UtilisateurForm();
	        model.addAttribute("title_register", "Créer un compte");
	        model.addAttribute("titre_register", "Créer un compte");
	       model.addAttribute("utilisateurForm", form);
	  
	       return "registerPage";
	    }
	    
	    // This method is called to save the registration information.
	    // @Validated: To ensure that this Form
	    // has been Validated before this method is invoked.
	    @RequestMapping(value = "/register", method = RequestMethod.POST)
	    public String saveRegister(Model model, //
	          @ModelAttribute("utilisateurForm") @Validated UtilisateurForm utilisateurForm, //
	          BindingResult result, //
	          final RedirectAttributes redirectAttributes) {
	  
	       // Validate result
	       if (result.hasErrors()) {
		        model.addAttribute("title_register", "Créer un compte");
		        model.addAttribute("titre_register", "Créer un compte");
	          return "registerPage";
	       }
//	       Utilisateur newUser = new Utilisateur(utilisateurForm.getNoUtilisateur(), utilisateurForm.getPseudo(), utilisateurForm.getNom(), utilisateurForm.getPrenom(), utilisateurForm.getEmail(), utilisateurForm.getTelephone(), utilisateurForm.getRue(), utilisateurForm.getCode_postal(), utilisateurForm.getVille(), utilisateurForm.getMotDePasse(), utilisateurForm.getCredit(), utilisateurForm.isActif());
	       // TODO - user role à paramétrer - si on veut améliorer : mapper (+tard)
	       Utilisateur newUser = null;
	       try {
//	          newUser = utilisateurRepository.save(newUser);
	          // newUser = appUserDAO.createAppUser(appUserForm);
	    	   newUser = utilisateurManager.ajouterUtilisateur(utilisateurForm);
	       }
	       // Other error!!
	       catch (Exception e) {
	          model.addAttribute("errorMessage", "Error: " + e.getMessage());
		        model.addAttribute("title_register", "Créer un compte");
		        model.addAttribute("titre_register", "Créer un compte");
	          return "registerPage";
	       }
	  
	       redirectAttributes.addFlashAttribute("flashUser", newUser);
	        
	       return "redirect:/registerSuccessfull";
	    }
	    
	    @RequestMapping("/registerSuccessfull")
	    public String viewRegisterSuccessful(Model model) {
	        model.addAttribute("title_registerSuccessfull", "Compte créé");
	        model.addAttribute("titre_registerSuccessfull", "Compte créé avec succès !");
	       return "registerSuccessfullPage";
	    }
	    
	    @RequestMapping(value = "/newSale", method = RequestMethod.GET)
	    public String newSale(Model model, Principal principal) {
	    	String pseudo = principal.getName();
	    	Utilisateur user = utilisateurRepository.findByPseudo(pseudo);

			ArticleVenduForm form = new ArticleVenduForm();
			model.addAttribute("title_newSale", "Nouvelle vente");
			model.addAttribute("titre_newSale", "Nouvelle vente");
			model.addAttribute("articleForm", form);
			Iterable<Categorie> list = categorieRepository.findAll(); 
			model.addAttribute("categories", list); 	        
	        return "newSalePage";
	    }
	    
		@RequestMapping(value = "/newSale", method = RequestMethod.POST)
		public String saveNewSale(Model model, Principal principal, //
				@ModelAttribute("articleForm") ArticleVenduForm articleForm, //
				BindingResult result, //
				final RedirectAttributes redirectAttributes) {
			
			try {
				Utilisateur currentUser = utilisateurManager.selectionnerUtilisateur(principal.getName());
				articleForm.setUtilisateur(currentUser);
				
				//articleForm.setDateDebutEncheres(new SimpleDateFormat("dd/MM/yyyy").parse(""));
				//articleForm.setDateFinEncheres(new SimpleDateFormat("dd/MM/yyyy").parse(""));
				
				Categorie currentCategorie = (Categorie) model.getAttribute("categories") ; 
				 // currentCategorie = categorieManager.selectionnerCategorie(libelle);
				articleForm.setCategorie(currentCategorie);
				
				articleVenduValidator.validate(articleForm, result);
			} catch (Exception e) {
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
				model.addAttribute("title_newSale", "Nouvelle vente");
				model.addAttribute("titre_newSale", "Nouvelle vente");
				Iterable<Categorie> list = categorieRepository.findAll(); 
				model.addAttribute("categories", list); 
				return "newSalePage";
			}
			
			if (result.hasErrors()) {
				Iterable<Categorie> list = categorieRepository.findAll(); 
				model.addAttribute("categories", list); 
				model.addAttribute("title_newSale", "Nouvelle vente");
				model.addAttribute("titre_newSale", "Nouvelle vente");
				return "newSalePage";
			}
			
			ArticleVendu newArticle = null;
			
			try {
				newArticle = articleVenduManager.ajouterArticleVendu(articleForm);
			} catch (Exception e) {
				model.addAttribute("title_newSale", "Nouvelle vente");
				model.addAttribute("titre_newSale", "Nouvelle vente");
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
				Iterable<Categorie> list = categorieRepository.findAll(); 
				model.addAttribute("categories", list);  
				return "newSalePage";
			}

			redirectAttributes.addFlashAttribute("flashUser", newArticle);
			
			return "redirect:/welcome";
		}
	    
	    
	    
	    
}
