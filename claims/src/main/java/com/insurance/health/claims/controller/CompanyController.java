package com.insurance.health.claims.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@GetMapping
	public String sayHello() {
		return "Hello, I am a company";
	}
}
