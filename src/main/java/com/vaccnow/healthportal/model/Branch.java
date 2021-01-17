package com.vaccnow.healthportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "branch")
public class Branch extends BaseEntity {
	public Branch () {}
	
	public Branch (Integer Id) {
		this.id = Id;
	}
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

//	public List<Application> getApplications() {
//		return applications;
//	}
//
//	public void setApplications(List<Application> applications) {
//		this.applications = applications;
//	}
//
//	public List<Vaccine> getVaccines() {
//		return Vaccines;
//	}
//
//	public void setVaccines(List<Vaccine> vaccines) {
//		Vaccines = vaccines;
//	}
}
