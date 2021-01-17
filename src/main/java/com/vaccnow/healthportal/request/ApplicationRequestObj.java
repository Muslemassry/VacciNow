package com.vaccnow.healthportal.request;

import java.util.Date;

public class ApplicationRequestObj {
	public Integer id;
	public String paymentType;
	public Date appointment;
	public Integer patientId;
	public Integer branchId;
	public Integer vaccineId;
	public String toEmail;
	
	public ApplicationRequestObj() {
		super();
	}

	public ApplicationRequestObj(String paymentType, Date appointment, Integer patientId, Integer branchId,
			Integer vaccineId) {
		super();
		this.paymentType = paymentType;
		this.appointment = appointment;
		this.patientId = patientId;
		this.branchId = branchId;
		this.vaccineId = vaccineId;
	}
	
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public Date getAppointment() {
		return appointment;
	}
	public void setAppointment(Date appointment) {
		this.appointment = appointment;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	public Integer getVaccineId() {
		return vaccineId;
	}
	public void setVaccineId(Integer vaccineId) {
		this.vaccineId = vaccineId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	
}
