package com.microservice.authservice.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.authservice.DTO.Token;
import com.microservice.authservice.DTO.UserDetailsDTO;
import com.microservice.authservice.Entity.UserAuthDetails;
import com.microservice.authservice.Entity.Enums.EmployeeRoles;
import com.microservice.authservice.projections.UserDetailsToken;
import com.microservice.authservice.repository.AuthRepository;
import com.microservice.authservice.util.JWTUtil;
import com.netflix.discovery.converters.Auto;

@Service
public class AuthServiceImp implements AuthService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AuthRepository authRepository;
	
	
	@Autowired
	private JWTUtil jwtUtil;
	
	

	@Transactional
	public Token generateToken(UserDetailsDTO userDetailsDTO) throws Exception {
		Optional<UserDetailsToken> userOptional = authRepository.getUserAddressToken(userDetailsDTO.getUsername());
		
		UserDetailsToken user = userOptional
				.orElseThrow(()->new UsernameNotFoundException("User not found"));

//		user.get
		Token token = jwtUtil.getJwtToken(user);
		
		
		return token;
	}
	
	@Transactional
	public boolean validateUser(UserDetailsDTO userDetailsDTO) throws Exception {
		Boolean userAuthenticated=false;
		Authentication auth = new UsernamePasswordAuthenticationToken(userDetailsDTO.getUsername(),userDetailsDTO.getPassword());
		try {
			userAuthenticated = authenticationManager.authenticate(auth).isAuthenticated();
		}
		catch (UsernameNotFoundException e) {
			System.out.println("User not present "+userDetailsDTO.getUsername());
		}
		catch(BadCredentialsException e) {
			String errorMessage = String.format("User %s provided bad password", userDetailsDTO.getUsername());
			System.out.println(errorMessage);
		}
		return userAuthenticated;
	}
	
	
	public boolean validateToken(String token) {
		return jwtUtil.validateToken(token); 
	}

}
