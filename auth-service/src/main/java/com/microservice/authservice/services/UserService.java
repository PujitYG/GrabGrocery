package com.microservice.authservice.services;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.authservice.DTO.UserDetailsDTO;

public interface UserService {

	public UsernamePasswordAuthenticationToken getAuthenticationToken(String user);
	
	
	public boolean userExists(UserDetailsDTO userDetailsDTO);
	
	
	public String addUser(UserDetailsDTO userDetailsDTO);
}
