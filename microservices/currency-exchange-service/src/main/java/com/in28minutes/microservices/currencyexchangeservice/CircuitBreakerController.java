package com.in28minutes.microservices.currencyexchangeservice;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class CircuitBreakerController {
	
	@GetMapping("/sample-api")
	public String sampleApi() {
		return "Sample API";
	}
}
