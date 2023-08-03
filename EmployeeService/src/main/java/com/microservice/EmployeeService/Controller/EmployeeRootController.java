package com.microservice.EmployeeService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

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
	@RateLimiter(name = "rate-limiter1",fallbackMethod = "fallBack")
	public String check(HttpServletRequest request, HttpServletResponse response) {
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return "hello";
	}
	
	public String fallBack(Exception e) {
		return "Fall back for rate limiter executed";
	}
}
