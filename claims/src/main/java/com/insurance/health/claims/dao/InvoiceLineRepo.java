package com.insurance.health.claims.dao;

import com.insurance.health.claims.model.InvoiceLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceLineRepo extends JpaRepository<InvoiceLine, Long> {
}
