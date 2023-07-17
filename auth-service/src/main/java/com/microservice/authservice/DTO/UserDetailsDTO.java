package com.microservice.authservice.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;


public class UserDetailsDTO {
	private String username;
	private String password;
	private String email;
	
	
	
	public UserDetailsDTO() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "UserDetailsDTO [username=" + username + ", password=" + password + ", email=" + email + "]";
	}
	
	
	
	
}
