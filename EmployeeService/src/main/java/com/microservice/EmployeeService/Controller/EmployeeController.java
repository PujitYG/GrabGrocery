package com.microservice.EmployeeService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.EmployeeService.DTO.EmployeeRegistrationDTO;
import com.microservice.EmployeeService.DTO.EmployeeServiceResponseDTO;
import com.microservice.EmployeeService.Exception.BadRequestException;
import com.microservice.EmployeeService.Service.EmployeeService;
import com.microservice.EmployeeService.Util.EmployeeServiceUtil;
import com.netflix.discovery.converters.Auto;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

import static org.springframework.web.reactive.function.client.WebClient.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequestMapping("api/employee")
public class EmployeeController {
	
	@Autowired
	private Builder webClientBuilder;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeServiceUtil employeeServiceUtil;

	@GetMapping("")
	public ResponseEntity<String> root() {
		String res =webClientBuilder
		.build()
		.get()
		.uri("http://superMarket-Service/api/supermarket/details/test")
		.retrieve()
		.bodyToMono(String.class)
		.block();
		return ResponseEntity.ok(res);
	}
	
	@GetMapping("check")
	@RateLimiter(name = "rate-limiter1",fallbackMethod = "fallBack")
	public String check(HttpServletRequest request, HttpServletResponse response) {
		System.out.print(request.getHeader("Header-Example"));
		return "hello";
	}
	
	@PostMapping("save")
	public ResponseEntity<EmployeeServiceResponseDTO> saveEmployee(@Valid @RequestBody EmployeeRegistrationDTO employee,
			HttpServletRequest request) throws Exception{
		
		if(employee==null) throw new BadRequestException("Invalid data. Please check your Request");
		
		Boolean value = employeeService.saveEmployee(employee);
		
		EmployeeServiceResponseDTO response = employeeServiceUtil
				.getEmployeeServiceResponseDTO(HttpStatus.ACCEPTED.value(), 
						HttpStatus.ACCEPTED.getReasonPhrase(), value, request.getRequestURI());
		
		return ResponseEntity.ok(response);
	}
	
	
	public String fallBack(Exception e) {
		return "Fall back for rate limiter executed";
	}
}
