package com.microservice.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.authservice.Entity.UserAuthDetails;

@Repository
public interface AuthRepository extends JpaRepository<UserAuthDetails,String> {

	Optional<UserAuthDetails> findByUsername(String username);
	
}

