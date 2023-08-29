package com.microservice.authservice.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.microservice.authservice.Entity.Enums.EmployeeRoles;

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
	
	@Column(name="ACTIVE")
	private boolean active;
	
	@ElementCollection
	@Enumerated(EnumType.STRING)
	private List<EmployeeRoles> roles;
	
	public UserAuthDetails() {
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<EmployeeRoles> getRoles() {
		return roles;
	}

	public void setRoles(List<EmployeeRoles> roles) {
		this.roles = roles;
	}
	
	

	

}
