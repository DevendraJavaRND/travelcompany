package com.microservices.paymentservice.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.microservices.paymentservice.model.CustomerDto;

@HttpExchange
public interface CustomerClient {
	@GetExchange("/api/payment/{paymentId}")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public CustomerDto findByPayment(@PathVariable("paymentId") Long paymentId);
//	
//	@GetExchange("/api/payment/{paymentId}")
//	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
//	public CustomerDto findByFlight(@PathVariable("paymentId") Long paymentId);
	
//	@PostMapping("api/payment/{customerId}")
//	public CustomerDto findByCustomerId(@PathVariable("customerId") Long customerId);
	
}
