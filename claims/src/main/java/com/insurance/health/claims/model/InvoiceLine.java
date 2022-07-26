package com.insurance.health.claims.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class InvoiceLine {
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "invoice_line_seq"
	)
	@SequenceGenerator(
			name = "invoice_line_seq",
			allocationSize = 1
	)
	long lineId;
	private String category;
	private String description;
	private double price;
	private int quantity;
	
	public InvoiceLine(@JsonProperty("category") String category, 
			@JsonProperty("description") String description, 
			@JsonProperty("pricePerUnit") double price, 
			@JsonProperty("quantity") int quantity) {
		
		this.category = category;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getLineId() {
		return lineId;
	}

	public void setLineId(long lineId) {
		this.lineId = lineId;
	}

	@Override
	public String toString() {
		return "InvoiceLine [category=" + category + ", description=" + description + ", price=" + price + ", quantity="
				+ quantity + "]";
	}
	
	
}
