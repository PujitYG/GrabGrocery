package com.microservice.EmployeeService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.EmployeeService.Client.SuperMarketServiceClient;
import com.microservice.EmployeeService.Entity.Employee;
import com.microservice.EmployeeService.Model.EmployeeRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class EmployeeDetailsService {
	
	@Autowired
	SuperMarketServiceClient psc;	
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	
	@CircuitBreaker(name= "supermarket-service", fallbackMethod = "doThis")
	public String checkSupermarketIsUp() {
		return psc.isUp().getBody();
	}
	
	private String doThis(RuntimeException e) {
		return "Wait bro";
	}
	
	@Transactional
	public String check() {
		employeeRepository.findAll();
		return "hello";
	}
	
	
}
