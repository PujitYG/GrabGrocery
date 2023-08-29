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
import com.microservice.EmployeeService.DTO.EmployeeRegistrationStatusEventDTO;
import com.microservice.EmployeeService.DTO.EmployeeServiceResponseDTO;
import com.microservice.EmployeeService.Entity.Employee;
import com.microservice.EmployeeService.Exception.BadRequestException;
import com.microservice.EmployeeService.Model.EmployeeRepository;
import com.microservice.EmployeeService.Service.Enums.EmployeeStatus;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private SuperMarketServiceClient psc;	
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeStatusPublisher publisher;
	
	
//	@CircuitBreaker(name= "supermarket-service", fallbackMethod = "doThis")
//	public String checkSupermarketIsUp() {
//		return psc.isUp().getBody();
//	}
//	
//	private String doThis(RuntimeException e) {
//		return "Wait bro";
//	}
	
	@Transactional(rollbackFor = IllegalArgumentException.class)
	public String saveEmployee(EmployeeRegistrationDTO employee) throws BadRequestException {
		
		if(employee==null) throw new BadRequestException("Bad Request");
		
		Employee emp = new Employee();
		emp.setEmployeeName(employee.getEmployeeName());
		emp.setDob(employee.getDob());
		emp.setEmailId(employee.getEmailId());
		emp.setPhoneNumber(employee.getPhoneNumber());
		emp.setSalary(employee.getSalary());
		emp.setJobDescriptition(employee.getJobDescriptition());
		emp.setShift(employee.getShift());
			
		employeeRepository.save(emp);
		
		EmployeeRegistrationStatusEventDTO employeeEventDTO= new EmployeeRegistrationStatusEventDTO();
		employeeEventDTO.setEmployeeId(emp.getEmployeeId());
		employeeEventDTO.setPassword(employee.getPassword());
		employeeEventDTO.setStatus(EmployeeStatus.CREATED);
		employeeEventDTO.setEmployeeEmail(emp.getEmailId());
		
		publisher.publishEmployeeStatus(employeeEventDTO);
		
		
		String response = String
				.format("Employee: %s registration has been initiated.", emp.getEmployeeName(),emp.getEmployeeId());
		
		return response;
			
	}
	
	
	
}
