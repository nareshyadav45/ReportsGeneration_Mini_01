package com.reports.generation.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="Citizen_Plan_Info")
public class CitizenPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer citizenId;
	private String citizenName;
	private String gender;
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	private Double benefitedAmt;
	private String denialReason;
	private LocalDate terminatedDate;
	private String terminationReason;
	
	public Double getBenefitedAmt() {
		return benefitedAmt;
	}
	
	public void setBenefitedAmt(Double benefitedAmt) {
		this.benefitedAmt=benefitedAmt;
	}
	
	public Integer getCitizenId() {
		return citizenId;
	}
	public void setCitizenId(Integer citizenId) {
		this.citizenId = citizenId;
	}
	public String getCitizenName() {
		return citizenName;
	}
	public void setCitizenName(String citizenName) {
		this.citizenName = citizenName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getStatus() {
		return planStatus;
	}
	public void setStatus(String status) {
		this.planStatus = status;
	}
	public LocalDate getPlanStartDate() {
		return planStartDate;
	}
	public void setPlanStartDate(LocalDate planStartDate) {
		this.planStartDate = planStartDate;
	}
	public LocalDate getPlanEndDate() {
		return planEndDate;
	}
	public void setPlanEndDate(LocalDate planEndDate) {
		this.planEndDate = planEndDate;
	}
	public String getDenialReason() {
		return denialReason;
	}
	public void setDenialReason(String denialReason) {
		this.denialReason = denialReason;
	}
	public LocalDate getTerminatedDate() {
		return terminatedDate;
	}
	public void setTerminatedDate(LocalDate terminatedDate) {
		this.terminatedDate = terminatedDate;
	}
	public String getTerminationReason() {
		return terminationReason;
	}
	public void setTerminationReason(String terminationReason) {
		this.terminationReason = terminationReason;
	}
	public CitizenPlan(Integer citizenId, String citizenName, String gender, String planName, String status,
			LocalDate planStartDate, LocalDate planEndDate, String denialReason, LocalDate terminatedDate,
			String terminationReason,Double amt) {
		super();
		this.citizenId = citizenId;
		this.citizenName = citizenName;
		this.gender = gender;
		this.planName = planName;
		this.planStatus = status;
		this.benefitedAmt=amt;
		this.planStartDate = planStartDate;
		this.planEndDate = planEndDate;
		this.denialReason = denialReason;
		this.terminatedDate = terminatedDate;
		this.terminationReason = terminationReason;
	}
	
	
	
	

	@Override
	public String toString() {
		return "CitizenPlan [citizenId=" + citizenId + ", citizenName=" + citizenName + ", gender=" + gender
				+ ", planName=" + planName + ", planStatus=" + planStatus + ", planStartDate=" + planStartDate
				+ ", planEndDate=" + planEndDate + ", benefitedAmt=" + benefitedAmt + ", denialReason=" + denialReason
				+ ", terminatedDate=" + terminatedDate + ", terminationReason=" + terminationReason + "]";
	}

	public CitizenPlan() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
