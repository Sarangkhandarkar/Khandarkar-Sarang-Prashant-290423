package com.avisys.cim.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avisys.cim.Customer;
import com.avisys.cim.repository.CustomerRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class CustomerServiceImplimentation implements CustomerService {

	@Autowired
	public CustomerRepository customerRepo;
	
	@Override
	public Customer addCustomer(Customer newCustomer) {
		return customerRepo.save(newCustomer);
	}

	@Override
	public List<Customer> getAllCustomer() {
		return customerRepo.findAll() ;
	}

	@Override
	public List<Customer> getCustomerByFirstname(String firstname) {
		return customerRepo.getByFirstNameContainingIgnoreCase(firstname);
	}

	@Override
	public List<Customer> getCustomerByLastname(String lastname) {
		return customerRepo.getByLastNameContainingIgnoreCase(lastname);
	}

	@Override
	public Optional<Customer> getCustomerByMobilenumber(String mobilenumber) {
		return customerRepo.getByMobileNumber(mobilenumber);
	}

}
