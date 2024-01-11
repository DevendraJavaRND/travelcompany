package com.microservices.flightservice.client;

import java.util.List;

import com.microservices.flightservice.model.CustomerDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface CustomerClient {
	@GetExchange("/api/flight/{flightId}")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<CustomerDto> findByFlight(@PathVariable("flightId") Long flightId);
		
		
}
