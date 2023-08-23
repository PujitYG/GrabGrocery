package com.microservice.authservice.Config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.authservice.DTO.ExceptionDTO;
import com.microservice.authservice.util.JWTUtil;
import com.netflix.discovery.converters.Auto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;


public class JWTOncePerRequestFilter extends OncePerRequestFilter {
	
	private static String AUTHORIZATION="AUTHORIZATION";

	private JWTUtil jwtUtil;

	private UserDetailsService userDetailsService;
	
	public JWTOncePerRequestFilter(JWTUtil jwtUtil,UserDetailsService userDetailsService) {
		this.jwtUtil=jwtUtil;
		this.userDetailsService=userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		System.out.println("Inside the filter 1");
		
		String authorization = request.getHeader(AUTHORIZATION);
		
		
		if(authorization==null || !jwtUtil.validateToken(authorization)) {
//			filterChain.doFilter(request, response);
//			throw new RuntimeException("Yes jwt not valid");
			ExceptionDTO dto = new ExceptionDTO();
			dto.setError("BAD REQUEST");
			dto.setStatusCode(403);
			dto.setMessage("Invalid Authentication");
			dto.setPath(request.getRequestURL().toString());
//			dto.setTimeStamp(LocalDateTime.now());
			String json = new ObjectMapper().writeValueAsString(dto);
			response.getWriter().write(json);
			response.setContentType(ContentType.APPLICATION_JSON.toString());
			response.setStatus(403);
//			filterChain.doFilter(request, response);
			return;
		}
		
		Jws<Claims> claims=jwtUtil.getClaimsFromToken(authorization);
		String user = claims.getBody().get("name").toString();
				
		
		UsernamePasswordAuthenticationToken authentication=
				getAuthenticationToken(user);
		
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
		
	}
	
	private UsernamePasswordAuthenticationToken getAuthenticationToken(String user) {
		
		UserDetails userDetails=userDetailsService.loadUserByUsername(user);
		
		return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
		
	}

}
