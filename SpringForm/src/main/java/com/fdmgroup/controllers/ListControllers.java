package com.fdmgroup.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.betablocDAO.CardDAO;
import com.fdmgroup.betablocDAO.OrderDAO;
import com.fdmgroup.betablocDAO.ProductDAO;
import com.fdmgroup.betablocDAO.UserDAO;
import com.fdmgroup.betablocentities.Card;
import com.fdmgroup.betablocentities.Order;
import com.fdmgroup.betablocentities.Product;
import com.fdmgroup.betablocentities.User;

@Controller
public class ListControllers {
	private HashMap<Product, Integer> cartOfProducts = new HashMap<Product, Integer>();

	@RequestMapping(value = "listTheProducts")
	public String listTheProducts(Model model, HttpServletRequest request) {
		String filter = request.getParameter("filter");
		ProductDAO productDAO = new ProductDAO();

		List<Product> listOfProducts = productDAO.listProducts(filter, "filtersearch");
		model.addAttribute("listOfProducts", listOfProducts);

		return "listOfProductsPage";

	}

	@RequestMapping(value = "listTheProductsByCategory")
	public String listTheProductsByCategory(Model model, HttpServletRequest request) {
		String category = request.getParameter("category");
		ProductDAO productDAO = new ProductDAO();
		List<Product> listOfProducts = productDAO.listProductsByCategory(category);
		model.addAttribute("listOfProducts", listOfProducts);

		return "listOfProductsPage";

	}

	@RequestMapping(value = "listTheUsers")
	public String listTheUsers(Model model) {

		UserDAO userDAO = new UserDAO();
		List<User> listOfUsers = userDAO.listUsers();
		model.addAttribute("listOfUsers", listOfUsers);

		return "listOfUsersPage";

	}

	@RequestMapping(value = "listTheUserInfoForAUser")
	public String listTheUserInfoForAUser(Model model, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		UserDAO userDAO = new UserDAO();
		List<User> listOfUsers = userDAO.listTheUserInfoForAUser(userId);
		model.addAttribute("listOfUsers", listOfUsers);

		return "listOfUsersPage";

	}

	@RequestMapping(value = "listTheCards")
	public String listTheCards(Model model) {

		CardDAO cardDAO = new CardDAO();
		List<Card> listOfCards = cardDAO.listCards();
		model.addAttribute("listOfCards", listOfCards);

		return "listOfCardsPage";

	}

	@RequestMapping(value = "listCardsForAUser")
	public String listCardsForAUser(Model model, HttpSession session) {
		CardDAO cardDAO = new CardDAO();
		String userId = (String) session.getAttribute("userId");
		List<Card> listOfCards = cardDAO.listCardsForAUser(userId);
		model.addAttribute("listOfCards", listOfCards);

		return "listOfCardsPage";

	}

	@RequestMapping(value = "addMoneyToCard/{cardId}", method = POST)
	public String addMoneyToCard(Model model, @PathVariable String cardId, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		CardDAO cardDAO = new CardDAO();
		Double money = Double.parseDouble(request.getParameter("money"));
		Card card = cardDAO.getCard(cardId);
		card.addMoneyToCard(money);
		return "moneyAddedConfirmation";
	}

	@RequestMapping(value = "addACard", method = POST)
	public String addACard(Model model, Card card, HttpSession session) {
		model.addAttribute("card", card);
		UserDAO userDAO = new UserDAO();
		CardDAO cardDAO = new CardDAO();
		String userId = (String) session.getAttribute("userId");
		User user = userDAO.getUser(userId);
		card.setUser(user);
		cardDAO.addCard(card);
		return "cardAddedConfirmation";
	}

	@RequestMapping(value = "removeCard/{cardId}", method = POST)
	public String removeCard(Model model, @PathVariable String cardId, HttpSession session) {
		CardDAO cardDAO = new CardDAO();
		cardDAO.removeCard(cardId);
		return "cardRemovedConfirmation";
	}

	@RequestMapping(value = "chooseCardToRemove")
	public String chooseCardToRemove(Model model, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		CardDAO cardDAO = new CardDAO();
		List<Card> cards = cardDAO.listCardsForAUser(userId);
		model.addAttribute("cards", cards);
		model.addAttribute("card", new Card());
		return "chooseCardToRemove";

	}

	@RequestMapping(value = "inputCardInfo")
	public String inputCardInfo(Model model, HttpSession session) {
		model.addAttribute("card", new Card());
		return "inputCardInfo";
	}

	@RequestMapping(value = "listTheOrders")
	public String listTheOrders(Model model) {

		OrderDAO orderDAO = new OrderDAO();
		List<Order> listOfOrders = orderDAO.listOrders();
		model.addAttribute("listOfOrders", listOfOrders);

		return "listOfOrdersPage";

	}

	@RequestMapping(value = "listTheOrdersForAUser")
	public String listTheOrdersForAUser(Model model, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		OrderDAO orderDAO = new OrderDAO();
		List<Order> listOfOrders = orderDAO.listOrdersForAUser(userId);
		model.addAttribute("listOfOrders", listOfOrders);

		return "listOfOrdersPage";

	}

	@RequestMapping(value = "cart")
	public String CartHandler(Model model) {
		Double total = 0.0;
		for (Entry<Product, Integer> entry : cartOfProducts.entrySet()) {
			total = (total + entry.getKey().getPrice());
		}
		model.addAttribute("total", total);
		model.addAttribute("cartOfProducts", cartOfProducts);
		return "cart";
	}

	@RequestMapping(value = "clearCart")
	public String CartClearer(Model model) {
		cartOfProducts.clear();
		Double total = 0.0;
		for (Entry<Product, Integer> entry : cartOfProducts.entrySet()) {
			total = total + entry.getKey().getPrice();
		}
		model.addAttribute("total", total);
		return "cart";
	}

	@RequestMapping(value = "addItemToBasket/{productId}", method = POST)
	public String addProduct(Model model, @PathVariable String productId) {
		ProductDAO productDAO = new ProductDAO();
		Product product = productDAO.getProduct(productId);

		if (cartOfProducts.containsKey(product)) {
			int quantity = cartOfProducts.get(product);
			cartOfProducts.put(product, quantity + 1);
		} else {
			cartOfProducts.put(product, 1);
		}

		Double total = 0.0;
		for (Entry<Product, Integer> entry : cartOfProducts.entrySet()) {
			total = total + entry.getKey().getPrice();
		}
		model.addAttribute("total", total);
		model.addAttribute("cartOfProducts", cartOfProducts);
		return "cart";
	}

	@RequestMapping(value = "removeProductFromCart/{productId}", method = POST)
	public String removeProduct(Model model, @PathVariable String productId) {
		ProductDAO productDAO = new ProductDAO();
		Product product = productDAO.getProduct(productId);
		cartOfProducts.remove(product);
		Double total = 0.0;
		for (Entry<Product, Integer> entry : cartOfProducts.entrySet()) {
			total = total + entry.getKey().getPrice();
		}
		model.addAttribute("total", total);
		model.addAttribute("cartOfProducts", cartOfProducts);
		return "cart";
	}

	@RequestMapping(value = "OrderConfirmationPage")
	public String OrderConfirmation(Model model) {
		model.addAttribute("cartOfProducts", cartOfProducts);
		return "orderConfirmationPage";
	}

	@RequestMapping(value = "cardChoice")
	public String cardChoice(Model model, HttpSession session, HttpServletRequest request) {
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			request.setAttribute("message", "You must login before placing an order");
			model.addAttribute("user", new User());
			return "login";
		} else {
			CardDAO cardDAO = new CardDAO();
			List<Card> cards = cardDAO.listCardsForAUser(userId);
			model.addAttribute("cards", cards);
			model.addAttribute("card", new Card());
			return "cardChoice";
		}
	}

	@RequestMapping(value = "placeOrder/{cardId}", method = POST)
	public String placeOrder(Model model, HttpSession session, HttpServletRequest request,
			@PathVariable String cardId) {
		String userId = (String) session.getAttribute("userId");
		OrderDAO orderDAO = new OrderDAO();
		CardDAO cardDAO = new CardDAO();
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUser(userId);
		Card card = cardDAO.getCard(cardId);
		Order order = new Order(user, new Date());

		order.setProducts(cartOfProducts);
		cartOfProducts.clear();

		// if user has sufficient funds and items are in stock, place order
		if (order.checkProductStock() && order.updateCardBalance(card)) {
			order.updateProductStock();
			cardDAO.updateCard(card);
			orderDAO.addOrder(order);
			return "finalOrderPage";
		}

		return "orderFailureInsufficientFunds";
	}

}
