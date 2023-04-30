package com.avisys.cim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avisys.cim.MobileNumber;

public interface MobileNumberRepository extends JpaRepository<MobileNumber, Long> {
	List<MobileNumber> getByCustomerId(Long customerid);
	MobileNumber getByNumber(String mobilenumber);
}
