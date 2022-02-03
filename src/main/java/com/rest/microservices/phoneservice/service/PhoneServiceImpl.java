package com.rest.microservices.phoneservice.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.rest.microservices.phoneservice.entity.Phone;
import com.rest.microservices.phoneservice.entity.User;
import com.rest.microservices.phoneservice.exception.PhoneNotFoundException;
import com.rest.microservices.phoneservice.exception.UserNotFoundException;
import com.rest.microservices.phoneservice.repository.PhoneRepository;
import com.rest.microservices.phoneservice.repository.UserRepository;

/**
 * Service layer implementation to provide save/delete/retrieve/update on {@link Phone} resource.
 * 
 * 
 * @author Gaurav Tiwari
 *
 */

@Service
public class PhoneServiceImpl implements PhoneService {
	
	private static final Logger logger = LoggerFactory.getLogger(PhoneServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PhoneRepository phoneRepository;

	@Override
	public Phone addPhoneToUser(UUID userId, Phone phone) {
		final User userFromDB = getUserFromDB(userId);
		
		phone.setUser(userFromDB);
		logger.debug("Going to save phone in database {} ", phone);
		return phoneRepository.save(phone);
	}

	@Override
	public void deletePhone(UUID id) throws PhoneNotFoundException {
		final Optional<Phone> phoneOptional = phoneRepository.findById(id);
		if(!phoneOptional.isPresent()) {
			logger.error("Phone doesn't exist {} ", id);
			throw new PhoneNotFoundException();
		}
		
		phoneRepository.delete(phoneOptional.get());
	}

	@Override
	public List<Phone> retrievePhonesByUserId(UUID userId)  throws PhoneNotFoundException {
		final User userFromDB = getUserFromDB(userId);
		final List<Phone> phones = phoneRepository.findByUser(userFromDB);
		if(phones.isEmpty()) {
			logger.error("Going to save phone in database for user {} ", userId);
			throw new PhoneNotFoundException();
		}
		
		return phones;
	}
	
	private User getUserFromDB(final UUID userId) {
		final Optional<User> userOptional = userRepository.findById(userId);
		if(!userOptional.isPresent()) {
			logger.error("User doesn't exist {} ", userId);
			throw new UserNotFoundException();
		}
		
		return userOptional.get();
	}

	

}
