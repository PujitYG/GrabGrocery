package com.microservice.EmployeeService.Client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.web.reactive.function.client.WebClient.*;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;

@Configuration
public class SuperMarketServiceClient2 {
	
	@Bean
	@LoadBalanced
	public Builder webClientBuilder(){
		return builder();
	}

}
