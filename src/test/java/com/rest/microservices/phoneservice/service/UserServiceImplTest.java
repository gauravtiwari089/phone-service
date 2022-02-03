package com.rest.microservices.phoneservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static com.rest.microservices.phoneservice.util.Utility.getExpectedUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.rest.microservices.phoneservice.entity.User;
import com.rest.microservices.phoneservice.exception.UserNotFoundException;
import com.rest.microservices.phoneservice.repository.UserRepository;


@SpringBootTest
public class UserServiceImplTest {
	
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	private static final UUID USER_UUID = UUID.fromString("e280a344-ca81-40c6-ad97-8585e006e3c1");

	@Test
	@DisplayName(value = "Verify add valid user data works fine in service layer")
    public void whenAddUserIsCalled_ThenUserShouldBeAdded() {
    	
		final User expectedUser = getExpectedUser();
    	
    	Mockito.when(userRepository.save(expectedUser)).thenReturn(expectedUser);
    	
    	final User actualUser = userService.addUser(expectedUser);
    	
    	assertEquals(expectedUser, actualUser);
    	
    	Mockito.verify(userRepository, Mockito.times(1)).save(expectedUser);
    	
    }
	
	@Test
	@DisplayName(value = "Verify delete user works fine in service layer")
    public void whenDeleteUserIsCalled_ThenUserShouldBeDeleted() {

		final Optional<User> user = Optional.of(getExpectedUser());		
		
		Mockito.when(this.userRepository.findById(USER_UUID)).thenReturn(user);
		
		Mockito.doNothing().when(userRepository).deleteById(USER_UUID);
    	
    	userService.deleteUser(USER_UUID);
    }
	
	@Test
	@DisplayName(value = "Verify delete user and it throw exception when user doesn't exist")
    public void whenDeleteUserIsCalled_ThenExceptionIsThrownForNoUser() throws UserNotFoundException {
    
		final UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
    		userService.deleteUser(USER_UUID);
        });
    }
	
	@Test
	@DisplayName(value = "Verify retrieve all users works fine in service layer")
    public void whenRetrieveAllUsersIsCalled_ThenSavedUserShouldReturn() {
    	
    	final List<User> userList = new ArrayList<>();
    	userList.add(getExpectedUser());
    	userList.add(getExpectedUser());
    	
    	Mockito.when(userRepository.findAll()).thenReturn(userList);
    	
    	final List<User> actualUser = userService.retrieveAllUsers();
    	
    	assertEquals(userList, actualUser);
    }
	
	@Test
	@DisplayName(value = "Verify update user list works fine in service layer")
    public void whenUpdateUserIsCalled_ThenUserShouldUpdate() throws UserNotFoundException {
    
    	final Optional<User> user = Optional.of(getExpectedUser());
    	final User updateUser = getExpectedUser();
    	updateUser.setId(USER_UUID);
    	
    	Mockito.when(this.userRepository.findById(USER_UUID)).thenReturn(user);
    	Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenReturn(updateUser);    	
    	
    	final User actualUser = userService.updateUser(USER_UUID, updateUser);
    	
    	assertEquals(updateUser, actualUser);
    }
	
	@Test
	@DisplayName(value = "Verify update user and it throw exception when user doesn't exist")
    public void whenUpdateUserIsCalled_ThenExceptionIsThrownForNoUser() throws UserNotFoundException {
    
		final UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
    		userService.updateUser(USER_UUID,  getExpectedUser());
        });
    	
    }
	
	@Test
	@DisplayName(value = "Verify retrieve user by id works fine in service layer")
    public void whenRetrieveUserByIdIsCalled_ThenOneUserShouldReturn() throws UserNotFoundException {
    	
		final Optional<User> user = Optional.of(getExpectedUser());
    	
		Mockito.when(this.userRepository.findById(USER_UUID)).thenReturn(user);
    	
    	final User actualUser = userService.retrieveUserById(USER_UUID);
    	
    	assertEquals(user.get(), actualUser);
    	
    }
	
	@Test
	@DisplayName(value = "Verify get user by id and it throw exception when user doesn't exist")
    public void whenGetUserByIdIsCalled_ThenExceptionIsThrownForNoUser() throws UserNotFoundException {
    	
		final UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
    		userService.retrieveUserById(USER_UUID);
        });
    	
    }
	
	
}
