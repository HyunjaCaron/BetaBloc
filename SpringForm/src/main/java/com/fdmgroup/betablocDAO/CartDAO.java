package com.fdmgroup.betablocDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.fdmgroup.betablocentities.Cart;

public class CartDAO {
	
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpringForm");
	private EntityManager entityManager = emf.createEntityManager();
	private EntityTransaction entityTransaction = entityManager.getTransaction();
			

	public List<Cart> listCarts() {
		String jpql = "SELECT c FROM carts c";
		TypedQuery<Cart> cartQuery = entityManager.createQuery(jpql,Cart.class);
		
		List<Cart> cartsFromTable = cartQuery.getResultList();
		
		return cartsFromTable;
	}
	
	
	public List<Cart> listCartForAUser(String userId) {
		String jpql = "SELECT c FROM carts c WHERE user.userId =:userId";
		TypedQuery<Cart> cartQuery = entityManager.createQuery(jpql,Cart.class);
		cartQuery.setParameter("userId", userId);
		List<Cart> cartsFromTable = cartQuery.getResultList();
		
		return cartsFromTable;
	}

	public void addCart(Cart cart) {
		entityTransaction.begin();
			entityManager.persist(cart);
		entityTransaction.commit();
		
	}

	public Cart getCart(String primaryKey) {
		Cart cartFromTable = entityManager.find(Cart.class, primaryKey);
		return cartFromTable;
	}

	public void removeCart(String primaryKey) {
		Cart cartToDelete =  entityManager.find(Cart.class, primaryKey);
		
		if(cartToDelete != null) {
			entityTransaction.begin();
				entityManager.remove(cartToDelete);
			entityTransaction.commit();
		}
		
	}

	public void updateCart(Cart cart) {
		
		Cart cartToUpdate = entityManager.find(Cart.class, cart.getCartId());
		
		if(cartToUpdate != null) {
			entityTransaction.begin();
				entityManager.merge(cart);
			
			entityTransaction.commit();
		}
	}

}
