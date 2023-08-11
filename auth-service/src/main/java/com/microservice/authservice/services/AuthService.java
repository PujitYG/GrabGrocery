package com.microservice.authservice.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.authservice.DTO.Token;
import com.microservice.authservice.DTO.UserDetailsDTO;
import com.microservice.authservice.Entity.UserAuthDetails;
import com.microservice.authservice.Entity.Enums.EmployeeRoles;
import com.microservice.authservice.projections.UserDetailsToken;

public interface AuthService {

	
	@Transactional
	public Token generateToken(UserDetailsDTO userDetailsDTO) throws Exception;
	
	@Transactional
	public boolean validateUser(UserDetailsDTO userDetailsDTO) throws Exception;
	
	
	public boolean validateToken(String token);

}
