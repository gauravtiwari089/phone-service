package com.rest.microservices.phoneservice.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.microservices.phoneservice.entity.User;
import com.rest.microservices.phoneservice.service.UserService;




/**
 * This Controller will provide Rest Interface for the User resource.
 * 
 * API's included in this Controller are 
 * <ul>
 * <li>Add User
 * <li>Delete User
 * <li>Get User List
 * <li>Edit User by updating preferred phone number
 * <li>Get User by Id
 * <li>Add Phone to User
 * <li>Delete User Phone
 * <li>Get Phones by UserId
 * 
 * 
 * 
 * @author Gaurav Tiwari
 *
 */

@RequestMapping("/api/v1")
@RestController
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<User> saveUser(@Valid @RequestBody final User user) {
		logger.info("Add user request received for {} ",  user);
		final User newUser = userService.addUser(user);
		
		final URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(newUser.getId()).toUri();
		
		logger.info("User added successfully {} ", user);
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{userId}")
	public void deleteUser(@PathVariable final String userId) {
		logger.info("Delete user request received for {} ",  userId);
		final UUID userUuid = UUID.fromString(userId);
		userService.deleteUser(userUuid);
		logger.info("User deleted successfully {} ", userId);
	}
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		logger.info("Retrieve all user request received");
		final List<User> users = userService.retrieveAllUsers();
		logger.info("Total {} users retrieved ", users.size());
		return users;
	}
	
	@PutMapping("/users/{userId}")
	public User updatePreferredPhoneNumber(@PathVariable final String userId, @RequestBody final User user) {
		logger.info("Update preffered phone number request received for userId {} & user {} ",  userId, user);
		final UUID userUuid = UUID.fromString(userId);
		final User updatedUser = userService.updateUser(userUuid, user);
		logger.info("UserId {} updated successfully {} ", userId, updatedUser);
		return updatedUser;
	}
	
	@GetMapping("/users/{userId}")
	public EntityModel<User> retrieveUserById(@PathVariable final String userId) {
		logger.info("Retrieve user request received for userId {} ",  userId);
		final UUID userUuid = UUID.fromString(userId);
		final User newUser = userService.retrieveUserById(userUuid);
		
		final EntityModel<User> model = EntityModel.of(newUser);
		
		final WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		
		model.add(linkToUsers.withRel("all-users"));
		logger.info("User successfully found {} ", userId);
		
		return model;
	}
	

}
