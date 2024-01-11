package com.microservices.paymentservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.paymentservice.client.CustomerClient;
import com.microservices.paymentservice.model.PaymentDto;
import com.microservices.paymentservice.service.PaymentService;
@RestController
@RequestMapping("/api")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private CustomerClient customerClient;
	
	@PostMapping("/payment/flight")
	//@PreAuthorize("hasAnyAuthority('ROLE_STUDENT', 'ROLE_ADMIN')")
	public ResponseEntity<PaymentDto> makePaymentForFlight(@RequestBody PaymentDto paymentDto) {
		try {
			paymentService.makePaymentForFlight(paymentDto);
			return ResponseEntity.ok(paymentDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	@PostMapping("/payment/hotel")
	//@PreAuthorize("hasAnyAuthority('ROLE_STUDENT', 'ROLE_ADMIN')")
	public ResponseEntity<PaymentDto> makePaymentForHotel(@RequestBody PaymentDto paymentDto) {
		try {
			paymentService.makePaymentForHotel(paymentDto);
			return ResponseEntity.ok(paymentDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	@GetMapping("/with-customer-flight")
	public ResponseEntity<PaymentDto> getPaymentForFlight() {
		List<PaymentDto> paymentDtoList = new ArrayList<PaymentDto>();
		try {
			paymentDtoList = paymentService.getAllPayments();
			PaymentDto paymentDto = paymentDtoList.get(0);
			paymentDto.setCustomer(customerClient.findByPayment(paymentDto.getId()));
			System.out.println("Customer: "+paymentDto.getCustomer());
			return ResponseEntity.ok(paymentDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("Customer: "+paymentDto.getCustomer());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	@GetMapping("/with-customer-hotel")
	public ResponseEntity<PaymentDto> getPaymentForHotel() {
		List<PaymentDto> paymentDtoList = new ArrayList<PaymentDto>();
		try {
			paymentDtoList = paymentService.getAllPayments();
			PaymentDto paymentDto = paymentDtoList.get(0);
			paymentDto.setCustomer(customerClient.findByPayment(paymentDto.getId()));
			return ResponseEntity.ok(paymentDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	
	@GetMapping("/payment")
	public ResponseEntity<List<PaymentDto>> getAllPayments() {
		List<PaymentDto> paymentDtoList = new ArrayList<PaymentDto>();
		try {
			paymentDtoList = paymentService.getAllPayments();
			return ResponseEntity.ok(paymentDtoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

//	@GetMapping("/hotel/{hotelId}/with-customer/{customerId}")
//	//@PreAuthorize("hasAnyAuthority('ROLE_STUDENT', 'ROLE_ADMIN')")
//	public ResponseEntity<PaymentDto> getAllPaymentForHotelWithCustomers
//	(@PathVariable("hotelId") Long hotelId, @PathVariable("customerId") Long customerId){
//		PaymentDto paymentDto = new PaymentDto();
//		try {
//			paymentDto = paymentService.getPaymentByCustomerIdAndHotelId(customerId, hotelId);
//			if(paymentDto != null) {
//				paymentDto.setCustomer(customerClient.findByHotel(hotelId, customerId));
//				return ResponseEntity.ok(paymentDto);
//			}
//			//listAuthorDto = customerClient.findByFlight(null);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        
//	}
	
//	@GetMapping("/flight/{flightId}/with-customer/{customerId}")
//	//@PreAuthorize("hasAnyAuthority('ROLE_STUDENT', 'ROLE_ADMIN')")
//	public ResponseEntity<PaymentDto> getAllPaymentForFlightWithCustomers
//	(@PathVariable("flightId") Long flightId, @PathVariable("customerId") Long customerId){
//		PaymentDto paymentDto = new PaymentDto();
//		try {
//			paymentDto = paymentService.getPaymentByCustomerIdAndFlightId(customerId, flightId);
//			if(paymentDto != null) {
//				paymentDto.setCustomer(customerClient.findByFlight(flightId, customerId));
//				return ResponseEntity.ok(paymentDto);
//			}
//			//listAuthorDto = customerClient.findByFlight(null);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        
//	}
}
