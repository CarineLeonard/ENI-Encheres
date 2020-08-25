package fr.eni.encheres.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.eni.encheres.dao.UtilisateurRepository;
import fr.eni.encheres.utils.WebUtils;

// used to map web requests to Spring Controller methods.
@Controller
public class MainController {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository; 
	
	 @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	    public String welcomePage(Model model) {
	        model.addAttribute("title", "Welcome");
	        model.addAttribute("message", "This is welcome page!");
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
	 
	        return "loginPage";
	    }
	 
	    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
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
}
