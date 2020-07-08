package com.wiley.covid19.model;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	@Embedded
	private Address address;

	private String contactNumber;
	private String district;
	private String province;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date confirmedDate;

	private boolean isCitizen;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Enumerated(EnumType.STRING)
	private CaseOrigin caseOrigin;

	public Person() {

	}

	public Person(String name, Address address, String contactNumber, String district, String province,
			Date confirmedDate, boolean isCitizen, Status status, CaseOrigin caseOrigin) {
		super();
		this.name = name;
		this.address = address;
		this.contactNumber = contactNumber;
		this.district = district;
		this.province = province;
		this.confirmedDate = confirmedDate;
		this.isCitizen = isCitizen;
		this.status = status;
		this.caseOrigin = caseOrigin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Date getConfirmedDate() {
		return confirmedDate;
	}

	public void setConfirmedDate(Date confirmedDate) {
		this.confirmedDate = confirmedDate;
	}

	public boolean isCitizen() {
		return isCitizen;
	}

	public void setCitizen(boolean isCitizen) {
		this.isCitizen = isCitizen;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public CaseOrigin getCaseOrigin() {
		return caseOrigin;
	}

	public void setCaseOrigin(CaseOrigin caseOrigin) {
		this.caseOrigin = caseOrigin;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", address=" + address + ", contactNumber=" + contactNumber
				+ ", district=" + district + ", province=" + province + ", confirmedDate=" + confirmedDate
				+ ", isCitizen=" + isCitizen + ", status=" + status + ", caseOrigin=" + caseOrigin + "]";
	}

}
