package com.rest.microservices.phoneservice.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PhoneModel {
	
	IPHONE("IPHONE"),
	ANDROID("ANDROID"),
	DESK_PHONE("DESK_PHONE"),
	SOFT_PHONE("SOFT_PHONE");
	
	private String phoneModel;

    PhoneModel(String phoneModel){
        this.phoneModel = phoneModel;
    }
	
	@JsonCreator
    public static PhoneModel fromString(String phoneModel) {
        return phoneModel == null ? null : PhoneModel.valueOf(phoneModel.toUpperCase());
    }

    @JsonValue
    public String getPhoneModel() {
        return this.phoneModel.toUpperCase();
    }

}
