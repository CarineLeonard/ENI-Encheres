package fr.eni.encheres.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.eni.encheres.bll.ArticleBlockManager;
import fr.eni.encheres.bll.ArticleVenduManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bll.UserDetailsServiceImpl;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleBlock;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.EnchereId;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.RetraitId;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.services.ArticleVenduForm;
import fr.eni.encheres.services.EnchereForm;
import fr.eni.encheres.services.RechercheForm;
import fr.eni.encheres.services.UtilisateurForm;
import fr.eni.encheres.services.WebUtils;

// used to map web requests to Spring Controller methods.
@Controller
public class MainController {

	@Autowired
	private ArticleVenduManager articleVenduManager;
	@Autowired
	private ArticleBlockManager articleBlockManager;
	@Autowired
	private CategorieManager categorieManager;
	@Autowired 
	private RetraitManager retraitManager;
	@Autowired
	private UtilisateurManager utilisateurManager;
	@Autowired
	private EnchereManager enchereManager;

	@Autowired
	private UtilisateurValidator utilisateurValidator;
	@Autowired
	private UtilisateurEditValidator utilisateurEditValidator;
	@Autowired
	private ArticleVenduValidator articleVenduValidator;
	@Autowired
	private EnchereValidator enchereValidator;

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
//	@RequestMapping(value = { "/", "/encheres" }, method = RequestMethod.GET)
//	public String welcomePage(Model model) {
//		model.addAttribute("title_welcome", "Accueil");
//		model.addAttribute("titre_welcome", "Liste des enchères");
//		Iterable<Categorie> list = categorieManager.selectionnerTous();
//		model.addAttribute("categories", list);
//		try {
//			model.addAttribute("articles", articleBlockManager.selectionnerTousArticleBlocks());
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("errorMessage", "Error: " + e.getMessage());
//		}
//		return "welcomePage";
//	}
	
	@RequestMapping(value = { "/", "/encheres" }, method = RequestMethod.GET)
	public String encheres(@RequestParam(value = "id", defaultValue = "") Long id, 
			@RequestParam(value = "noCategorie", defaultValue = "0") Long noCategorie, HttpServletRequest request,
			@RequestParam(value = "recherche", defaultValue = "") String recherche,
			@RequestParam(value = "radio", defaultValue = "false") boolean radio,
			@RequestParam(value = "achatsOuvertes", defaultValue = "false") boolean achatsOuvertes,
			@RequestParam(value = "achatsEnCours", defaultValue = "false") boolean achatsEnCours,
			@RequestParam(value = "achatsRemportees", defaultValue = "false") boolean achatsRemportees,
			@RequestParam(value = "ventesEnCours", defaultValue = "false") boolean ventesEnCours,
			@RequestParam(value = "ventesNonDebutees", defaultValue = "false") boolean ventesNonDebutees,
			@RequestParam(value = "ventesTerminees", defaultValue = "false") boolean ventesTerminees,
			Model model, Principal principal) {
		
		model.addAttribute("title_welcome", "Enchères");
		model.addAttribute("titre_welcome", "Liste des enchères");
		
		if(id != null) {
			model.addAttribute("enchere_id", id);
			
			ArticleBlock article = null;
			try {
				article = articleBlockManager.selectionnerArticleBlockById(id);
				
				String uploadPath = request.getServletContext().getRealPath("/upload")+ "\\" + id;
			    System.err.println("uploadPath=" + uploadPath);
			    
			    File file = new File(uploadPath);
				if (file.exists()) {
					model.addAttribute("file", file);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
			}
			model.addAttribute("article", article);
			
			EnchereForm form = new EnchereForm();
			model.addAttribute("enchereForm", form);
	
			return "viewSalePage";
		}

		if(noCategorie == 0 && recherche == "" && radio == false &&
				achatsOuvertes == false && achatsEnCours == false && achatsRemportees == false &&
				ventesEnCours == false && ventesNonDebutees == ventesTerminees && radio == false) {
			Iterable<Categorie> list = categorieManager.selectionnerTous();
			model.addAttribute("categories", list);
			try {
				model.addAttribute("articles", articleBlockManager.selectionnerArticleBlocksEncheresOuvertes(null, null));
				
				String uploadPath = request.getServletContext().getRealPath("/upload")+ "\\" + id;
			    System.err.println("uploadPath=" + uploadPath);
			    
			    File file = new File(uploadPath);
				if (file.exists()) {
					model.addAttribute("file", file); }
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
			}
			
			RechercheForm form = new RechercheForm();
			form.setAchatsOuvertes(true);
			model.addAttribute("rechercheForm", form);
		} else {
			RechercheForm rechercheForm = new RechercheForm();
			rechercheForm.setCategorie(noCategorie);
			rechercheForm.setRecherche(recherche);
			rechercheForm.setRadio(radio);
			rechercheForm.setAchatsOuvertes(achatsOuvertes);
			rechercheForm.setAchatsEnCours(achatsEnCours);
			rechercheForm.setAchatsRemportees(achatsRemportees);
			rechercheForm.setVentesEnCours(ventesEnCours);
			rechercheForm.setVentesNonDebutees(ventesNonDebutees);
			rechercheForm.setVentesTerminees(ventesTerminees);
			model.addAttribute("rechercheForm", rechercheForm);
			
			Iterable<Categorie> list = categorieManager.selectionnerTous();
			model.addAttribute("categories", list);
			
			Long noUtilisateur = null;
			try {
				if (principal != null) {
					noUtilisateur = utilisateurManager.selectionnerUtilisateur(principal.getName()).getNoUtilisateur();
				}
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
			}
			
			Long categorie = null;
			if (rechercheForm.getCategorie() != 0) {
				categorie = rechercheForm.getCategorie();
			}
			if (rechercheForm.getRecherche().length()<=0) {
				rechercheForm.setRecherche(null);
			}
			
			try {
				if(!rechercheForm.isRadio()) {
					if (rechercheForm.isAchatsOuvertes() && rechercheForm.isAchatsEnCours() && rechercheForm.isAchatsRemportees()) {
						model.addAttribute("articles", articleBlockManager.selectionnerArticleBlocksEncheresOuvertesMesEncheresRemportees(noUtilisateur, categorie, rechercheForm.getRecherche()));
					} else if (rechercheForm.isAchatsOuvertes() && rechercheForm.isAchatsEnCours()) {
						model.addAttribute("articles", articleBlockManager.selectionnerArticleBlocksEncheresOuvertesMesEncheresEncours(categorie, rechercheForm.getRecherche()));
					} else if (rechercheForm.isAchatsOuvertes() && rechercheForm.isAchatsRemportees()) {
						model.addAttribute("articles", articleBlockManager.selectionnerArticleBlocksEncheresOuvertesMesEncheresRemportees(noUtilisateur, categorie, rechercheForm.getRecherche()));
					} else if (rechercheForm.isAchatsEnCours() && rechercheForm.isAchatsRemportees()) {
						model.addAttribute("articles", articleBlockManager.selectionnerArticleBlocksMesEncheresEncoursMesEncheresRemportees(noUtilisateur, categorie, rechercheForm.getRecherche()));
					} else if (rechercheForm.isAchatsOuvertes()) {
						model.addAttribute("articles", articleBlockManager.selectionnerArticleBlocksEncheresOuvertes(categorie, rechercheForm.getRecherche()));
					} else if (rechercheForm.isAchatsEnCours()) {
						model.addAttribute("articles", articleBlockManager.selectionnerArticleBlocksMesEncheresEncours(noUtilisateur, categorie, rechercheForm.getRecherche()));
					} else if (rechercheForm.isAchatsRemportees()) {
						model.addAttribute("articles", articleBlockManager.selectionnerArticleBlocksMesEncheresRemportees(noUtilisateur, categorie, rechercheForm.getRecherche()));
					} else {
						model.addAttribute("articles", articleBlockManager.selectionnerArticleBlocksEncheresOuvertes(categorie, rechercheForm.getRecherche()));
					}

				} else {
					if (rechercheForm.isVentesEnCours() && rechercheForm.isVentesNonDebutees() && rechercheForm.isVentesTerminees()) {
						model.addAttribute("articles", articleBlockManager.selectionnerArticleBlocksToutesMesVentes(noUtilisateur, categorie, rechercheForm.getRecherche()));
					} else if (rechercheForm.isVentesEnCours() && rechercheForm.isVentesNonDebutees()) {
						model.addAttribute("articles", articleBlockManager.selectionnerArticleBlocksMesVentesEnCoursMesVentesNonDebutees(noUtilisateur, categorie, rechercheForm.getRecherche()));
					} else if (rechercheForm.isVentesEnCours() && rechercheForm.isVentesTerminees()) {
						model.addAttribute("articles", articleBlockManager.selectionnerArticleBlocksMesVentesEnCoursMesVentesTerminees(noUtilisateur, categorie, rechercheForm.getRecherche()));
					} else if (rechercheForm.isVentesNonDebutees() && rechercheForm.isVentesTerminees()) {
						model.addAttribute("articles", articleBlockManager.selectionnerArticleBlocksMesVentesNonDebuteesMesVentesTerminees(noUtilisateur, categorie, rechercheForm.getRecherche()));
					} else if (rechercheForm.isVentesEnCours()) {
						model.addAttribute("articles", articleBlockManager.selectionnerArticleBlocksMesVentesEnCours(noUtilisateur, categorie, rechercheForm.getRecherche()));
					} else if (rechercheForm.isVentesNonDebutees()) {
						model.addAttribute("articles", articleBlockManager.selectionnerArticleBlocksMesVentesNonDebutees(noUtilisateur, categorie, rechercheForm.getRecherche()));
					} else if (rechercheForm.isVentesTerminees()) {
						model.addAttribute("articles", articleBlockManager.selectionnerArticleBlocksMesVentesTerminees(noUtilisateur, categorie, rechercheForm.getRecherche()));
					}
					
				}
				String uploadPath = request.getServletContext().getRealPath("/upload")+ "\\" + id;
			    System.err.println("uploadPath=" + uploadPath);
			    
			    File file = new File(uploadPath);
				if (file.exists()) {
				model.addAttribute("file", file); }
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
			}
		}
		
		return "welcomePage";
	}

	@RequestMapping(value = "/encheres", method = RequestMethod.POST)
	public String newEnchere(@RequestParam(value = "id", defaultValue = "") Long id, Model model, Principal principal, //
			@ModelAttribute("enchereForm") EnchereForm enchereForm, //
			@ModelAttribute("rechercheForm") RechercheForm rechercheForm,
			BindingResult result, //
			final RedirectAttributes redirectAttributes) {

		model.addAttribute("title_welcome", "Enchères");
		model.addAttribute("titre_welcome", "Liste des enchères");
		if(id != null) {
			model.addAttribute("enchere_id", id);
			
			Utilisateur currentUser = null;
			ArticleVendu articleVendu = null;
			ArticleBlock article = null;
			try {
				currentUser = utilisateurManager.selectionnerUtilisateur(principal.getName());
				articleVendu = articleVenduManager.selectionnerArticleVendu(id);
				article = articleBlockManager.selectionnerArticleBlockById(id);
				enchereForm.setArticleVendu(articleVendu);
				enchereForm.setUtilisateur(currentUser);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
			}
			model.addAttribute("article", article);
	
			try {
				enchereValidator.validate(enchereForm, result);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
				model.addAttribute("path", "encheres?id=" + id);
				return "viewSalePage";
			}
			
			if (result.hasErrors()) {
				model.addAttribute("path", "encheres?id=" + id);
				return "viewSalePage";
			}
	
			try {
				enchereManager.ajouterEnchere(new EnchereId(enchereForm.getUtilisateur(), enchereForm.getArticleVendu()), enchereForm);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
				model.addAttribute("path", "encheres?id=" + id);
				return "viewSalePage";
			}
			
			return "redirect:/encheres?id=" + id;
		}
		String params = "?noCategorie=" + rechercheForm.getCategorie()
						+"&recherche=" + rechercheForm.getRecherche()
						+"&radio=" + rechercheForm.isRadio()
						+"&achatsOuvertes=" + rechercheForm.isAchatsOuvertes()
						+"&achatsEnCours=" + rechercheForm.isAchatsEnCours()
						+"&achatsRemportees=" + rechercheForm.isAchatsRemportees()
						+"&ventesEnCours=" + rechercheForm.isVentesEnCours()
						+"&ventesNonDebutees=" + rechercheForm.isVentesNonDebutees()
						+"&ventesTerminees=" + rechercheForm.isVentesTerminees();
		return "redirect:/encheres"+params;
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
	public String userInfo(@RequestParam(value = "pseudo", defaultValue = "") String pseudo, Model model,
			Principal principal) {
		model.addAttribute("title_userInfo", "Profil");
		model.addAttribute("titre_userInfo", "Profil");
		if (pseudo.equals("")) {
			pseudo = principal.getName();
		}
		Utilisateur user = null;
		try {
			user = utilisateurManager.selectionnerUtilisateur(pseudo);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
		}
		model.addAttribute("user", user);

		return "userInfoPage";
	}

	@RequestMapping(value = "/editInfo", method = RequestMethod.GET)
	public String editInfo(Model model, Principal principal) {
		String pseudo = principal.getName();
		Utilisateur user = null;
		try {
			user = utilisateurManager.selectionnerUtilisateur(pseudo);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
		}
		model.addAttribute("user", user);

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
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				userDetailsServiceImpl.loadUserByUsername(newUser.getPseudo()), newUser.getMotDePasse(),
				updatedAuthorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		redirectAttributes.addFlashAttribute("flashUser", newUser);

		return "redirect:/userInfo";
	}

	@RequestMapping(value = "/deleteAccount", method = RequestMethod.GET)
	public String deleteAccount(Model model, Principal principal, //
			final RedirectAttributes redirectAttributes) {
		try {
			String pseudo = principal.getName();
			Utilisateur user = null;
			try {
				user = utilisateurManager.selectionnerUtilisateur(pseudo);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
			}
			utilisateurManager.supprimerUtilisateur(user.getNoUtilisateur());
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

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String viewRegister(Model model) {

		UtilisateurForm form = new UtilisateurForm();
		model.addAttribute("title_register", "Créer un compte");
		model.addAttribute("titre_register", "Mon profil");
		model.addAttribute("utilisateurForm", form);

		return "registerPage";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveRegister(Model model, //
			@ModelAttribute("utilisateurForm") @Validated UtilisateurForm utilisateurForm, //
			BindingResult result, //
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			model.addAttribute("title_register", "Créer un compte");
			model.addAttribute("titre_register", "Créer un compte");
			return "registerPage";
		}
	    // TODO - user role à paramétrer - si on veut améliorer : mapper (+tard)
		Utilisateur newUser = null;
		try {
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
		Utilisateur user = null;
		try {
			user = utilisateurManager.selectionnerUtilisateur(pseudo);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
		}
		model.addAttribute("user", user);

		ArticleVenduForm form = new ArticleVenduForm();
		model.addAttribute("title_newSale", "Nouvelle vente");
		model.addAttribute("titre_newSale", "Nouvelle vente");
		model.addAttribute("articleForm", form);
		Iterable<Categorie> list = categorieManager.selectionnerTous();
		model.addAttribute("categories", list);
		return "newSalePage";
	}

	@RequestMapping(value = "/newSale", method = RequestMethod.POST)
	public String saveNewSale(Model model, Principal principal, HttpServletRequest request,
			@ModelAttribute("articleForm") ArticleVenduForm articleForm, //
			BindingResult result, @RequestParam(value = "categorie") Long currentNoCategorie, //
			final RedirectAttributes redirectAttributes) {

		try {
			Utilisateur currentUser = utilisateurManager.selectionnerUtilisateur(principal.getName());
			articleForm.setUtilisateur(currentUser);

			Categorie currentCategorie = categorieManager.selectionnerCategorie(currentNoCategorie);
			articleForm.setCategorie(currentCategorie);
	
			articleVenduValidator.validate(articleForm, result);
			System.err.println("1");
	

		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
			model.addAttribute("title_newSale", "Nouvelle vente");
			model.addAttribute("titre_newSale", "Nouvelle vente");
			Iterable<Categorie> list = categorieManager.selectionnerTous();
			model.addAttribute("categories", list);
			System.err.println("2");
			return "newSalePage";
		}

		if (result.hasErrors()) {
			Iterable<Categorie> list = categorieManager.selectionnerTous();
			model.addAttribute("categories", list);
			model.addAttribute("title_newSale", "Nouvelle vente");
			model.addAttribute("titre_newSale", "Nouvelle vente");
			System.err.println("2");
			return "newSalePage";

		}
		ArticleVendu newArticle = null;

		try {
			newArticle = articleVenduManager.ajouterArticleVendu(articleForm);
			retraitManager.ajouterRetrait(new RetraitId(newArticle), articleForm);
		} catch (Exception e) {
			model.addAttribute("title_newSale", "Nouvelle vente");
			model.addAttribute("titre_newSale", "Nouvelle vente");
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
			Iterable<Categorie> list = categorieManager.selectionnerTous();
			model.addAttribute("categories", list);
			System.err.println("3");
			return "newSalePage";
		}

		// photo !!
		// Root Directory.
	      String uploadRootPath = request.getServletContext().getRealPath("upload");
	      System.err.println("uploadRootPath=" + uploadRootPath);
	 
	      File uploadRootDir = new File(uploadRootPath);
	      // Create directory if it not exists.
	      if (!uploadRootDir.exists()) {
	         uploadRootDir.mkdirs();
	      }
	      MultipartFile[] fileDatas = articleForm.getFileDatas();
	      //
	      List<File> uploadedFiles = new ArrayList<File>();
	      List<String> failedFiles = new ArrayList<String>();
			System.err.println("5");
	 
	      for (MultipartFile fileData : fileDatas) {
				System.err.println("6"); 
	         // Client File Name
	         String name = newArticle.getNoArticle().toString();
	         System.out.println("Client File Name = " + name);
	 
	         if (name != null && name.length() > 0) {
	 			System.err.println("7");
	            try {
	               // Create the file at server
	               File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
	 
	               BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
	               stream.write(fileData.getBytes());
	               stream.close();
	               //
	               uploadedFiles.add(serverFile);
	               System.err.println("Write file: " + serverFile);
	            } catch (Exception e) {
	               System.err.println("Error Write file: " + name);
	               failedFiles.add(name);

	            }
	         }
	      }
	      
	      model.addAttribute("uploadedFiles", uploadedFiles);
	      model.addAttribute("failedFiles", failedFiles);
		
		
		redirectAttributes.addFlashAttribute("flashUser", newArticle);

		return "redirect:/encheres";
	}

	@RequestMapping(value = "/editSale/{noArticle}", method = RequestMethod.GET)
	public String editSale(@PathVariable("noArticle") Long noArticle, Model model, Principal principal) {

		String pseudo = principal.getName();
		Utilisateur user = null;
		try {
			user = utilisateurManager.selectionnerUtilisateur(pseudo);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
		}
		model.addAttribute("user", user);

		Iterable<Categorie> list = categorieManager.selectionnerTous();
		model.addAttribute("categories", list);

		ArticleVenduForm form = new ArticleVenduForm();
		model.addAttribute("articleForm", form);
		
		Date datejour = new Date();
		model.addAttribute("datejour", datejour);

		try {
			ArticleVendu article = articleVenduManager.selectionnerArticleVendu(noArticle);
			model.addAttribute("article", article);

			Retrait retrait = retraitManager.selectionnerRetrait(new RetraitId(article));
			model.addAttribute("retrait", retrait);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("title_editSale", "Modifier ma vente");
		model.addAttribute("titre_editSale", "Modifier ma vente");
		return "editSalePage";
	}

	// TODO - à faire
	@RequestMapping(value = "/editSale/{noArticle}", method = RequestMethod.POST)
	public String editSale(@PathVariable("noArticle") Long noArticle, Model model, Principal principal, 
			@RequestParam(value = "categorie") Long currentNoCategorie, 
			@ModelAttribute("articleForm") ArticleVenduForm articleForm, BindingResult result, final RedirectAttributes redirectAttributes) {
		try {
			ArticleVendu article = articleVenduManager.selectionnerArticleVendu(noArticle);
			model.addAttribute("article", article);
			
			Utilisateur currentUser = utilisateurManager.selectionnerUtilisateur(principal.getName());
			articleForm.setUtilisateur(currentUser);
			Categorie currentCategorie = categorieManager.selectionnerCategorie(currentNoCategorie);
			articleForm.setCategorie(currentCategorie);
			articleForm.setNoArticle(noArticle);

			articleVenduValidator.validate(articleForm, result);

		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
			model.addAttribute("title_editSale", "Modifier ma vente");
			model.addAttribute("titre_editSale", "Modifier ma vente");
			Iterable<Categorie> list = categorieManager.selectionnerTous();
			model.addAttribute("categories", list);
			return "editSalePage";
		}

		if (result.hasErrors()) {
			model.addAttribute("title_editSale", "Modifier ma vente");
			model.addAttribute("titre_editSale", "Modifier ma vente");
			Iterable<Categorie> list = categorieManager.selectionnerTous();
			model.addAttribute("categories", list);
			return "editSalePage";
		}

		ArticleVendu newArticle = null;

		try {
			newArticle = articleVenduManager.updateArticleVendu(articleForm);
			retraitManager.updateRetrait(new RetraitId(newArticle), articleForm);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
			model.addAttribute("title_editSale", "Modifier ma vente");
			model.addAttribute("titre_editSale", "Modifier ma vente");
			Iterable<Categorie> list = categorieManager.selectionnerTous();
			model.addAttribute("categories", list);
			return "editSalePage";
		}

		redirectAttributes.addFlashAttribute("flashUser", newArticle);

		return "redirect:/encheres";
	}

	@RequestMapping(value = "/deleteSale/{noArticle}", method = RequestMethod.GET)
	public String deleteSale(@PathVariable("noArticle") Long noArticle, Model model, Principal principal, //
			final RedirectAttributes redirectAttributes) {
		try {
			ArticleVendu articleVendu = articleVenduManager.selectionnerArticleVendu(noArticle);
			Retrait retrait = retraitManager.selectionnerRetrait(new RetraitId(articleVendu));
			retraitManager.supprimerRetrait(retrait); 
			articleVenduManager.supprimerArticle(articleVendu);
			
		} catch (Exception e) {
			System.out.println(e);
			redirectAttributes.addFlashAttribute("errorMessage", "Error: " + e.getMessage());
			return "editSalePage";
		}

		return "redirect:/";
	}

	@RequestMapping(value = "/endSale/{noArticle}", method = RequestMethod.GET)
	public String endSale(@PathVariable("noArticle") Long noArticle, Model model, HttpServletRequest request, Principal principal) {
		model.addAttribute("title_endSale", "Enchère terminée");
		ArticleVendu article = null; 
		ArticleBlock articleBlock = null; 
		try {
			article = articleVenduManager.selectionnerArticleVendu(noArticle);
			articleBlock = articleBlockManager.selectionnerArticleBlockById(noArticle);
		    
			String uploadPath = request.getServletContext().getRealPath("/upload")+ "\\" + noArticle;
		    System.err.println("uploadPath=" + uploadPath);
		    
		    File file = new File(uploadPath);
			if (file.exists()) {
				model.addAttribute("file", file);
			}
				
		} catch (Exception e) {
			System.out.println(e);
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
		} 
		model.addAttribute("article", article);
		model.addAttribute("articleBlock", articleBlock);
		return "endSalePage";
	}
	
	@RequestMapping(value= "/article/{noArticle}", method = RequestMethod.GET)
	public String article(@PathVariable("noArticle") Long noArticle, Model model, HttpServletRequest request, Principal principal) {
		
		Utilisateur currentUser=null;
		try {
			if (principal != null) {
				currentUser = utilisateurManager.selectionnerUtilisateur(principal.getName());
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArticleVendu article1 = null; 
		ArticleBlock a = null; 
		try {
			article1 = articleVenduManager.selectionnerArticleVendu(noArticle);
			a = articleBlockManager.selectionnerArticleBlockById(noArticle);
			
			String uploadPath = request.getServletContext().getRealPath("/upload")+ "\\" + noArticle;
		    System.err.println("uploadPath=" + uploadPath);
		    
		    File file = new File(uploadPath);
			if (file.exists()) {
				model.addAttribute("file", file);
			}
			
		} catch (Exception e) {
			System.out.println(e);
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
		} 
		
		Date datejour = new Date();
		
		String aretournerString = null; 
		
		// si date fin enchères pas passé mais que date début enchère passée 
		if ((article1.getDateFinEncheres().compareTo(datejour) > 0) & (article1.getDateDebutEncheres().compareTo(datejour) < 0)) {
			return "redirect:/encheres?id=" + noArticle;
		}
		// 				model.addAttribute("path", "encheres?id=" + id);
		// return "viewSalePage";
		
		// si user en cours est le vendeur et que date début enchère pas passé 
		if (currentUser.getPseudo().equals(a.getPseudoVendeur()) &  (article1.getDateDebutEncheres().compareTo(datejour) > 0)) {
			return "redirect:/editSale/" + noArticle;
		}
		
		// si user en cours est un autre user et que date début enchère pas passée 
		if (!currentUser.getPseudo().equals(a.getPseudoVendeur()) &  (article1.getDateDebutEncheres().compareTo(datejour) > 0)) {
			model.addAttribute("path", "encheres?id=" + noArticle);
			model.addAttribute("article1", article1);
			return "redirect:/encheres?id=" + noArticle;
		}
		
		// si date fin enchère est passée 
		if (article1.getDateFinEncheres().compareTo(datejour) < 0) {
			model.addAttribute("path", noArticle);		
			return "redirect:/endSale/" + noArticle; 
		}
		
		return aretournerString;
		
		

		
	}
}
