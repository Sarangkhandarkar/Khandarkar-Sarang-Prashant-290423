package com.avisys.cim.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avisys.cim.Customer;
import com.avisys.cim.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	public CustomerController() {
		System.out.println("Inside the default constructor of "+ getClass());
	}
@Autowired
public CustomerService custService;
	
	@GetMapping("/all")
	public List<Customer> getAllCustomers(){
		return custService.getAllCustomer();
	}
	
	@GetMapping("firstname/{name}")
	public ResponseEntity<?> getCustomerByFirstName(@PathVariable String name){
		List<Customer> list = custService.getCustomerByFirstname(name);
		return list.isEmpty()? new ResponseEntity<>("No customer found matching "+name,HttpStatus.BAD_REQUEST): ResponseEntity.ok(list);
	}
	
	@GetMapping("lastname/{lname}")
	public ResponseEntity<?> getCustomerByLastName(@PathVariable String lname){
		List<Customer> list = custService.getCustomerByLastname(lname);
		return list.isEmpty()? new ResponseEntity<>("No Customer found matching "+ lname,HttpStatus.NOT_FOUND):ResponseEntity.ok(list);
	}
	
	@GetMapping("mobilenumber/{number}")
	public ResponseEntity<?> getCustomerByMobileNumber(@PathVariable String number){
		Optional<Customer> cust = custService.getCustomerByMobilenumber(number);
		return cust.isPresent()? ResponseEntity.ok(cust):new ResponseEntity<>("There is no customer with this mobile number", HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/saveuser")
	public ResponseEntity<?> saveNewCustomer(@RequestBody Customer newcustomer){
		Optional<Customer> existingCustomer = custService.getCustomerByMobilenumber(newcustomer.getMobileNumber());
		return existingCustomer.isEmpty()? ResponseEntity.ok(custService.addCustomer(newcustomer)):new ResponseEntity<>("Unable to create Customer. Mobile number already present.",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
