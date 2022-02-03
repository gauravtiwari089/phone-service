package com.rest.microservices.phoneservice.repository;

import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.microservices.phoneservice.entity.User;

/**
 * User Repository to provide all crud operation on {@link User} resource by extending {@link JpaRepository}
 * 
 * 
 * @author Gaurav Tiwari
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

}
