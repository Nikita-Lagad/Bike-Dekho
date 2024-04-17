package com.jspiders.springrest2.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="user2")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String userName;
	@Column(nullable = false,unique=true)
	private String email;
	@Column(nullable = false)
	private long mobile;
	@Column(nullable = false,unique=true)
	private String password;

}
