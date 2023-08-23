package com.microservice.authservice.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.authservice.DTO.JwtDTO;
import com.microservice.authservice.DTO.Token;
import com.microservice.authservice.DTO.UserDetailsDTO;
import com.microservice.authservice.services.AuthService;
import com.microservice.authservice.util.JWTUtil;


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
	
	@PostMapping("token")
	public ResponseEntity<Token> authenticateUser(@RequestBody UserDetailsDTO dto) throws Exception {
		
		if(!authService.validateUser(dto)) throw new BadCredentialsException("Please enter valid Details");
		
		
		Token token = authService.generateToken(dto);

		return ResponseEntity.accepted().body(token);
	}
	
	
	@PostMapping("validate/token")
	public Boolean validateToken(@RequestBody JwtDTO dto) throws Exception {
		return authService.validateToken(dto.getJwtToken());
	}

}
