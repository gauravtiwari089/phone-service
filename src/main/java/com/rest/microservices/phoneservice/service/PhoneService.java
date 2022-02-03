package com.rest.microservices.phoneservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rest.microservices.phoneservice.entity.Phone;
import com.rest.microservices.phoneservice.exception.PhoneNotFoundException;
import com.rest.microservices.phoneservice.exception.UserNotFoundException;

public interface PhoneService {

	Phone addPhoneToUser(UUID userId, Phone phone);
	
	void deletePhone(UUID phoneId) throws PhoneNotFoundException;
	
	List<Phone> retrievePhonesByUserId(UUID userId)  throws PhoneNotFoundException;
}
