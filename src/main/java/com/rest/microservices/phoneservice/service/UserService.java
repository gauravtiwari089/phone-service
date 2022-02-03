package com.rest.microservices.phoneservice.service;

import java.util.UUID;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rest.microservices.phoneservice.entity.User;
import com.rest.microservices.phoneservice.exception.UserNotFoundException;

public interface UserService {
	
	User addUser(User user);
	
	void deleteUser(UUID userId) throws UserNotFoundException;
	
	List<User> retrieveAllUsers();
	
	User updateUser(UUID userId, User user) throws UserNotFoundException;
	
	User retrieveUserById(UUID userId) throws UserNotFoundException;

}
