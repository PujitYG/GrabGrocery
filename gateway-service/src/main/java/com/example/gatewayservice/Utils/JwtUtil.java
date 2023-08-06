package com.example.gatewayservice.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.gatewayservice.Client.AuthServiceClient;
import com.example.gatewayservice.Client.AuthServiceClient2;
import com.example.gatewayservice.DTO.JwtDTO;

import reactor.core.publisher.Mono;


@Component
public class JwtUtil {
	
	@Autowired
	AuthServiceClient2 authServiceClient;
	
	public Mono<Boolean> validateJWT(String token) {
		JwtDTO jwt = new JwtDTO(token);
		
		return authServiceClient
		.webClientBuilder()
		.build()
		.post()
		.uri("http://Auth-service/auth/validate/token")
		.bodyValue(jwt)
		.retrieve()
		.bodyToMono(Boolean.class)
		.map(val->{
			if(!val) throw new RuntimeException("Invalid Token");
			return val;
		});
	}

}
