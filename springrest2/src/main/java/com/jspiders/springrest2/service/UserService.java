package com.jspiders.springrest2.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspiders.springrest2.pojo.User;
import com.jspiders.springrest2.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	public User addUser(User user) {
		return userRepo.addUser(user);
	}
	
	public User validateUser(String userName, String password) {
		try {
			User user = userRepo.validateUser(userName, password);
			return user;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public User updateUser(User user) {
		return userRepo.updateUser(user);
	}
	
	public User deleteUser(int id) {
		return userRepo.deleteUser(id);
	}
}
