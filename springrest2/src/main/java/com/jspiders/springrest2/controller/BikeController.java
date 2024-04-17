package com.jspiders.springrest2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.springrest2.pojo.Bike;
import com.jspiders.springrest2.response.ResponseStructure;
import com.jspiders.springrest2.service.BikeService;

@RestController
public class BikeController {
	
	@Autowired
	private BikeService bikeService;
	
	@PostMapping(path="/bike")
	public ResponseEntity<ResponseStructure<Bike>> addBike(@RequestBody Bike bike) {
		Bike addedBike=bikeService.addBike(bike);
		ResponseStructure<Bike> responseStructure=new ResponseStructure<Bike>();
		responseStructure.setMessage("Bike Added");
		responseStructure.setData(addedBike);
		responseStructure.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Bike>>(responseStructure,HttpStatus.OK);	
	}
	
	@GetMapping(path="/bikes")
	public ResponseEntity<ResponseStructure<List<Bike>>> findAllBikes(){
		List<Bike> bikes=bikeService.findAllBikes();
		ResponseStructure< List<Bike>> responseStructure=new ResponseStructure<List<Bike>>();
		if (bikes!=null) {
			responseStructure.setMessage("Car Found");
			responseStructure.setData(bikes);
			responseStructure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Bike>>>(responseStructure,HttpStatus.FOUND);
		}else {
			responseStructure.setMessage("Car Found");
			responseStructure.setData(bikes);
			responseStructure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Bike>>>(responseStructure,HttpStatus.FOUND);
		}
	}
	@DeleteMapping(path="/bike")
	public ResponseEntity<ResponseStructure<Bike>> deleteBike(@RequestParam(name="id") int id){
		Bike deletedCar=bikeService.deleteBike(id);
		ResponseStructure<Bike> responseStructure=new ResponseStructure<Bike>();
		if (deletedCar!=null) {
			responseStructure.setMessage("Bike Deleted");
			responseStructure.setData(deletedCar);
			responseStructure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Bike>>(responseStructure,HttpStatus.OK);
		}else {
			responseStructure.setMessage("Bike Not Found");
			responseStructure.setData(deletedCar);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<Bike>>(responseStructure,HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping(path="/bike")
	public ResponseEntity<ResponseStructure<Bike>> updateCar(@RequestBody Bike bike){
		Bike updatedBike=bikeService.updateBike(bike);
		ResponseStructure<Bike> responseStructure=new ResponseStructure<Bike>();
		if (updatedBike!=null) {
			responseStructure.setMessage("Bike Updated");
			responseStructure.setData(updatedBike);
			responseStructure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Bike>>(responseStructure,HttpStatus.OK);
		}else {
			responseStructure.setMessage("Bike Not Found");
			responseStructure.setData(updatedBike);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<Bike>>(responseStructure,HttpStatus.NOT_FOUND);
		}
	}
}
