package com.avisys.cim.service;

import java.util.List;
import java.util.Optional;

import com.avisys.cim.Customer;

public interface CustomerService {
	Customer addCustomer(Customer newCustomer);
	List<Customer> getAllCustomer();
	List<Customer> getCustomerByFirstname(String firstname);
	List<Customer> getCustomerByLastname(String lastname);
	Optional<Customer> getCustomerByMobilenumber(String mobilenumber);
	Customer saveNewUser (Customer newCustomer);
}
