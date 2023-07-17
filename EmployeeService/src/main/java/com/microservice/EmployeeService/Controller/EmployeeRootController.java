package com.microservice.EmployeeService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.reactive.function.client.WebClient.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("api/employee")
public class EmployeeRootController {
	
	@Autowired
	private Builder webClientBuilder;

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
	public String check(HttpServletRequest request, HttpServletResponse response) {
		return "hello";
	}
	
}
