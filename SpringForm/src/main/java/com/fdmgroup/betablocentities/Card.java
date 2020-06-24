package com.fdmgroup.betablocentities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fdmgroup.betablocDAO.CardDAO;

@Entity(name="cards")
public class Card {
	
	@Id
	@Column
	private String cardId;
	@Column
	private double balance;
	@ManyToOne
	private User user;

	
	public void addMoneyToCard(double amount) {
		CardDAO cardDAO = new CardDAO();
		balance = balance + amount; 
		cardDAO.updateCard(this);
		
	}
	
	
	//constructors
	
	public Card() {}
	public Card(String cardId, double balance, User user) {
		super();
		this.cardId = cardId;
		this.balance = balance;
		this.user = user;
	}
	
	//getters and setters
	
	
	
	
	public String getCardId() {
		return cardId;
	}



	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
    public boolean equals(Object o) { 
   
        if (o == this) { 
            return true; 
        } 
  
       
        if (!(o instanceof Card)) { 
            return false; 
        } 

        Card c = (Card) o; 
          
        return( c.getCardId().equals(this.cardId) &&
				c.getBalance() == (this.balance));
    } 
	
	

}
