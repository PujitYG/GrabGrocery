package com.microservice.authservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservice.authservice.DTO.UserDetailsDTO;
import com.microservice.authservice.Entity.UserAuthDetails;
import com.microservice.authservice.repository.AuthRepository;
import com.microservice.authservice.util.JWTUtil;
import com.netflix.discovery.converters.Auto;

@Service
public class AuthService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AuthRepository authRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	public boolean userExists(UserDetailsDTO userDetailsDTO) {
		String username = userDetailsDTO.getUsername();
		
		Optional<UserAuthDetails> user = authRepository.findByUsername(username);
		
		return user.isPresent();
	}
	
	public String addUser(UserDetailsDTO userDetailsDTO) {
		UserAuthDetails user = new UserAuthDetails();
		user.setUsername(userDetailsDTO.getUsername());
		user.setPassword(encoder.encode(userDetailsDTO.getPassword()));
		user.setEmail(userDetailsDTO.getEmail());
		authRepository.save(user);
		return String.format("User: %s added successfully", userDetailsDTO.getUsername());
	}
	
	public String generateToken(UserDetailsDTO userDetailsDTO) throws Exception {
//		if(!validateUser(userDetailsDTO)) throws new 
		return "Hello";
	}
	
	public boolean validateUser(UserDetailsDTO userDetailsDTO) throws Exception {
		Authentication auth = new UsernamePasswordAuthenticationToken(userDetailsDTO.getUsername(),userDetailsDTO.getPassword());
		auth = authenticationManager.authenticate(auth);
		return auth.isAuthenticated();
	}
	
	public boolean validateToken(String token) {
		return jwtUtil.validateToken(token);
	}

}
