package com.insurance.health.claims.service;

import com.insurance.health.claims.dao.InvoiceRepo;
import com.insurance.health.claims.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    private final InvoiceRepo invoiceRepo;

    @Autowired
    public InvoiceService(InvoiceRepo invoiceRepo) {
        this.invoiceRepo = invoiceRepo;
    }

    public void save(Invoice invoice){
        invoiceRepo.save(invoice);
    }
}
