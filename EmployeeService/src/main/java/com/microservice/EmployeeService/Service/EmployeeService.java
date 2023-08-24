package com.microservice.EmployeeService.Service;

import com.microservice.EmployeeService.DTO.EmployeeRegistrationDTO;
import com.microservice.EmployeeService.Exception.BadRequestException;

public interface EmployeeService {
	
	public boolean saveEmployee(EmployeeRegistrationDTO employee) throws Exception;

}
