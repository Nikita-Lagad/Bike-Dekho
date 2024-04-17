package com.jspiders.springrest2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jspiders.springrest2.pojo.User;
import com.jspiders.springrest2.response.ResponseStructure;
import com.jspiders.springrest2.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<ResponseStructure<User>> addUser(@RequestBody User user){
		User addedUser=userService.addUser(user);
		ResponseStructure<User> responseStructure=new ResponseStructure<User>();
		if (addedUser!=null) {
			responseStructure.setMessage("User Added");
			responseStructure.setData(addedUser);
			responseStructure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
		}else {
			responseStructure.setMessage("User already Exists");
			responseStructure.setData(addedUser);
			responseStructure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping("/user")
	public ResponseEntity<ResponseStructure<User>> validateUser(@RequestParam(name = "userName") String userName,
			@RequestParam(name = "password") String password) {
		User user = userService.validateUser(userName, password);
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		if (user != null) {
			responseStructure.setMessage("User found");
			responseStructure.setData(user);
			responseStructure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.FOUND);
		} else {
			responseStructure.setMessage("User not found. Invalid username or password");
			responseStructure.setData(user);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/user")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user){
		User updatedUser=userService.updateUser(user);
		ResponseStructure<User> responseStructure=new ResponseStructure<User>();
		if (updatedUser!=null) {
			responseStructure.setMessage("User Updated");
			responseStructure.setData(updatedUser);
			responseStructure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
		}else {
			responseStructure.setMessage("User Updated");
			responseStructure.setData(updatedUser);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/user")
	public ResponseEntity<ResponseStructure<User>> deleteUser(@RequestParam(name="id") int id){
		User deletedUser=userService.deleteUser(id);
		ResponseStructure<User> responseStructure=new ResponseStructure<User>();
		if (deletedUser!=null) {
			responseStructure.setMessage("User Deleted");
			responseStructure.setData(deletedUser);
			responseStructure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
		}else {
			responseStructure.setMessage("User Not Found");
			responseStructure.setData(deletedUser);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.NOT_FOUND);
		}
	}
}
