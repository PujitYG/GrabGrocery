package com.microservice.authservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

public class AuthService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public String addUser() {
		return "User Added";
	}
	
	public String generateToken() {
		return "token";
	}
	
	public boolean validateDetails(String user) {
		return true;
	}
	
	public boolean validateToken() {
		return true;
	}

}
