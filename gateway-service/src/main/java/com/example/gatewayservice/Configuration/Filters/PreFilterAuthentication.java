package com.example.gatewayservice.Configuration.Filters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.example.gatewayservice.Utils.JwtUtil;


import reactor.core.publisher.Mono;

@Component
public class PreFilterAuthentication implements GlobalFilter {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	private static String AUTHENTICATION="Authentication";
	
	public List<String> openEndPoints=List.of(
			"/auth/token",
			"/auth/validate/token"
			);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) throws RuntimeException {
		

		if(!openEndPoints(exchange.getRequest())){
			if(!exchange.getRequest().getHeaders().containsKey(AUTHENTICATION)) {
				throw new RuntimeException("Authentication header not present! Access Denied");
			}
			
			return jwtUtil.validateJWT(exchange.getRequest().getHeaders().getFirst(AUTHENTICATION))
					.then(chain.filter(exchange));
			
		}
		
		return chain.filter(exchange);
	}
	
	public boolean openEndPoints(ServerHttpRequest request) {
		return openEndPoints
				.stream()
				.anyMatch(uri->request
						.getPath().toString()
						.equals(uri));
	}

}
