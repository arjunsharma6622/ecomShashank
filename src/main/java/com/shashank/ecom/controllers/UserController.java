package com.shashank.ecom.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shashank.ecom.Services.UserService;
import com.shashank.ecom.models.User;

@RestController
public class UserController {
	
	UserService userService;
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/user/{id}")
	public User getSingleUser(@PathVariable("id") long id) {
		User SingleUser;  
		SingleUser = userService.getSingleUser(id);
		
		return SingleUser;
	}
	
	@GetMapping("/user")
	public List<User> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return users;
	}
}
