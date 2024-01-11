package com.microservices.paymentservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.paymentservice.entity.Payment;
import com.microservices.paymentservice.model.PaymentDto;
import com.microservices.paymentservice.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public PaymentDto makePaymentForFlight(PaymentDto paymentDto) {
		Payment payment = new Payment();
		payment.setCustomerId(paymentDto.getCustomerId());
		payment.setFlightId(paymentDto.getFlightId());
		payment.setPaymentDate(paymentDto.getPaymentDate());
		payment.setStatus(paymentDto.getStatus());
		paymentRepository.save(payment);
		paymentDto.setId(payment.getId());
		return paymentDto;
	}

	@Override
	public PaymentDto makePaymentForHotel(PaymentDto paymentDto) {
		Payment payment = new Payment();
		payment.setCustomerId(paymentDto.getCustomerId());
		payment.setHotelId(paymentDto.getHotelId());
		payment.setPaymentDate(paymentDto.getPaymentDate());
		payment.setStatus(paymentDto.getStatus());
		paymentRepository.save(payment);
		paymentDto.setId(payment.getId());
		return paymentDto;
	}

	@Override
	public List<PaymentDto> getAllPayments() {
		List<PaymentDto> paymentDtoList = new ArrayList<>();
		List<Payment> paymentList = (List<Payment>) paymentRepository.findAll();
		for(Payment payment: paymentList) {
			PaymentDto paymentDto = new PaymentDto();
			paymentDto.setId(payment.getId());
			paymentDto.setCustomerId(payment.getCustomerId());
			paymentDto.setFlightId(payment.getFlightId());
			paymentDto.setHotelId(payment.getHotelId());
			paymentDto.setPaymentDate(payment.getPaymentDate());
			paymentDto.setStatus(payment.getStatus());
			paymentDtoList.add(paymentDto);
		}
		return paymentDtoList;
	}

	@Override
	public PaymentDto getPaymentByPaymentIdAndFlightId(Long id) {
		PaymentDto paymentDto = new PaymentDto();
		Optional<Payment> paymentOptional = paymentRepository.findById(id);
		Payment payment = paymentOptional.get();
		if(paymentOptional.isPresent()) {
			paymentDto.setId(payment.getId());
			paymentDto.setCustomerId(payment.getCustomerId());
			paymentDto.setFlightId(payment.getFlightId());
			paymentDto.setPaymentDate(payment.getPaymentDate());
			paymentDto.setStatus(payment.getStatus());
			//System.out.println(paymentId+" Flight id: "+flightId);
		}
		return paymentDto;
	}

	@Override
	public PaymentDto getPaymentByPaymentIdAndHotelId(Long id) {
		PaymentDto paymentDto = new PaymentDto();
		Optional<Payment> paymentOptional = paymentRepository.findById(id);
		Payment payment = paymentOptional.get();
		if(payment != null) {
			paymentDto.setId(payment.getId());
			paymentDto.setCustomerId(payment.getCustomerId());
			paymentDto.setHotelId(payment.getHotelId());
			paymentDto.setPaymentDate(payment.getPaymentDate());
			paymentDto.setStatus(payment.getStatus());
		}
		return paymentDto;
	}

}
