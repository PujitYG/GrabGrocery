package com.microservice.authservice.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microservice.authservice.DTO.ExceptionDTO;
import com.microservice.authservice.DTO.UserDetailsDTO;
import com.microservice.authservice.Exception.UserAlreadyExistException;
import com.microservice.authservice.services.AuthService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	AuthService authService;
	
	@PostMapping("add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> addUser(@Valid @RequestBody UserDetailsDTO userDetails) throws UserAlreadyExistException{
		
		ExceptionDTO dto = new ExceptionDTO();
		dto.setError("Forbidden");
		dto.setMessage("user already exists");
		dto.setTimeStamp(LocalDateTime.now());
		dto.setStatusCode(401);
		
		if(authService.userExists(userDetails)) throw new UserAlreadyExistException(dto);
		
		String message = authService.addUser(userDetails);
		
		return ResponseEntity
				.status(200)
				.body(message);
	}

}
