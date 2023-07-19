package com.microservice.authservice.DTO;

public class Token {
	
	private String jwtToken;

	public Token() {
		super();
	}
	
	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
		

}
