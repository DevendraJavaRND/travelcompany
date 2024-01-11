package com.microservices.paymentservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservices.paymentservice.entity.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long>{
	Payment findByIdAndFlightId(Long paymentId, Long flightId);
	Payment findByIdAndHotelId(Long customerId, Long hotelId);
}
