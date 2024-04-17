package com.jspiders.springrest2.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.jspiders.springrest2.pojo.Bike;

@Repository
public class BikeRepo {
	
	private EntityManagerFactory  entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;
	
	private void openConnection() {
		entityManagerFactory=Persistence.createEntityManagerFactory("bike");
		entityManager=entityManagerFactory.createEntityManager();
		entityTransaction=entityManager.getTransaction();
	}
	
	private void closeConnection() {
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
	
	public Bike addBike(Bike bike) {
		openConnection();
		entityTransaction.begin();
		entityManager.persist(bike);
		entityTransaction.commit();
		closeConnection();
		return bike;
	}

	public List<Bike> findAllBikes(){
		openConnection();
		Query query=entityManager.createQuery("SELECT bike FROM Bike bike");
		List<Bike> bikes=query.getResultList();
		closeConnection();
		return bikes;
	}
	public Bike deleteBike(int id) {
		openConnection();
		Bike bikeToBeDeleted=entityManager.find(Bike.class, id);
		entityTransaction.begin();
		if (bikeToBeDeleted!=null) {
			entityManager.remove(bikeToBeDeleted);
		}
		entityTransaction.commit();
		closeConnection();
		return bikeToBeDeleted;
	}
	public Bike updateBike(Bike bike) {
		openConnection();
		Bike bikeToBeUpdated=entityManager.find(Bike.class, bike.getId());
		entityTransaction.begin();
		if (bikeToBeUpdated!=null) {
			bikeToBeUpdated.setName(bike.getName());
			bikeToBeUpdated.setColor(bike.getColor());
			bikeToBeUpdated.setPrice(bike.getPrice());
			entityManager.persist(bikeToBeUpdated);
		}
		entityTransaction.commit();
		closeConnection();
		return bikeToBeUpdated;
		
	}
}
