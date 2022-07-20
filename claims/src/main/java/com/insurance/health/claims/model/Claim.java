package com.insurance.health.claims.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Claim {

	private String firstName;
	private String middleName;
	private String lastName;
	private LocalDate dateOfBirth;
	private int beneficiaryId;
	
	private String patientFirstName;
	private String patientMiddleName;
	private String patientLastName;
	private LocalDate patientDateOfBirth;

	private LocalDate claimDate;
	private String diagnosis;
	public Claim(@JsonProperty("firstName") String firstName, 
			@JsonProperty("middleName") String middleName, 
			@JsonProperty("lastName") String lastName, 
			@JsonProperty("dateOfBirth") String dateOfBirth,
			@JsonProperty("beneficiaryId") int beneficiaryId,
			@JsonProperty("patientFirstName") String patientFirstName, 
			@JsonProperty("patientMiddleName") String patientMiddleName, 
			@JsonProperty("patientLastName") String patientLastName, 
			@JsonProperty("patientDay") int patientDay,
			@JsonProperty("patientDateOfBirth") String patientDateOfBirth,
			@JsonProperty("patientYear") int patientYear,
			@JsonProperty("diagnosis") String diagnosis) {
		
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dateOfBirth = LocalDate.parse(dateOfBirth);
		this.beneficiaryId = beneficiaryId;
		this.patientFirstName = patientFirstName;
		this.patientMiddleName = patientMiddleName;
		this.patientLastName = patientLastName;
		this.patientDateOfBirth = LocalDate.parse(patientDateOfBirth);
		this.claimDate = LocalDate.now();
		this.diagnosis = diagnosis;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public int getBeneficiaryId() {
		return beneficiaryId;
	}
	public void setBeneficiaryId(int beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}
	public String getPatientFirstName() {
		return patientFirstName;
	}
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}
	public String getPatientMiddleName() {
		return patientMiddleName;
	}
	public void setPatientMiddleName(String patientMiddleName) {
		this.patientMiddleName = patientMiddleName;
	}
	public String getPatientLastName() {
		return patientLastName;
	}
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}
	public LocalDate getPatientDateOfBirth() {
		return patientDateOfBirth;
	}
	public void setPatientDateOfBirth(LocalDate patientDateOfBirth) {
		this.patientDateOfBirth = patientDateOfBirth;
	}
	public LocalDate getClaimDate() {
		return claimDate;
	}
	public void setClaimDate(LocalDate claimDate) {
		this.claimDate = claimDate;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	@Override
	public String toString() {
		return "Claim [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", beneficiaryId=" + beneficiaryId + ", patientFirstName="
				+ patientFirstName + ", patientMiddleName=" + patientMiddleName + ", patientLastName=" + patientLastName
				+ ", patientDateOfBirth=" + patientDateOfBirth + ", claimDate=" + claimDate + ", diagnosis=" + diagnosis
				+ "]";
	}
	
	
}
