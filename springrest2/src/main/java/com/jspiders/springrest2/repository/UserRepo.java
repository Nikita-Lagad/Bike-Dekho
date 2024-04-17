package com.jspiders.springrest2.repository;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.jspiders.springrest2.pojo.User;

@Repository
public class UserRepo {
	
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;
	
	public void openConnection() {
		entityManagerFactory=Persistence.createEntityManagerFactory("bike");
		entityManager=entityManagerFactory.createEntityManager();
		entityTransaction=entityManager.getTransaction();
	}
	
	public void closeConnection() {
		if (entityManagerFactory!=null) {
			entityManagerFactory.close();
		}
		if (entityManager!=null) {
			entityManager.close();
		}
		if (entityTransaction!=null) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
		}
	}
	public User addUser(User user) {
		openConnection();
		entityTransaction.begin();
		entityManager.persist(user);
		entityTransaction.commit();
		closeConnection();
		return user;
	}
	
	
	public User validateUser(String userName, String password) {
		openConnection();
		Query query = entityManager.createQuery("SELECT user FROM User user WHERE userName = ?1 AND password = ?2");
		query.setParameter(1, userName);
		query.setParameter(2, password);
		User user = (User) query.getSingleResult();
		return user;
	}
	
	public User updateUser(User user) {
		openConnection();
		User userToBeUpdated=entityManager.find(User.class, user.getId());
		if (userToBeUpdated!=null) {
			userToBeUpdated.setUserName(user.getUserName());
			userToBeUpdated.setEmail(user.getEmail());
			userToBeUpdated.setMobile(user.getMobile());
			userToBeUpdated.setPassword(user.getPassword());
			entityTransaction.begin();
			entityManager.persist(userToBeUpdated);
			entityTransaction.commit();
		}
		closeConnection();
		return userToBeUpdated;
	}
	
	public User deleteUser(int id) {
		openConnection();
		User userToBeDeleted=entityManager.find(User.class, id);
		if (userToBeDeleted!=null) {
			entityTransaction.begin();
			entityManager.remove(userToBeDeleted);
			entityTransaction.commit();
			
		}
		closeConnection();
		return userToBeDeleted;
	}
	
	
}
