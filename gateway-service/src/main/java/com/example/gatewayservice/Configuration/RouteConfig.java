package com.example.gatewayservice.Configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
	
	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("r1",p->p
						.path("/api/employee/**")
						.uri("lb://Employee-Service"))
				.route("r2",p->p
						.path("/auth/**","/user/add")
						.uri("lb://Auth-service"))
				.build();
	}

}
