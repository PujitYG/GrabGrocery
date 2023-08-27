package com.microservice.authservice.DTO;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.microservice.authservice.controller.constraints.EmployeeRolesValidation;


//@Validated
public class UserDetailsDTO {
	
	@NotNull
	private String username;
	@NotNull
	private String password;
	@NotNull
	private String email;
	
	@EmployeeRolesValidation
	private List<String> roles;
	
	
	
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
	
	
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "UserDetailsDTO [username=" + username + ", password=" + password + ", email=" + email + "]";
	}
	
	
	
	
}
