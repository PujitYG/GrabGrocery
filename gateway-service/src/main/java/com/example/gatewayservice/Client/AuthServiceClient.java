package com.example.gatewayservice.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gatewayservice.DTO.JwtDTO;

@FeignClient(name = "Auth-Service")
public interface AuthServiceClient {

	@PostMapping(path="/validate/token")
	public ResponseEntity<Boolean> validateJWT(@RequestBody JwtDTO token);
	
}
