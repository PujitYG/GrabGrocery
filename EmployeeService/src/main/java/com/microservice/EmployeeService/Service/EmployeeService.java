package com.microservice.EmployeeService.Service;

import com.microservice.EmployeeService.DTO.EmployeeRegistrationDTO;
import com.microservice.EmployeeService.Exception.BadRequestException;

public interface EmployeeService {
	
	public String saveEmployee(EmployeeRegistrationDTO employee) throws Exception;

}
