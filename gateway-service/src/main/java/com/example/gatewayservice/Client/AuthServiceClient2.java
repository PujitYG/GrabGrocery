package com.example.gatewayservice.Client;

import static org.springframework.web.reactive.function.client.WebClient.builder;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient.Builder;

@Configuration
public class AuthServiceClient2 {
//	@Bean
//	@LoadBalanced
//	public RestTemplate restBuilder(){
//		return new RestTemplate();
//	}
	
	@Bean
	@LoadBalanced
	public Builder webClientBuilder(){
		return builder();
	}
}
