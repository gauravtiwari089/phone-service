package com.rest.microservices.phoneservice.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity Class for Phone Resource. Each field will represent column in Phone table.
 *
 * This class has ManyToOneRelation with User table.
 * 
 * 
 * @author Gaurav Tiwari
 *
 */

@Entity
public class Phone {

	@Id
	@GeneratedValue
	private UUID id;
	
	@NotBlank(message = "phone name must not be null or empty")
	private String phoneName;
	
	private PhoneModel phoneModel;
	
	@NotBlank(message = "phone number must not be null or empty")
	private String phoneNumber;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	@JsonIgnore
	private User user;

	public Phone() {
		super();
	}

	public Phone(UUID id, @NotBlank(message = "phone name must not be null or empty") String phoneName,
			PhoneModel phoneModel, @NotBlank(message = "phone number must not be null or empty") String phoneNumber) {
		this.id = id;
		this.phoneName = phoneName;
		this.phoneModel = phoneModel;
		this.phoneNumber = phoneNumber;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getPhoneName() {
		return phoneName;
	}

	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
	}

	public PhoneModel getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(PhoneModel phoneModel) {
		this.phoneModel = phoneModel;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Phone [id=" + id + ", phoneName=" + phoneName + ", phoneModel=" + phoneModel
				+ ", phoneNumber=" + phoneNumber + "]";
	}
	
	
}
