package com.fdmgroup.betablocDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.fdmgroup.betablocentities.User;

public class UserDAO {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpringForm");
	private EntityManager entityManager = emf.createEntityManager();
	private EntityTransaction entityTransaction = entityManager.getTransaction();
			

	public List<User> listUsers() {
		String jpql = "SELECT u FROM users u";
		TypedQuery<User> userQuery = entityManager.createQuery(jpql,User.class);
		
		List<User> usersFromTable = userQuery.getResultList();
		
		return usersFromTable;
	}
	
	public List<User> listTheUserInfoForAUser(String userId) {
		String jpql = "SELECT u FROM users u where u.userId =:userId";
		TypedQuery<User> userQuery = entityManager.createQuery(jpql,User.class);
		userQuery.setParameter("userId", userId);
		List<User> usersFromTable = userQuery.getResultList();

		return usersFromTable;
	}

	public void addUser(User user) {
		entityTransaction.begin();
			entityManager.persist(user);
		entityTransaction.commit();
		
	}

	public User getUser(String primaryKey) {
		User userFromTable = entityManager.find(User.class, primaryKey);
		return userFromTable;
	}

	public void removeUser(String userId) {
		User userToDelete =  entityManager.find(User.class, userId);
		
		if(userToDelete != null) {
			entityTransaction.begin();
				entityManager.remove(userToDelete);
			entityTransaction.commit();
		}
		
	}

	public void updateUser(User user) {
		
		User userToUpdate = entityManager.find(User.class, user.getUserId());
		
		if(userToUpdate != null) {
			entityTransaction.begin();
				entityManager.merge(user);
			
			entityTransaction.commit();
		}
	}

}
