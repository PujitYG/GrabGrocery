package com.example.gatewayservice.Configuration;



import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;


@Configuration
public class Resilience4jCircuitBreakerConfig {
	
	
	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> factory() {
		
		CircuitBreakerRegistry registry = CircuitBreakerRegistry.ofDefaults();
		
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
        		.minimumNumberOfCalls(3)
        		.slidingWindowType(SlidingWindowType.COUNT_BASED)
        		.slidingWindowSize(5)
                .failureRateThreshold(60) 
                .waitDurationInOpenState(Duration.ofSeconds(5)) 
                .permittedNumberOfCallsInHalfOpenState(3)
                .enableAutomaticTransitionFromOpenToHalfOpen()
                .build();
		
//		return factory -> factory
//				.configure(builder-> builder.circuitBreakerConfig(circuitBreakerConfig), "CB-1");
        
        return factory-> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
        		.circuitBreakerConfig(circuitBreakerConfig).build());
	}

}
