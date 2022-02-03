package com.rest.microservices.phoneservice.repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.microservices.phoneservice.entity.Phone;
import com.rest.microservices.phoneservice.entity.User;

/**
 * Phone Repository to provide all crud operation on {@link Phone} resource by extending {@link JpaRepository}
 * 
 * 
 * @author Gaurav Tiwari
 *
 */

@Repository
public interface PhoneRepository extends JpaRepository<Phone, UUID>{

	List<Phone> findByUser(User userId) ;
	
}
