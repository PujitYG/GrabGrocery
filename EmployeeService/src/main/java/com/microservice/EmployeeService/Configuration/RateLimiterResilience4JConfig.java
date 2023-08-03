package com.microservice.EmployeeService.Configuration;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;

@Configuration
public class RateLimiterResilience4JConfig {
	
	@Bean
	public RateLimiterRegistry rateLimiterRegistry() {
		return RateLimiterRegistry.ofDefaults();
	}
	
	@Bean
	public RateLimiter rateLimiter(RateLimiterRegistry registry) {
		RateLimiterConfig config = RateLimiterConfig.custom()
				.timeoutDuration(Duration.ofSeconds(0))
				.limitForPeriod(2)
				.limitRefreshPeriod(Duration.ofSeconds(10))
				.build();
		
		return registry.rateLimiter("rate-limiter1", config);
		
	}
	
}
