package com.fdmgroup.betablocentities;

import com.fdmgroup.betablocDAO.CardDAO;
import com.fdmgroup.betablocDAO.ProductDAO;
import com.fdmgroup.betablocDAO.UserDAO;

public class Application {

	public static void main(String[] args) {
		
		
		ProductDAO productDAO = new ProductDAO(); 
		UserDAO userDAO = new UserDAO();
		CardDAO cardDAO = new CardDAO();
		Product product1 = new Product("hngbrd1", 65.99, "Beastmaker1000 HangBoard", 28, "hangboard", "beastmaker1000.jpg");
		Product product2 = new Product("hngbrd2", 80.99, "Beastmaker2000 HangBoard", 50, "hangboard", "beastmaker2000.jpg");
		Product product3 = new Product("shoes1", 123.99, "Katana La Sportiva (womens) Climbing Shoes", 50, "shoes", "Katana.jpg");
		Product product4 = new Product("shoes2", 259.99, "SoIll (Unisex) Climbing Shoes", 50, "shoes", "soIll.jpeg");
		Product product5 = new Product("rope1", 100.99, "Black Diamond 100m Rope", 50, "sportclimbing", "rope100m.jpg");
		Product product6 = new Product("rope2", 89.99, "Black Diamond 50m Rope", 50, "sportclimbing", "rope50m.jpg");
		Product product7 = new Product("carabiner1", 45.99, "20pc Carabiner Set", 50, "tradclimbing", "carabinerspack.jpg");
		Product product8 = new Product("belaydevice1", 25.99, "Black Diamond Belay Device", 50, "sportclimbing", "belaydevice.jpg");
		User user1 = new User("boblovesrefrigerators", "bob@email.com", "Bob Vance","phyllis");
		User user2 = new User("pamlovesart", "pam@email.com", "Pam Halbert","jim");
		Card card1 = new Card("card01", 582.74, user1);
		Card card2 = new Card("card02", 8888.74, user2);
		
		productDAO.addProduct(product1);
		productDAO.addProduct(product2);
		productDAO.addProduct(product3);
		productDAO.addProduct(product4);
		productDAO.addProduct(product5);
		productDAO.addProduct(product6);
		productDAO.addProduct(product7);
		productDAO.addProduct(product8);
		userDAO.addUser(user1);
		userDAO.addUser(user2);
		cardDAO.addCard(card1);
		cardDAO.addCard(card2);
		
		

	}

}
