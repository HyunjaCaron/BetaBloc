package com.fdmgroup.betablocentities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "products")
public class Product {
	
	@Id
	@Column
	private String productId;
	@Column
	private double price;
	@Column
	private String name;
	@Column
	private int stock;
	@Column
	private String category;
	@Column
	private String img;
	
	
	public boolean checkStock(int quantity) {
		
		if(quantity>stock) {
			return false;
		}
		return true;
	}
	
	public void updateStock(int quantity) {
		stock = stock -quantity;
	}
	
	
	//constructors
	
	public Product() { }
	
	public Product(String productId, double price, String name, int stock, String category, String img) {
		super();
		this.productId = productId;
		this.price = price;
		this.name = name;
		this.stock = stock;
		this.category = category;
		this.img =img;
	}
	
	//getters and setters
	
	
	
	
	public String getProductId() {
		return productId;
	}
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + stock;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (stock != other.stock)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

	
	
	
	
	

}
