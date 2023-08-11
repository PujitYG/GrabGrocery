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

import com.microservice.authservice.DTO.Token;
import com.microservice.authservice.DTO.UserDetailsDTO;
import com.microservice.authservice.Entity.UserAuthDetails;
import com.microservice.authservice.Entity.Enums.EmployeeRoles;
import com.microservice.authservice.projections.UserDetailsToken;
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
		
		Optional<UserAuthDetails> user = authRepository.findByUsernameIgnoreCase(username);
		
		return user.isPresent();
	}
	
	public String addUser(UserDetailsDTO userDetailsDTO) {
		UserAuthDetails user = new UserAuthDetails();
		user.setUsername(userDetailsDTO.getUsername());
		user.setPassword(encoder.encode(userDetailsDTO.getPassword()));
		user.setEmail(userDetailsDTO.getEmail());
		
		List<EmployeeRoles> roles = userDetailsDTO.getRoles()
				.stream()
				.map(val->EmployeeRoles.valueOf(val.toUpperCase()))
				.collect(Collectors.toList());
		
		user.setRoles(roles);
		authRepository.save(user);
		return String.format("User: %s added successfully", userDetailsDTO.getUsername());
	}
	
	public Token generateToken(UserDetailsDTO userDetailsDTO) throws Exception {
		Optional<UserDetailsToken> userOptional = authRepository.getUserAddressToken(userDetailsDTO.getUsername());
		
		UserDetailsToken user = userOptional
				.orElseThrow(()->new UsernameNotFoundException("User not found"));
		
		Token token = jwtUtil.getJwtToken(user);
		
		
		return token;
	}
	
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
