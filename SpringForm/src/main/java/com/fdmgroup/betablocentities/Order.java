package com.fdmgroup.betablocentities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.SequenceGenerator;

import com.fdmgroup.betablocDAO.CardDAO;
import com.fdmgroup.betablocDAO.ProductDAO;

@Entity(name="orders")
public class Order {
	@Id
	@Column
//	@SequenceGenerator(name = "orderIdSeq", sequenceName = "OREDR_ID_SEQ", initialValue = 1000, allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderIdSeq")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int orderId;
	
	@ManyToOne
	@JoinColumn
	private User user;
	
	@Column(name="order_date")
	private Date date;
	
	@Column
	private double totalPrice=0;
	
	@ElementCollection
	@CollectionTable(name="products_in_order",joinColumns=@JoinColumn(name="order_id")) // name of joining table
	@MapKeyJoinColumn(name="product_id") // pk of class used as Map key
	@Column(name="number_in_order") // name of value column from Map
	// must create reference to Map interface - cannot reference concrete class
	private Map<Product,Integer> products = new HashMap<Product,Integer>();

	//constructors
	
	public Order() {}
	
	public Order(User user, Date date) {
		super();
		this.user = user;
		this.date = date;
	}
	
	

	//getters and setters
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Map<Product, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<Product, Integer> products) {
		this.products = products;
		updateTotalPrice();
	}
	
	public void updateTotalPrice() {
		totalPrice=0;
		for (Entry<Product,Integer> eachEntry : products.entrySet()) {
			double price = eachEntry.getKey().getPrice();
			int quantity = eachEntry.getValue();
			totalPrice = totalPrice + (price*quantity);
			
		}
	}
	
	public boolean updateCardBalance(Card card) {
		double balance = card.getBalance();
		if(balance<totalPrice) {
			return false;
		}
		balance = balance - totalPrice;
		card.setBalance(balance);
		return true;
	}
	
	public boolean checkProductStock() {
		for (Entry<Product,Integer> eachEntry : products.entrySet()) {
			Product product = eachEntry.getKey();
			int quantity = eachEntry.getValue();
			if(product.checkStock(quantity)) {
				continue;
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	public void updateProductStock() {
		ProductDAO productDAO= new ProductDAO();
		for (Entry<Product,Integer> eachEntry : products.entrySet()) {
			Product product = eachEntry.getKey();
			int quantity = eachEntry.getValue();
			product.updateStock(quantity);
			productDAO.updateProduct(product);
		}
		
	}
	
	
	
	
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
    public boolean equals(Object o) { 
   
        if (o == this) { 
            return true; 
        } 
  
       
        if (!(o instanceof Order)) { 
            return false; 
        } 

        Order ord = (Order) o; 
          
        return( ord.getOrderId() == (this.orderId) &&
				ord.getDate().equals((this.date)) &&
				ord.getUser() == this.user &&
				ord.getProducts().equals(this.products));
    } 
	
	

}
