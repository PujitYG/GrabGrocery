package com.microservice.authservice.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
public class UserAuthDetails {
	
	@Id
	private String username;

	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="EMAIL")
	private String email;
	
	@ElementCollection
	private List<String> Roles;
	
	public UserAuthDetails() {
		super();
	}
	
	


	public UserAuthDetails(String username, String password, String email, List<String> roles) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		Roles = roles;
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
		return Roles;
	}




	public void setRoles(List<String> roles) {
		Roles = roles;
	}




	@Override
	public String toString() {
		return "UserAuthDetails [username=" + username + ", password=" + password + ", email=" + email + "]";
	}
	
	
	

}
