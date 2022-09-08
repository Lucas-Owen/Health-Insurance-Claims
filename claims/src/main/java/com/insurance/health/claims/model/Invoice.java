package com.insurance.health.claims.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Invoice {
	private String providerName;
	private String invoiceId;
	private String beneficiaryFirstName;
	private String beneficiaryMiddleName;
	private String beneficiaryLastName;
	private int beneficiaryId;
	private double amount;
	private ArrayList<InvoiceLine> invoiceLines;
	
	public Invoice(@JsonProperty("providerName") String providerName, 
			@JsonProperty("invoiceId") String invoiceId, 
			@JsonProperty("beneficiaryFirstName") String beneficiaryFirstName, 
			@JsonProperty("beneficiaryMiddleName") String beneficiaryMiddleName,
			@JsonProperty("beneficiaryLastName") String beneficiaryLastName, 
			@JsonProperty("beneficiaryId") int beneficiaryId, 
			@JsonProperty("invoiceGrossAmount") double amount,
			@JsonProperty("invoiceLines") ArrayList<InvoiceLine> invoiceLines) {
		
		this.providerName = providerName;
		this.invoiceId = invoiceId;
		this.beneficiaryFirstName = beneficiaryFirstName;
		this.beneficiaryMiddleName = beneficiaryMiddleName;
		this.beneficiaryLastName = beneficiaryLastName;
		this.beneficiaryId = beneficiaryId;
		this.amount = amount;
		this.invoiceLines = invoiceLines;
	}

	public ArrayList<InvoiceLine> getInvoiceLines() {
		return invoiceLines;
	}

	public void setInvoiceLines(ArrayList<InvoiceLine> invoiceLines) {
		this.invoiceLines = invoiceLines;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getBeneficiaryFirstName() {
		return beneficiaryFirstName;
	}

	public void setBeneficiaryFirstName(String beneficiaryFirstName) {
		this.beneficiaryFirstName = beneficiaryFirstName;
	}

	public String getBeneficiaryMiddleName() {
		return beneficiaryMiddleName;
	}

	public void setBeneficiaryMiddleName(String beneficiaryMiddleName) {
		this.beneficiaryMiddleName = beneficiaryMiddleName;
	}

	public String getBeneficiaryLastName() {
		return beneficiaryLastName;
	}

	public void setBeneficiaryLastName(String beneficiaryLastName) {
		this.beneficiaryLastName = beneficiaryLastName;
	}

	public int getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(int beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	@Override
	public String toString() {
		return "Invoice [providerName=" + providerName + ", invoiceId=" + invoiceId + ", beneficiaryFirstName="
				+ beneficiaryFirstName + ", beneficiaryMiddleName=" + beneficiaryMiddleName + ", beneficiaryLastName="
				+ beneficiaryLastName + ", beneficiaryId=" + beneficiaryId + ", amount=" + amount + ", invoiceLines="
				+ invoiceLines + "]";
	}
	
}
