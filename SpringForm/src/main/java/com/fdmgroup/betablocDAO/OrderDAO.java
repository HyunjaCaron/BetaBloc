package com.fdmgroup.betablocDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.fdmgroup.betablocentities.Order;

public class OrderDAO {
	
	
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpringForm");
	private EntityManager entityManager = emf.createEntityManager();
	private EntityTransaction entityTransaction = entityManager.getTransaction();
			

	public List<Order> listOrders() {
		String jpql = "SELECT o FROM orders o";
		TypedQuery<Order> orderQuery = entityManager.createQuery(jpql,Order.class);
		
		List<Order> ordersFromTable = orderQuery.getResultList();
		
		return ordersFromTable;
	}
	
	public List<Order> listOrdersForAUser(String userId) {
		String jpql = "SELECT o FROM orders o where o.user.userId =:userId";
		TypedQuery<Order> orderQuery = entityManager.createQuery(jpql,Order.class);
		orderQuery.setParameter("userId", userId);
		List<Order> ordersFromTable = orderQuery.getResultList();
		
		return ordersFromTable;
	}

	public void addOrder(Order order) {
		entityTransaction.begin();
			entityManager.persist(order);
		entityTransaction.commit();
		
	}

	public Order getOrder(String primaryKey) {
		Order orderFromTable = entityManager.find(Order.class, primaryKey);
		return orderFromTable;
	}

	public void removeOrder(String primaryKey) {
		Order orderToDelete =  entityManager.find(Order.class, primaryKey);
		
		if(orderToDelete != null) {
			entityTransaction.begin();
				entityManager.remove(orderToDelete);
			entityTransaction.commit();
		}
		
	}

	public void updateOrder(Order order) {
		
		Order orderToUpdate = entityManager.find(Order.class, order.getOrderId());
		
		if(orderToUpdate != null) {
			entityTransaction.begin();
				entityManager.merge(order);
			
			entityTransaction.commit();
		}
	}

}
