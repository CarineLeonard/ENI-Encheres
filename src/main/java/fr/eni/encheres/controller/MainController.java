package fr.eni.encheres.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.eni.encheres.dao.UtilisateurRepository;
import fr.eni.encheres.entity.Utilisateur;
import fr.eni.encheres.form.UtilisateurForm;
import fr.eni.encheres.utils.WebUtils;
import fr.eni.encheres.validator.UtilisateurValidator;

// used to map web requests to Spring Controller methods.
@Controller
public class MainController {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository; 
	
	@Autowired
	  private UtilisateurValidator utilisateurValidator;
	
	 // Set a form validator
    @InitBinder
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
	 
	    @RequestMapping(value = "/logout", method = RequestMethod.GET)
	    public String logoutSuccessfulPage(Model model) {
	        model.addAttribute("title", "Logout");
	        return "logoutSuccessfulPage";
	    }
	 
	    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	    public String userInfo(Model model, Principal principal) {
	 
	        // After user login successfully.
	        String pseudo = principal.getName();
	 
	        System.out.println(" Pseudo : " + pseudo);
	 
	        User loginedUser = (User) ((Authentication) principal).getPrincipal();
	 
	        String userInfo = WebUtils.toString(loginedUser);
	        model.addAttribute("userInfo", userInfo);
	 
	        return "userInfoPage";
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
	          return "registerPage";
	       }
	       Utilisateur newUser= null;
	       try {
	          newUser = utilisateurRepository.save(utilisateurForm);
	          // newUser = appUserDAO.createAppUser(appUserForm);
	       }
	       // Other error!!
	       catch (Exception e) {
	          model.addAttribute("errorMessage", "Error: " + e.getMessage());
	          return "registerPage";
	       }
	  
	       redirectAttributes.addFlashAttribute("flashUser", newUser);
	        
	       return "redirect:/registerSuccessful";
	    }
	    
	    
	    
}
