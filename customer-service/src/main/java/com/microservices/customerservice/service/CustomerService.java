package com.microservices.customerservice.service;

import java.util.List;

import com.microservices.customerservice.model.CustomerDto;

public interface CustomerService {
	public CustomerDto addCustomer(CustomerDto customerDto);
	public CustomerDto updateCustomer(CustomerDto customerDto);
	public String deleteCustomer(Long id);
	public List<CustomerDto> getAllCustomer();
	public CustomerDto getCustomerById(Long id);
	public List<CustomerDto> getCustomerByFlightId(Long flightId);
	public List<CustomerDto> getCustomerByHotelId(Long hotelId);
	public CustomerDto getCustomerByPaymentId(Long paymentId);
//	CustomerDto getCustomerByFlightIdAndPaymentId(Long paymentId);
//	CustomerDto getCustomerByHotelIdAndPaymentId(Long paymentId);
}
