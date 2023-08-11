package com.microservice.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.microservice.authservice.Entity.UserAuthDetails;
import com.microservice.authservice.projections.UserDetailsToken;

@Repository
@EnableJpaRepositories(basePackages = "com.microservice.authservice.Entity")
public interface AuthRepository extends JpaRepository<UserAuthDetails,String> {

	Optional<UserAuthDetails> findByUsernameIgnoreCase(String username);
	
	@Query(value="SELECT u FROM UserAuthDetails as u WHERE u.username= ?1")
	Optional<UserDetailsToken> getUserAddressToken(String username);
	
}

