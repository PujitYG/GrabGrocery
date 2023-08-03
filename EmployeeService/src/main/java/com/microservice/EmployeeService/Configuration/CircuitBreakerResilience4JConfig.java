package com.microservice.EmployeeService.Configuration;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;

import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;


@Configuration
public class CircuitBreakerResilience4JConfig {
	
	   @Bean
	    public CircuitBreakerRegistry circuitBreakerRegistry() {
	        return CircuitBreakerRegistry.ofDefaults();
	    }

	    @Bean
	    public CircuitBreaker defaultCircuitBreaker(CircuitBreakerRegistry circuitBreakerRegistry) {
	        return circuitBreakerRegistry.circuitBreaker("defaultCircuitBreaker");
	    }
	    
	    @Bean
	    public CircuitBreaker customCircuitBreaker(CircuitBreakerRegistry circuitBreakerRegistry) {
	        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
	        		.minimumNumberOfCalls(3)
	        		.slidingWindowType(SlidingWindowType.COUNT_BASED)
	        		.slidingWindowSize(5)
	                .failureRateThreshold(60) // Customize as needed
	                .waitDurationInOpenState(Duration.ofSeconds(5)) // Customize as needed
	                .permittedNumberOfCallsInHalfOpenState(3) // Customize as needed
	                .enableAutomaticTransitionFromOpenToHalfOpen()// Customize as needed
	                .build();

	        return circuitBreakerRegistry.circuitBreaker("supermarket-service",circuitBreakerConfig);
	    }
	    

}
