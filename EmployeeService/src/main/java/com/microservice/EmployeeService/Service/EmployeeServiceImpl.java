package com.microservice.EmployeeService.Service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.EmployeeService.Client.SuperMarketServiceClient;
import com.microservice.EmployeeService.DTO.EmployeeRegistrationDTO;
import com.microservice.EmployeeService.DTO.EmployeeServiceResponseDTO;
import com.microservice.EmployeeService.Entity.Employee;
import com.microservice.EmployeeService.Exception.BadRequestException;
import com.microservice.EmployeeService.Model.EmployeeRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	SuperMarketServiceClient psc;	
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	
//	@CircuitBreaker(name= "supermarket-service", fallbackMethod = "doThis")
//	public String checkSupermarketIsUp() {
//		return psc.isUp().getBody();
//	}
//	
//	private String doThis(RuntimeException e) {
//		return "Wait bro";
//	}
	
	@Transactional(rollbackFor = IllegalArgumentException.class)
	public boolean saveEmployee(EmployeeRegistrationDTO employee) throws BadRequestException {
		
		if(employee==null) throw new BadRequestException("Bad Request");
		
		Employee emp = new Employee();
		emp.setEmployeeName(employee.getEmployeeName());
		emp.setDob(employee.getDob());
		emp.setEmailId(employee.getEmailId());
		emp.setPhoneNumber(employee.getPhoneNumber());
			
		employeeRepository.save(emp);
		
		return true;
			
	}
	
	
	
}