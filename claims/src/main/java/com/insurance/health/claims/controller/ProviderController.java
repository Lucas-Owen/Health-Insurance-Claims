package com.insurance.health.claims.controller;

import com.insurance.health.claims.dao.ClaimRepo;
import com.insurance.health.claims.dao.InvoiceRepo;
import com.insurance.health.claims.service.ClaimService;
import com.insurance.health.claims.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.insurance.health.claims.model.Claim;
import com.insurance.health.claims.model.Invoice;

@RestController
@RequestMapping("/provider")
public class ProviderController {

	private final ClaimService claimService;
	private final InvoiceService invoiceService;

	@Autowired
	public ProviderController(ClaimService claimService, InvoiceService invoiceService) {
		this.claimService = claimService;
		this.invoiceService = invoiceService;
	}

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello, I am a provider";
	}
	
	@PostMapping("/claim")
	public String registerClaim(@RequestBody Claim claim) {
		claimService.save(claim);
		return claim.toString();
	}
	
	@PostMapping("/invoice")
	public String registerInvoice(@RequestBody Invoice invoice) {
		invoiceService.save(invoice);
		return invoice.toString();
	}
}
