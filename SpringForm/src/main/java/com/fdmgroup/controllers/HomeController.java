package com.fdmgroup.controllers;


import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.betablocDAO.ProductDAO;
import com.fdmgroup.betablocDAO.UserDAO;
import com.fdmgroup.betablocentities.Product;
import com.fdmgroup.betablocentities.User;



@Controller
public class HomeController {

	// Things to notice throughout this code:

	// 1. Each controller class must be marked with an @Controller annotation.

	// 2. Method names can be called anything but make them meaningful and descriptive
	//    so we know what they are doing.
	
	// 3. Each controller class can have many methods but if we have too many we 
	// may need to have a separate controller for different sections of the site. 
	//    e.g. ItemController,
	//         OrderController, 
	//         UserController and so on. 
	// The class name can be called anything but keep it descriptive.
	
	// 4. Methods can have any arguments you need. e.g. HttpServletRequest, 
	// HttpSession, Model and even your own Java classes. 
	// The class called Model is built into SpringMVC and is used as a container
	// used to transfer things between your controller methods and the .jsp file.  
	
	// 5. There is a RequestMapping attribute for each method and it returns a String. 
	//    This is used to determine the name of the file which will handle the request.
	
	// 6. Across all the controllers in your application, the RequestMapping 
	// attributes must be unique. i.e. you cannot have 2 RequestMapping values
	// with the same value pointing to two separate pages, 
	// even if they are in two separate files.
	
	// Lets look at each of the controllers..
	
	// 1. All requests for "/" sent to login.jsp
	// In other words, http://localhost:8088/<APPLICATION_NAME> /
	// In this method, we send a Model attribute called "user" to the login.jsp page
	// the Model attribute Object is a blank User object:   new User() 
	// this will be populated when the web page is filled 
	// with information in the page login.jsp

	@RequestMapping(value = "/")
	public String loginHandler(Model model, HttpSession session) {
		// add a model attribute which contains a "new" User object. 
		// this object will be populated in the login.jsp page using the Spring Form 
		String userId = (String) session.getAttribute("userId");
		if (userId==null) {
			model.addAttribute("user", new User());
			return "login";
		}
		model.addAttribute(userId);
		return "welcome";
	}
	
	@RequestMapping(value="welcome")
	public String welcomeHandler() {
		return "welcome";
	}

	@RequestMapping(value = "submitLogin", method = POST)
	public String loginSubmitHandler(Model model, User user,HttpServletRequest request) {
		model.addAttribute("user", user);
		
		UserDAO userDAO = new UserDAO();
		String userId = user.getUserId();
		User userInDB = userDAO.getUser(userId);
		String password = user.getPassword();
		
		if(userInDB==null) {
			model.addAttribute("user", new User());
			request.setAttribute("message", "Wrong login and password");
			return "login";
		}
		
		//user exists
		String passwordInDB = userInDB.getPassword();
		if (passwordInDB.equals(password)){
			request.setAttribute("message", "You have successfully logged in");
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			return "login";
			
		}else{
			model.addAttribute("user", new User());
			request.setAttribute("message", "Wrong login and password");
			return "login";
		}
	}
	
	@RequestMapping(value="submitSearch", method=POST)
	public String searchSubmitHandler (Model model, HttpServletRequest request, HttpServletResponse response) {
		ProductDAO productDAO = new ProductDAO();
		String search = request.getParameter("search");
		List<Product> listOfProducts = productDAO.listProducts(search, "searchbar");
		model.addAttribute("listOfProducts", listOfProducts);
		
		return "listOfProductsPage";
	}
	// 3. All GET or POST requests for "register" handled by register.jsp. 
	// No Method was specified: GET or POST

	@RequestMapping(value = "login")
	public String LoginHandler(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	@RequestMapping(value="logout")
	public String logout(Model model,HttpSession session, HttpServletRequest request) {
		session.invalidate();
		request.setAttribute("message", "You have successfully logged out");
		model.addAttribute("user", new User());
		return "login";
	}
	
	
	
	@RequestMapping(value = "register")
	public String registrationHandler(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}


	@RequestMapping(value = "submitRegister")
	public String submitRegistrationHandler(Model model, User user,HttpServletRequest request) {
		model.addAttribute("user", user);
		UserDAO userDAO = new UserDAO();
		String userId = user.getUserId();
		User userInDB = userDAO.getUser(userId);
		
		if(userInDB==null) {
			
		userDAO.addUser(user);
		
		System.out.println("You have been successfully registered");
		return "login";
		}
		
		else {
			model.addAttribute("user", user);
			request.setAttribute("message", "This username is already in use. Please choose another.");
			return "register";
		}
	}

	
	
	// 5. GET requests for testPage handled by testPage.jsp

	@RequestMapping(value = "displayPage", method = GET)
	public String displayPageHandler() {
		return "displayPage";
	}

	// 6. GET requests for /displayPage/{value} sent to displayPage.jsp
	// and we pass the valueInt attribute to that page and display it
	// notice in the argument list how we define the type
	
	@RequestMapping(value = "displayPage/{valueInt}", method = GET)
	public String displayPageHandlerWithPathVariables(Model model, @PathVariable int valueInt) {
		model.addAttribute("valueInt", valueInt);
		return "displayPage";
	}

	// 7. redirect to another handler. This has the effect of using a handler to call another
	@RequestMapping(value = "redirectToDisplayPage", method = GET)
	public String redirectToDisplayPageHandler() {
		return "redirect:/displayPage";
	}
	
	
}