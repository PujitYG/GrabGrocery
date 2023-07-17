package com.microservice.SupermarketService.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/supermarket/details")
public class SuperMarketDetailsController {
		
	@GetMapping("test")
	public String test() {
		System.out.println("hello");
		return "SuperMarketDetails";
	}
	
}
