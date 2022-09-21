package com.insurance.health.claims.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Invoice {
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "invoice_seq"
	)
	@SequenceGenerator(
			name = "invoice_seq",
			allocationSize = 1
	)
	private Long invoiceId;
	private String providerName;
	private String invoiceReference;
	private String beneficiaryFirstName;
	private String beneficiaryMiddleName;
	private String beneficiaryLastName;
	private int beneficiaryId;
	private double amount;

	@OneToMany(targetEntity = InvoiceLine.class, cascade = CascadeType.ALL)
	private List<InvoiceLine> invoiceLines;
	
	public Invoice(@JsonProperty("providerName") String providerName, 
			@JsonProperty("invoiceReference") String invoiceReference,
			@JsonProperty("beneficiaryFirstName") String beneficiaryFirstName, 
			@JsonProperty("beneficiaryMiddleName") String beneficiaryMiddleName,
			@JsonProperty("beneficiaryLastName") String beneficiaryLastName, 
			@JsonProperty("beneficiaryId") int beneficiaryId, 
			@JsonProperty("invoiceGrossAmount") double amount,
			@JsonProperty("invoiceLines") ArrayList<InvoiceLine> invoiceLines) {
		
		this.providerName = providerName;
		this.invoiceReference = invoiceReference;
		this.beneficiaryFirstName = beneficiaryFirstName;
		this.beneficiaryMiddleName = beneficiaryMiddleName;
		this.beneficiaryLastName = beneficiaryLastName;
		this.beneficiaryId = beneficiaryId;
		this.amount = amount;
		this.invoiceLines = invoiceLines;
	}

	public List<InvoiceLine> getInvoiceLines() {
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

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceReference() {
		return invoiceReference;
	}

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceReference(String invoiceReference) {
		this.invoiceReference = invoiceReference;
	}

	@Override
	public String toString() {
		return "Invoice [providerName=" + providerName + ", invoiceReference=" + invoiceReference+ ", beneficiaryFirstName="
				+ beneficiaryFirstName + ", beneficiaryMiddleName=" + beneficiaryMiddleName + ", beneficiaryLastName="
				+ beneficiaryLastName + ", beneficiaryId=" + beneficiaryId + ", amount=" + amount + ", invoiceLines="
				+ invoiceLines + "]";
	}
	
}
