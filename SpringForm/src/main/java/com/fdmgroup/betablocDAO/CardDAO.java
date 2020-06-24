package com.fdmgroup.betablocDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.fdmgroup.betablocentities.Card;

public class CardDAO {
	
	
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SpringForm");
	private EntityManager entityManager = emf.createEntityManager();
	private EntityTransaction entityTransaction = entityManager.getTransaction();
			

	public List<Card> listCards() {
		String jpql = "SELECT c FROM cards c";
		TypedQuery<Card> cardQuery = entityManager.createQuery(jpql,Card.class);
		
		List<Card> cardsFromTable = cardQuery.getResultList();
		
		return cardsFromTable;
	}
	
	public List<Card> listCardsForAUser(String userId) {
		String jpql = "SELECT c FROM cards c WHERE c.user.userId =:userId";
		TypedQuery<Card> cardQuery = entityManager.createQuery(jpql,Card.class);
		cardQuery.setParameter("userId", userId);
		List<Card> cardsFromTable = cardQuery.getResultList();
		return cardsFromTable;
	}

	public void addCard(Card card) {
		entityTransaction.begin();
			entityManager.persist(card);
		entityTransaction.commit();
		
	}

	public Card getCard(String primaryKey) {
		Card cardFromTable = entityManager.find(Card.class, primaryKey);
		return cardFromTable;
	}

	public void removeCard(String primaryKey) {
		Card cardToDelete =  entityManager.find(Card.class, primaryKey);
		
		if(cardToDelete != null) {
			entityTransaction.begin();
				entityManager.remove(cardToDelete);
			entityTransaction.commit();
		}
		
	}

	public void updateCard(Card card) {
		
		Card cardToUpdate = entityManager.find(Card.class, card.getCardId());
		
		if(cardToUpdate != null) {
			entityTransaction.begin();
				entityManager.merge(card);
			
			entityTransaction.commit();
		}
	}

}
