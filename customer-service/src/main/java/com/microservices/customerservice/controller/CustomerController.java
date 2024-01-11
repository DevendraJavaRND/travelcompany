package com.microservices.customerservice.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.microservices.customerservice.model.CustomerDto;
import com.microservices.customerservice.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {
		@Autowired
		private CustomerService customerService;
		
		@GetMapping("/customer")
		//@PreAuthorize("hasAnyAuthority('ROLE_STUDENT', 'ROLE_ADMIN')")
		public ResponseEntity<List<CustomerDto>> getAllCustomerHandler(){
			List<CustomerDto> listAuthorDto = null;
			try {
				listAuthorDto = customerService.getAllCustomer();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(listAuthorDto.size() <= 0) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	        return ResponseEntity.ok(listAuthorDto);
		}


		@GetMapping("/customer/{id}")
		//@PreAuthorize("hasAnyAuthority('ROLE_STUDENT', 'ROLE_ADMIN')")
		public  ResponseEntity<CustomerDto> getCustomerByIdHandler(@PathVariable("id") Long id){
			CustomerDto customerDto = null;
			try {
				customerDto = customerService.getCustomerById(id);
			}catch(Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.ok(customerDto);
		}
		

		@PostMapping("/customer")
		//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
		public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto){
			try {
				customerService.addCustomer(customerDto);
				return ResponseEntity.ok(customerDto);
			}catch(Exception e){
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}

		@PutMapping("/customer")
		//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
		public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto){
			try {
				customerService.updateCustomer(customerDto);
				return ResponseEntity.ok(customerDto);
			}catch(Exception e){
				return ResponseEntity.status(HttpStatus.ACCEPTED).build();
			}
		}

		@DeleteMapping("/customer/{id}")
		//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
		public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id){
			String message = "";
			try {
				message = customerService.deleteCustomer(id);
				return new ResponseEntity<String>(message, HttpStatus.OK);
			}catch(Exception e){
				return new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		@GetMapping("/flight/{flightId}")
	    public ResponseEntity<List<CustomerDto>> getCustomerByFlight(@PathVariable("flightId") Long flightId) {
			List<CustomerDto> customerDtoList = null;
			try {
				customerDtoList = customerService.getCustomerByFlightId(flightId);
			}catch(Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.ok(customerDtoList);
	    }
		@GetMapping("/hotel/{hotelId}")
	    public ResponseEntity<List<CustomerDto>> getCustomerByHotel(@PathVariable("hotelId") Long hotelId) {
			List<CustomerDto> customerDtoList = null;
			try {
				customerDtoList = customerService.getCustomerByHotelId(hotelId);
			}catch(Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.ok(customerDtoList);
	    }
		@GetMapping("/payment/{paymentId}")
	    public ResponseEntity<CustomerDto> getCustomerByHotelAndPayment(@PathVariable("paymentId") 
	    Long paymentId) {
			CustomerDto customerDto = null;
			try {
				customerDto = customerService.getCustomerByPaymentId(paymentId);
			}catch(Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.ok(customerDto);
	    }
//		@GetMapping("/payment/{paymentId}")
//	    public ResponseEntity<CustomerDto> getCustomerByFlightAndPayment(@PathVariable("paymentId") 
//	    Long paymentId) {
//			CustomerDto customerDto = null;
//			try {
//				customerDto = customerService.getCustomerByFlightIdAndPaymentId(paymentId);
//			}catch(Exception e) {
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//			}
//			return ResponseEntity.ok(customerDto);
//	    }
	}
