package com.avisys.cim.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.directory.SearchResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avisys.cim.Customer;
import com.avisys.cim.MobileNumber;
import com.avisys.cim.dto.CustomerDTO;
import com.avisys.cim.service.CustomerService;
import com.avisys.cim.service.MobileNumberService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	public CustomerController() {
		System.out.println("Inside the default constructor of "+ getClass());
	}
	@Autowired
	public CustomerService custService;

	@Autowired
	public MobileNumberService mobileNumberService;

	public List<CustomerDTO> generateResponseDto (List<Customer> list) {
		List<CustomerDTO> response = new ArrayList<>();
		for(Customer customer : list) {
			List<MobileNumber> mobList = mobileNumberService.getMobileNumberofUser(customer.getId());
			CustomerDTO customerdto = new CustomerDTO(customer.getFirstName(), customer.getLastName(),mobList);
			response.add(customerdto);
		}return response;
	}
	@GetMapping("/all")
	public List<CustomerDTO> getAllCustomers(){
		return generateResponseDto(custService.getAllCustomer());
	}

	@GetMapping("firstname/{name}")
	public ResponseEntity<?> getCustomerByFirstName(@PathVariable String name){
		List<Customer> list = custService.getCustomerByFirstname(name);
		//		List<CustomerDTO> searchResult = ;
		return list.isEmpty()? new ResponseEntity<>("No customer found matching "+name,HttpStatus.BAD_REQUEST): ResponseEntity.ok(generateResponseDto(list));
	}

	@GetMapping("lastname/{lname}")
	public ResponseEntity<?> getCustomerByLastName(@PathVariable String lname){
		List<Customer> list = custService.getCustomerByLastname(lname);
		return list.isEmpty()? new ResponseEntity<>("No Customer found matching "+ lname,HttpStatus.NOT_FOUND):ResponseEntity.ok(generateResponseDto(list));
	}

	@GetMapping("mobilenumber/{number}")
	public ResponseEntity<?> getCustomerByMobileNumber(@PathVariable String number){
		CustomerDTO response = new CustomerDTO();
		Optional<Customer> cust = custService.getCustomerById(mobileNumberService.GetMobilenumberobject(number).getCustomerId());
		if(cust.isPresent()) {
			List<MobileNumber> mobList = mobileNumberService.getMobileNumberofUser(cust.get().getId());
			response= new CustomerDTO(cust.get().getFirstName(), cust.get().getLastName(),mobList);
		}
		return cust.isPresent()? ResponseEntity.ok(response):new ResponseEntity<>("There is no customer with this mobile number", HttpStatus.NOT_FOUND);
	}

	@PostMapping("/saveuser")
	public ResponseEntity<?> saveNewCustomer(@RequestBody CustomerDTO newCustomerDto){
		CustomerDTO response = null;
		Long customerId;
		Optional<Customer> existingCustomer = java.util.Optional.empty();
		List<String> numbers = new ArrayList<>();
		List<MobileNumber> mobNumbers = newCustomerDto.getMobileNumbers();
		if(mobNumbers.isEmpty())return new ResponseEntity<>("Unable to create Customer. Mobile number is a required field.",HttpStatus.INTERNAL_SERVER_ERROR);
		for(MobileNumber item : mobNumbers) {
			if(mobileNumberService.GetMobilenumberobject(item.getNumber()) != null)
				existingCustomer = custService.getCustomerById(mobileNumberService.GetMobilenumberobject(item.getNumber()).getCustomerId());
			numbers.add(item.getNumber());
		}if(existingCustomer.isEmpty()) {
			Customer newcustomer = new Customer(newCustomerDto.getFirstName(), newCustomerDto.getLastName());
			customerId=custService.saveNewUser(newcustomer).getId();
			for (String number : numbers) {
				MobileNumber newmobilenumber = new MobileNumber(number, customerId);
				mobileNumberService.saveMobileNumber(newmobilenumber);
			} response = new CustomerDTO(custService.getCustomerById(customerId).get().getFirstName(), custService.getCustomerById(customerId).get().getLastName(), mobileNumberService.getMobileNumberofUser(customerId));
		}
		return existingCustomer.isEmpty()? ResponseEntity.ok(response):new ResponseEntity<>("Unable to create Customer. Mobile number already present.",HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/deletecustomer/{mobileNumber}")
	public ResponseEntity<?> deleteCustomerByMobileNumber(@PathVariable String mobileNumber){
		Optional<MobileNumber> existingMobileNumberObj = Optional.ofNullable((mobileNumberService.GetMobilenumberobject(mobileNumber)));
		if(existingMobileNumberObj.isEmpty()) {
			return new ResponseEntity<>("Unable to delete customer. Customer does not exist",HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			custService.deleteCustomerbyId(existingMobileNumberObj.get().getCustomerId());
			int count = mobileNumberService.deleteByCustomerId(existingMobileNumberObj.get().getCustomerId());
			if(count>0)return ResponseEntity.ok("The user is deleted successfully");
			return new ResponseEntity<>("Mobile Numbers were not deleted successfully",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//		Optional<Customer> customerToDelete = custService.getCustomerById(existingMobileNumberObj.get().getCustomerId());
	}

	@PutMapping("/updatecustomer/{id}")
	public ResponseEntity<?> updateORDeleteCustomerMobileNumber(@RequestBody CustomerDTO customerdto, @PathVariable Long id){
		List<MobileNumber> mobileNumberList = customerdto.getMobileNumbers();
		List<MobileNumber> exitingMobileNumbers = mobileNumberService.getMobileNumberofUser(id);
		for(MobileNumber existing : exitingMobileNumbers) {
			if(mobileNumberList.contains(existing)) {
				int index =mobileNumberList.indexOf(existing);
				if(!(mobileNumberList.get(index).getNumber().equalsIgnoreCase(existing.getNumber()))) {
					existing.setNumber(mobileNumberList.get(index).getNumber());
					mobileNumberList.remove(existing);
					continue;
				}else mobileNumberService.delete(existing);
			}
		}if(mobileNumberList.isEmpty())return ResponseEntity.ok("All the values of Mobile number are updated successfully");
		for(MobileNumber item : mobileNumberList) {
			mobileNumberService.saveMobileNumber(item);
		}return ResponseEntity.ok("All the values of mobile number are updated and new values are added Successfully");
	}

}
