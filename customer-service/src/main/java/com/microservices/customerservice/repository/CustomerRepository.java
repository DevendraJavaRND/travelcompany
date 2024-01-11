package com.microservices.customerservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservices.customerservice.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{
	List<Customer> findByFlightId(Long flightId);
	List<Customer> findByHotelId(Long hotelId);
	Customer findByPaymentId(Long paymentId);
	//Customer findByPaymentId(Long paymentId);
}
