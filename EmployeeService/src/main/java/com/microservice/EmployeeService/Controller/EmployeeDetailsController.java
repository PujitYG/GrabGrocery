package com.microservice.EmployeeService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.EmployeeService.Client.SuperMarketServiceClient;

@RestController
@RequestMapping("api/employee/details")
public class EmployeeDetailsController {
	
	@Autowired
	SuperMarketServiceClient psc;
	
	
	@GetMapping("test")
	public String test() {
		return "EmployeeDetails "+psc.isUp();
	}
	
}
