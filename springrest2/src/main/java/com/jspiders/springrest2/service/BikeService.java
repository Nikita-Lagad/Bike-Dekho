package com.jspiders.springrest2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspiders.springrest2.pojo.Bike;
import com.jspiders.springrest2.repository.BikeRepo;

@Service
public class BikeService {
	
	@Autowired
	private BikeRepo bikeRepo;
	
	public Bike addBike(Bike bike) {
		return bikeRepo.addBike(bike);
	}
	
	public List<Bike> findAllBikes(){
		List<Bike> bikes=bikeRepo.findAllBikes();
		if (bikes!=null && bikes.size()>0) {
			return bikes;
		}else {
			return null;
		}
	}
	public Bike deleteBike(int id) {
		return bikeRepo.deleteBike(id);
	}
	public Bike updateBike(Bike bike) {
		return bikeRepo.updateBike(bike);
	}

}
