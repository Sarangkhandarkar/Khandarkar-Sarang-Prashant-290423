package com.avisys.cim.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avisys.cim.Customer;
@Repository("CustomerRepository")
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> getByFirstNameContainingIgnoreCase(String firstname);
	List<Customer> getByLastNameContainingIgnoreCase(String lastname);
	Optional<Customer> getByMobileNumber(String mobilenumber);
}
