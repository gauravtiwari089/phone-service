package com.rest.microservices.phoneservice.entity;

import java.util.UUID;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
/**
 * Entity Class for User Resource. Each field will represent column in User table.
 *
 * This class has OneToManyRelation with Phone table.
 * 
 * 
 * @author Gaurav Tiwari
 *
 */

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private UUID userId;
	
	@NotBlank(message = "user name must not be null or empty")
	private String userName;
	
	@NotBlank(message = "password must not be null or empty")
	private String password;
	
	@Email(message = "Email Address format is not correct")
	private String emailAddress;
	
	@NotBlank(message = "preferred phone number must not be null or empty")
	private String preferredPhoneNumber;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Phone> phones;

	public User() {
		super();
	}

	public User(UUID userId, @NotBlank(message = "user name must not be null or empty") String userName,
			@NotBlank(message = "password must not be null or empty") String password,
			@Email(message = "Email Address format is not correct") String emailAddress,
			@NotBlank(message = "preferred phone number must not be null or empty") String preferredPhoneNumber) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.emailAddress = emailAddress;
		this.preferredPhoneNumber = preferredPhoneNumber;
	}

	public UUID getId() {
		return userId;
	}

	public void setId(UUID userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPreferredPhoneNumber() {
		return preferredPhoneNumber;
	}

	public void setPreferredPhoneNumber(String preferredPhoneNumber) {
		this.preferredPhoneNumber = preferredPhoneNumber;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", emailAddress="
				+ emailAddress + ", preferredPhoneNumber=" + preferredPhoneNumber + ", phones=" + phones + "]";
	}
	

}
