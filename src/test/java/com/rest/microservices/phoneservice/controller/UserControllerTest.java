package com.rest.microservices.phoneservice.controller;

import static com.rest.microservices.phoneservice.util.Utility.getExpectedUser;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.rest.microservices.phoneservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.microservices.phoneservice.entity.User;



@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	private final static String uuid = "e280a344-ca81-40c6-ad97-8585e006e3c1";
	
	@Test
	@DisplayName(value = "Verify add user rest interface is working fine")
	public void WhenAddUserIsCalled_ThenAddUserRestInterfaceIsCalled_ReturnUserObject() throws Exception {
		
		final User expectedUser = getExpectedUser();
		Mockito.when(userService.addUser(Mockito.any(User.class))).thenReturn(expectedUser);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(expectedUser)))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test	  
	@DisplayName(value = "Verify delete User rest interface is working fine")
	public void WhenDeleteUserIsCalled_ThenDeleteUserRestInterfaceIsCalled_ReturnSuccessMessage() throws Exception {

		UUID userUuid = UUID.randomUUID();
		Mockito.doNothing().when(userService).deleteUser(userUuid);

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/e280a344-ca81-40c6-ad97-8585e006e3c1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()); 
	}

	@Test
	@DisplayName(value = "Verify fetch User List rest interface is working fine")
	public void WhenFetchUserListIsCalled_ThenfetchUserListRestInterfaceIsCalled_ReturnListOfUserObject() throws Exception {

		final List<User> userList = new ArrayList<>(); 
		userList.add(getExpectedUser()); 
		userList.add(getExpectedUser());

		Mockito.when(userService.retrieveAllUsers()).thenReturn(userList);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()); 
	}

	@Test
	@DisplayName(value = "Verify update preferred phone number rest interface is working fine") 
	public void WhenUpdateUserIsCalled_ThenUpdateUserRestInterfaceIsCalled_ReturnEditedUserObject() throws Exception {

		final User expectedUser = getExpectedUser();
		expectedUser.setId(UUID.fromString("e280a344-ca81-40c6-ad97-8585e006e3c1"));
		Mockito.when(userService.updateUser(Mockito.any(UUID.class), Mockito.any(User.class))).thenReturn(expectedUser);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/e280a344-ca81-40c6-ad97-8585e006e3c1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(expectedUser)))
				.andExpect(MockMvcResultMatchers.status().isOk()); 
	}


	@Test
	@DisplayName(value = "Verify get User by id rest interface is working fine")
	public void WhenRetrieveUserByIdIsCalled_ThenRetrieveUserByIdRestInterfaceIsCalled_ReturnUserObject () throws Exception {

		final User expectedUser = getExpectedUser();
		Mockito.when(userService.retrieveUserById(UUID.fromString("e280a344-ca81-40c6-ad97-8585e006e3c1"))).thenReturn(expectedUser);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/e280a344-ca81-40c6-ad97-8585e006e3c1")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
