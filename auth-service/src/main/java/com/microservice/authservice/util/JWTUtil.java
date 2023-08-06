package com.microservice.authservice.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
		this.secretKey = new SecretKeySpec(Base64.getDecoder().decode(secret),SignatureAlgorithm.HS256.getJcaName());
	}
	
	
	public String getJwtToken(String name,String email) {
		LocalDateTime now = LocalDateTime.now();
		
		return Jwts.builder()
				.setSubject("USER-AUTHENTICATION")
				.setIssuer("Auth-service")
				.claim("name", name)
				.claim("email", email)
				.setIssuedAt(Date.from(now.toInstant(ZoneOffset.UTC)))
				.setExpiration(Date.from(now.plusMinutes(20l).toInstant(ZoneOffset.UTC)))
				.signWith(secretKey)
				.compact();
		
				
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

}
