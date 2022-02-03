package com.rest.microservices.phoneservice.util;

import com.rest.microservices.phoneservice.entity.User;

public class Utility {
	
	public static User getExpectedUser() {
		final User expectedUser = new User();
		expectedUser.setUserName("test1");
		expectedUser.setPassword("testPassword1");
		expectedUser.setEmailAddress("abc@xyz.com");
		expectedUser.setPreferredPhoneNumber("+3531234567");
		return expectedUser;
	}

}
