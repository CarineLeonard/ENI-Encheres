package fr.eni.encheres.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import fr.eni.encheres.bll.AppRoleManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.AppRole;
import fr.eni.encheres.bo.Utilisateur;

public class MainControllerInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UtilisateurManager utilisateurManager;
	
	@Autowired
	private AppRoleManager appRoleManager;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView) throws Exception {
		Principal principal = req.getUserPrincipal();
		
		Boolean userIsAdmin = (Boolean) req.getSession().getAttribute("userIsAdmin");
		Boolean userIsUser = (Boolean) req.getSession().getAttribute("userIsAdmin");
		
		if ((userIsAdmin == null || userIsUser == null) && modelAndView != null && principal != null) {
			req.getSession().setAttribute("userIsAdmin", false);
			req.getSession().setAttribute("userIsUser", false);
			
	    	List<AppRole> listeAppRoles = appRoleManager.selectionnerAppRolesByPseudo(principal.getName());
	    	
	    	for (AppRole appRole : listeAppRoles) {
		        System.out.println(appRole.getRoleName());
	    		if (appRole.getRoleName().equals("ROLE_ADMIN")) {
	    			req.getSession().setAttribute("userIsAdmin", true);
	    		}
	    		if (appRole.getRoleName().equals("ROLE_USER")) {
	    			req.getSession().setAttribute("userIsUser", true);
	    		}
	    	}
		}
		
		String userPseudo = (String) req.getSession().getAttribute("userPseudo");
		
		if (userPseudo == null && modelAndView != null && principal != null) {
	    	Utilisateur currentUser = utilisateurManager.selectionnerUtilisateur(principal.getName());
	    	req.getSession().setAttribute("userPseudo", currentUser.getPseudo());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex) throws Exception {
	}

}
