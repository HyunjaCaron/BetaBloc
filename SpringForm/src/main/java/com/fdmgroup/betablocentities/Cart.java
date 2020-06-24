package com.fdmgroup.betablocentities;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;

@Entity(name="carts")
public class Cart {
	
	@Id
	@Column
	private String cartId;
	@Column
	private double priceTotal;
	
	@ElementCollection
	@CollectionTable(name="products_in_cart",joinColumns=@JoinColumn(name="cart_id")) // name of joining table
	@MapKeyJoinColumn(name="product_id") // pk of class used as Map key
	@Column(name="number_in_cart") // name of value column from Map
	// must create reference to Map interface - cannot reference concrete class
	private Map<Product,Integer> products = new HashMap<Product,Integer>();
	
	public void addToCart(Product product, Integer amount) {
		products.put(product, amount);
	}
	
	public void addToCart(Product product) {
		addToCart(product, 1);
	}
	
	//constructors
	public Cart() {}
	
	public Cart(String cartId, double priceTotal) {
		super();
		this.cartId = cartId;
		this.priceTotal = priceTotal;
		
	}

	public Cart(String cartId, double priceTotal, Map<Product, Integer> products) {
		this(cartId, priceTotal);
		this.products = products;
	}

	//getters and setters
	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public double getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(double priceTotal) {
		this.priceTotal = priceTotal;
	}

	public Map<Product, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<Product, Integer> products) {
		this.products = products;
	} 
	
	@Override
    public boolean equals(Object o) { 
   
        if (o == this) { 
            return true; 
        } 
  
       
        if (!(o instanceof Cart)) { 
            return false; 
        } 

        Cart c = (Cart) o; 
          
        return(c.getCartId().equals(this.cartId) &&
				c.getPriceTotal() == (this.priceTotal) );
    } 
	
	

}
