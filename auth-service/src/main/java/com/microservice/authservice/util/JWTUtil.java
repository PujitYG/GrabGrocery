package com.microservice.authservice.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.microservice.authservice.DTO.Token;
import com.microservice.authservice.Entity.UserAuthDetails;
import com.microservice.authservice.projections.UserDetailsToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException; 

@Component
public class JWTUtil {
	

	private SecretKeySpec secretKey;
	
	@Autowired
	public JWTUtil(@Value("${jwts.secret}") String secret) {
		this.secretKey = new SecretKeySpec(secret.getBytes(),SignatureAlgorithm.HS256.getJcaName());
	}
	
	
	public Token getJwtToken(UserDetailsToken user) {
		LocalDateTime now = LocalDateTime.now();
		
		String jwtToken = Jwts.builder()
				.setSubject("USER-AUTHENTICATION")
				.setIssuer("Auth-service")
				.claim("name", user.getUsername())
				.claim("email", user.getEmail())
				.claim("roles", user.getRoles())
				.setIssuedAt(Date.from(now.toInstant(ZoneOffset.UTC)))
				.setExpiration(Date.from(now.plusMinutes(20l).toInstant(ZoneOffset.UTC)))
				.signWith(secretKey)
				.compact();
		
		return Token.buider()
				.username(user.getUsername())
				.email(user.getEmail())
				.jwt(jwtToken)
				.build();
		
				
	}
	
	public boolean validateToken(String token) throws SignatureException{
		try{
			Jwts.parserBuilder()
						.setSigningKey(secretKey)
						.build().parse(token);
		}catch (Exception e) {
			return false;
		}
		
		return true;

	}
	
	public Jws<Claims> getClaimsFromToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build().parseClaimsJws(token);
	}

}
