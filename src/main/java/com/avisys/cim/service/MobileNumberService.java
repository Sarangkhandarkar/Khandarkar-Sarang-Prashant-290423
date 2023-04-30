package com.avisys.cim.service;

import java.util.List;

import com.avisys.cim.MobileNumber;

public interface MobileNumberService {
	List<MobileNumber> getMobileNumberofUser(Long customerid);
	MobileNumber GetMobilenumberobject(String Mobilenumber);
	MobileNumber saveMobileNumber(MobileNumber newMobileNumber);
	int deleteByCustomerId(Long id);
}
