package com.rest.microservices.phoneservice.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.microservices.phoneservice.entity.User;
import com.rest.microservices.phoneservice.exception.UserNotFoundException;
import com.rest.microservices.phoneservice.repository.UserRepository;

/**
 * Service layer implementation to provide save/delete/retrieve/update on {@link User} resource.
 * 
 * 
 * @author Gaurav Tiwari
 *
 */

@Service
public class UserServiceImpl implements UserService {
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(UUID userId) throws UserNotFoundException {

		final User userFromDB = getUserFromDB(userId);
		
		logger.debug("User found in database {} ", userFromDB);
		
		userRepository.deleteById(userFromDB.getId());
		
	}

	@Override
	public List<User> retrieveAllUsers() {
		final List<User> users = userRepository.findAll();
		users.stream().forEach(u -> u.setPassword("*********"));
		return users;
	}
	
	@Override
	public User updateUser(UUID userId, User user) throws UserNotFoundException {
		final User userFromDB = getUserFromDB(userId);
		
		if(Objects.nonNull(user.getPreferredPhoneNumber()) && 
				!"".equalsIgnoreCase(user.getPreferredPhoneNumber())) {
			userFromDB.setPreferredPhoneNumber(user.getPreferredPhoneNumber());
		}
		logger.debug("Going to update preffered phone number in database {} ", userFromDB);
		return userRepository.save(userFromDB);
		
	}
	
	@Override
	public User retrieveUserById(UUID userId) {
		return getUserFromDB(userId);
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
