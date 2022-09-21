package com.insurance.health.claims.dao;

import com.insurance.health.claims.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepo extends JpaRepository<Invoice, Long> {
	
}
