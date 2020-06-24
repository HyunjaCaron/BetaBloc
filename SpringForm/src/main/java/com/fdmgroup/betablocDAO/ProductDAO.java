package com.fdmgroup.betablocDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.fdmgroup.betablocentities.Product;

public class ProductDAO {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpringForm");
	private EntityManager entityManager = emf.createEntityManager();
	private EntityTransaction entityTransaction = entityManager.getTransaction();
			

	public List<Product> listProducts(String field, String source) {
		String jpql="";
		if(field==null) {
			jpql = "SELECT p FROM products p";
			
		}
		else if (source.equals("filtersearch")) {
			jpql = "SELECT p FROM products p order by p." + field;
			
		}
		else if (source.equals("searchbar")) {
			jpql = "SELECT p FROM products p WHERE UPPER(p.name) LIKE UPPER('%" +field + "%')";
		}
		TypedQuery<Product> productQuery = entityManager.createQuery(jpql,Product.class);	
		List<Product> productsFromTable = productQuery.getResultList();
		return productsFromTable;
	}
	
	public List<Product> listProductsByCategory(String category) {
		TypedQuery<Product> productQuery = entityManager.createQuery( "SELECT p FROM products p WHERE p.category =:category",Product.class);			
		productQuery.setParameter("category", category);
		List<Product> productsFromTable = productQuery.getResultList();
		return productsFromTable;
	}

	

	public void addProduct(Product product1) {
		 String productId= product1.getProductId();
		  Product productInDB = entityManager.find(Product.class, productId);

		  if(productInDB==null){
		     entityTransaction.begin();
		     entityManager.persist(product1);
		     entityTransaction.commit();
		  }
		
	}

	public Product getProduct(String primaryKey) {
		Product productFromTable = entityManager.find(Product.class, primaryKey);
		return productFromTable;
	}

	public void removeProduct(String productId) {
		Product productToDelete =  entityManager.find(Product.class, productId);
		
		if(productToDelete != null) {
			entityTransaction.begin();
				entityManager.remove(productToDelete);
			entityTransaction.commit();
		}
		
	}

	public void updateProduct(Product product1) {
		System.out.println(product1.getProductId());
		Product productToUpdate = entityManager.find(Product.class, product1.getProductId());//if this trainee does not exist we will get null
		
		if(productToUpdate != null) {
			entityTransaction.begin();
				entityManager.merge(product1);
			
			entityTransaction.commit();
		}

		
	}
	
}
