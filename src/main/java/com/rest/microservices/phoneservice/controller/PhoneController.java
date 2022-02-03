package com.rest.microservices.phoneservice.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.microservices.phoneservice.entity.Phone;
import com.rest.microservices.phoneservice.service.PhoneService;

/**
 * This Controller will provide Rest Interface for the Phone resource.
 * 
 * API's included in this Controller are 
 * <ul>
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
public class PhoneController {
	
	private static final Logger logger = LoggerFactory.getLogger(PhoneController.class);
	
	@Autowired
	private PhoneService phoneService;
	
	@PostMapping("/users/{userId}/phones")
	public ResponseEntity<Phone> addPhoneToUser(@PathVariable final String userId, @Valid @RequestBody final Phone phone) {
		logger.info("Add phone to user request received for userId {} and phone {}",  userId, phone);
		final UUID userUuid = UUID.fromString(userId);
		final Phone newPhone = phoneService.addPhoneToUser(userUuid, phone);
		
		final URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newPhone.getId()).toUri();
		logger.info("Phone {} added to userId {} successfully ", newPhone, userId);
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/phones/{id}")
	public void deleteUserPhone(@PathVariable final String id) {
		logger.info("Delete phone request received for phoneId {} ",  id);
		final UUID phoneUuid = UUID.fromString(id);
		phoneService.deletePhone(phoneUuid);
		logger.info("Phone deleted successfully for {} ",  id);
	}
	
	@GetMapping("/users/{userId}/phones")
	public List<Phone> retrievePhonesByUserId(@PathVariable final String userId) {
		logger.info("Retrieve user phone request received for userId {} ",  userId);
		final UUID userUuid = UUID.fromString(userId);
		
		final List<Phone> phones = phoneService.retrievePhonesByUserId(userUuid);
		logger.info("Total {} phones retrived for userId {} ", phones.size(), userId);
		
		return phones;
	}

}
