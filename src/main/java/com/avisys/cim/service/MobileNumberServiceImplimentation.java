package com.avisys.cim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avisys.cim.MobileNumber;
import com.avisys.cim.repository.MobileNumberRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class MobileNumberServiceImplimentation implements MobileNumberService {

	@Autowired
	public MobileNumberRepository MobNoRepo;
	@Override
	public List<MobileNumber> getMobileNumberofUser(Long customerid) {
		return MobNoRepo.getByCustomerId(customerid);
	}
	@Override
	public MobileNumber GetMobilenumberobject(String Mobilenumber) {
		return MobNoRepo.getByNumber(Mobilenumber);
	}
	@Override
	public MobileNumber saveMobileNumber(MobileNumber newMobileNumber) {
		return MobNoRepo.save(newMobileNumber);
	}
	@Override
	public int deleteByCustomerId(Long id) {
		return MobNoRepo.deleteByCustomerId(id);
	}
	@Override
	public void delete(MobileNumber mobilenumber) {
		MobNoRepo.delete(mobilenumber);
	}
	
	
}
