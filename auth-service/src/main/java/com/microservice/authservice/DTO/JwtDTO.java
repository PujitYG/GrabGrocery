package com.microservice.authservice.DTO;

public class JwtDTO {
	
	
	private String jwtToken;
	
	public JwtDTO() {
	}
	

	public JwtDTO(String token) {
		this.jwtToken=token;
	}
	
	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
		
	

}
