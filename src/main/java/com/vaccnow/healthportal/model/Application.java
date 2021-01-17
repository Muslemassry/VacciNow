package com.vaccnow.healthportal.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "application")
public class Application extends BaseEntity {
	public Application() {}
	
	public Application(String paymentType, Date appointment, Patient patient, Branch branch, Vaccine vaccine) {
		super();
		this.paymentType = paymentType;
		this.appointment = appointment;
		this.patient = patient;
		this.branch = branch;
		this.vaccine = vaccine;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "payment_type")
	private String paymentType;
	
	@Column(name = "appointment")
	private Date appointment;
	
	@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
	private Patient patient;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name="branch_id", nullable=false)
	private Branch branch;
	
	@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "vaccine_id", referencedColumnName = "id", nullable = false)
	private Vaccine vaccine;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Vaccine getVaccine() {
		return vaccine;
	}

	public void setVaccine(Vaccine vaccine) {
		this.vaccine = vaccine;
	}
}
