package com.example.gatewayservice.Configuration.Filters;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.example.gatewayservice.Utils.JwtUtil;


import reactor.core.publisher.Mono;

@Component
public class PreFilterAuthentication implements GlobalFilter {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	private static String AUTHENTICATION="AUTHORIZATION";
	
	private Set<String> openEndPoints=Set.of(
			"/auth/token",
			"/auth/validate/token"
			);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) throws RuntimeException {
		
		ServerHttpResponse response = exchange.getResponse();
		
		if(!openEndPoints(exchange.getRequest())){
			if(!exchange.getRequest().getHeaders().containsKey(AUTHENTICATION)) {
				response.setStatusCode(HttpStatus.UNAUTHORIZED);
				DataBuffer buff = response.bufferFactory().wrap("Authentication header not present! Access Denied".getBytes());

				return response.writeWith(Mono.just(buff));
			}
			
			return jwtUtil
					.validateJWT(exchange.getRequest().getHeaders().getFirst(AUTHENTICATION))
					.flatMap(value->{
						if(!value) {
							response.setStatusCode(HttpStatus.UNAUTHORIZED);
							DataBuffer buff = response.bufferFactory().wrap("Access Denied: Token not valid".getBytes());
							return response.writeWith(Mono.just(buff));
						}
						return chain.filter(exchange);
					}); 
			
		}
		
		return chain.filter(exchange);
	}
	
	public boolean openEndPoints(ServerHttpRequest request) {
		return openEndPoints
				.contains(request
						.getPath()
						.toString()
						.toLowerCase());

//		return openEndPoints
//				.stream()
//				.anyMatch(uri->request
//						.getPath().toString()
//						.equals(uri));
	}

}
