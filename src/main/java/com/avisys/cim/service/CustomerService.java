package com.avisys.cim.service;

import java.util.List;
import java.util.Optional;

import com.avisys.cim.Customer;

public interface CustomerService {
	Customer addCustomer(Customer newCustomer);
	List<Customer> getAllCustomer();
	List<Customer> getCustomerByFirstname(String firstname);
	List<Customer> getCustomerByLastname(String lastname);
	Optional<Customer> getCustomerById(Long id);
	Customer saveNewUser (Customer newCustomer);
	void deleteCustomerbyId(Long id);
}
