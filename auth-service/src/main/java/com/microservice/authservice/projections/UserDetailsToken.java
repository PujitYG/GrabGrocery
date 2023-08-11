package com.microservice.authservice.projections;

import java.util.List;

import com.microservice.authservice.Entity.Enums.EmployeeRoles;


public interface UserDetailsToken {
	
	public String getUsername();
	
	public String getEmail();
	
	public List<EmployeeRoles> getRoles();
	
	
}
