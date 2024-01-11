package com.microservices.paymentservice.service;

import java.util.List;

import com.microservices.paymentservice.model.PaymentDto;

public interface PaymentService {
	public PaymentDto makePaymentForFlight(PaymentDto paymentDto);
	public PaymentDto makePaymentForHotel(PaymentDto paymentDto);
	public List<PaymentDto> getAllPayments();
	public PaymentDto getPaymentByPaymentIdAndFlightId(Long id);
	public PaymentDto getPaymentByPaymentIdAndHotelId(Long id);
}
