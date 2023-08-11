package com.microservice.authservice.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.authservice.DTO.UserDetailsDTO;
import com.microservice.authservice.Entity.UserAuthDetails;
import com.microservice.authservice.Entity.Enums.EmployeeRoles;
import com.microservice.authservice.repository.AuthRepository;
import com.microservice.authservice.util.JWTUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private AuthRepository authRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Transactional
	public UsernamePasswordAuthenticationToken getAuthenticationToken(String user) {
		
		UserDetails userDetails=userDetailsService.loadUserByUsername(user);
		
		return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
		
	}
	
	@Transactional
	public boolean userExists(UserDetailsDTO userDetailsDTO) {
		
		Optional<UserAuthDetails> user = authRepository.findByUsernameIgnoreCase(userDetailsDTO.getUsername());
		
		return user.isPresent();
	}
	
	@Transactional
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
	

}
