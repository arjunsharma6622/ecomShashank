package com.shashank.ecom.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shashank.ecom.Exceptions.UserNotFoundException;
import com.shashank.ecom.Repository.UserRepository;
import com.shashank.ecom.models.User;

@Service
public class UserService {
	UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User getSingleUser(long id) throws UserNotFoundException {
		Optional<User> GetSingleUserById = userRepository.findById(id);
		User us;
		if(GetSingleUserById.isEmpty()) {
			throw new UserNotFoundException("no user with that id");
			
		}
		else {
			us = GetSingleUserById.get();
		}
		return us;
	}
	
	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();
		
		return users;
	}

}
