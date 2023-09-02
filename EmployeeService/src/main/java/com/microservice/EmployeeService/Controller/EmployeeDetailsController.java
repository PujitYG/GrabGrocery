package com.microservice.EmployeeService.Controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.EmployeeService.Client.SuperMarketServiceClient;
import com.microservice.EmployeeService.Entity.Employee;
import com.microservice.EmployeeService.Entity.Enums.Shift;
import com.microservice.EmployeeService.Service.EmployeeService;
import com.microservice.EmployeeService.Service.EmployeeStatusPublisher;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("api/employee/details")
public class EmployeeDetailsController {
		
	@Autowired
	EmployeeStatusPublisher employeePub;
	
	@GetMapping("test")
	public String test() {
		Employee emp = new Employee();
		emp.setEmployeeName("Pujit");
		emp.setDob(LocalDate.now());
		emp.setEmailId("pujit@asd");
		emp.setPhoneNumber("123234");
		emp.setSalary(1213123);
		emp.setJobDescriptition(null);
		emp.setShift(Shift.MORNING);
		
		employeePub.publishEmployeeStatusCreation(emp, "hello1232");
		
		return "hello";
	}
	

	
}
