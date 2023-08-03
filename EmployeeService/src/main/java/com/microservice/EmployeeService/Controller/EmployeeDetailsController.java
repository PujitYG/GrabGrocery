package com.microservice.EmployeeService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.EmployeeService.Client.SuperMarketServiceClient;
import com.microservice.EmployeeService.Service.EmployeeDetailsService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("api/employee/details")
public class EmployeeDetailsController {
	
	@Autowired
    private EmployeeDetailsService eds;
	

	@GetMapping("test")
	public String test() {
		eds.check();
		return "EmployeeDetails ";
	}
	

	
}
