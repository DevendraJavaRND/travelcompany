package com.microservices.hotelservice.client;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.microservices.hotelservice.model.CustomerDto;

@HttpExchange
public interface CustomerClient {
	@GetExchange("/api/hotel/{hotelId}")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<CustomerDto> findByHotel(@PathVariable("hotelId") Long hotelId);
		
		
}
