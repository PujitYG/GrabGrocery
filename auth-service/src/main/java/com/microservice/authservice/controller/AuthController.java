package com.microservice.authservice.controller;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.authservice.Config.ApplicationUserDetails;
import com.microservice.authservice.DTO.ExceptionDTO;
import com.microservice.authservice.DTO.Token;
import com.microservice.authservice.DTO.UserDetailsDTO;
import com.microservice.authservice.Exception.UserAlreadyExistException;
import com.microservice.authservice.services.AuthService;
import com.microservice.authservice.util.JWTUtil;
import com.netflix.discovery.converters.Auto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@Value("${jwts.secret}")
	String value;
	
	@Autowired
	JWTUtil jwtutil;
	
	@GetMapping("")
	public String test() {
		return "Hello";
	}
	
	@PostMapping("add/user")
	public ResponseEntity<String> addUser(@RequestBody UserDetailsDTO userDetails) throws UserAlreadyExistException{
		
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
	
	@PostMapping("validate")
	public ResponseEntity<Boolean> validateUser(@RequestBody UserDetailsDTO dto) throws Exception{
		
		boolean message = authService.validateUser(dto);
		
		return ResponseEntity
				.status(200)
				.body(message);
	}
	
	@GetMapping("token")
	public String getSecretValidate() {
		
		return jwtutil.getJwtToken("Pujit", "tf@gmail.com");
	}
	
	
	@PostMapping("validate/token")
	public Boolean validateToken(@RequestBody Token token, Authentication p) throws Exception {
		System.out.println(((ApplicationUserDetails) p.getPrincipal()).getPassword());
		return authService.validateToken(token.getJwtToken());
	}

}
