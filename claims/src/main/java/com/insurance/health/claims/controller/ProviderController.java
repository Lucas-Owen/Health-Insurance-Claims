package com.insurance.health.claims.controller;

import org.springframework.web.bind.annotation.*;

import com.insurance.health.claims.model.Claim;
import com.insurance.health.claims.model.Invoice;

import java.util.HashMap;

@RestController
@RequestMapping("/provider")
public class ProviderController {
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello, I am a provider";
	}
	
	@PostMapping("/claim")
	public String registerClaim(@RequestBody Claim claim) {
		return claim.toString();
	}
	
	@PostMapping("/invoice")
	public String registerInvoice(@RequestBody Invoice invoice) {
		return invoice.toString();
	}

	@PostMapping("/login")
	public String loginUser(@RequestBody HashMap<String, String> user){
		return user.get("username") + user.get("password");
	}
}
