package com.avisys.cim.dto;

import java.util.List;

import com.avisys.cim.MobileNumber;

public class CustomerDTO {
	private String firstName;
	private String lastName;
	private List<MobileNumber> mobileNumbers;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<MobileNumber> getMobileNumbers() {
		return mobileNumbers;
	}
	public void setMobileNumbers(List<MobileNumber> mobileNumbers) {
		this.mobileNumbers = mobileNumbers;
	}
	public CustomerDTO(String firstName, String lastName, List<MobileNumber> mobileNumbers) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumbers = mobileNumbers;
	}
	public CustomerDTO() {
		super();
	}
	
	
}
