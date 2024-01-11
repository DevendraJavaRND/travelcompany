package com.microservices.customerservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.customerservice.entity.Customer;
import com.microservices.customerservice.model.CustomerDto;
import com.microservices.customerservice.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	@Override
	public CustomerDto addCustomer(CustomerDto customerDto) {
		Customer customer= new Customer();
		customer.setId(customerDto.getId());
		customer.setName(customerDto.getName());
		customer.setFlightId(customerDto.getFlightId());
		customer.setHotelId(customerDto.getHotelId());
		customerRepository.save(customer);
		customerDto.setId(customer.getId());
		return customerDto;
	}

	@Override
	public CustomerDto updateCustomer(CustomerDto customerDto) {
		Customer customer= new Customer();
		Optional<Customer> optionalCustomer = customerRepository.findById(customerDto.getId());
		if(optionalCustomer.isPresent()) {
			customer.setId(customerDto.getId());
			customer.setId(customerDto.getId());
			customer.setName(customerDto.getName());
			customer.setFlightId(customerDto.getFlightId());
			customerRepository.save(customer);
		}
		
		return customerDto;
	}

	@Override
	public String deleteCustomer(Long id) {
		String message = "Not deleted";
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		if(optionalCustomer.isPresent()) {
			customerRepository.deleteById(id);
			message = "Deleted";
		}
		return message;
	}

	@Override
	public List<CustomerDto> getAllCustomer() {
		List<CustomerDto> customerDtoList = new ArrayList<CustomerDto>();
		List<Customer> listCustomer = (List<Customer>) customerRepository.findAll();
		for(Customer customer: listCustomer) {
			CustomerDto customerDto = new CustomerDto();
			customerDto.setId(customer.getId());
			customerDto.setName(customer.getName());
			customerDto.setFlightId(customer.getFlightId());
			customerDtoList.add(customerDto);
		}
		return customerDtoList;
	}

	@Override
	public CustomerDto getCustomerById(Long id) {
		CustomerDto customerDto = new CustomerDto();
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		Customer customer = optionalCustomer.get();
		customerDto.setId(customer.getId());
		customerDto.setName(customer.getName());
		customerDto.setFlightId(customer.getFlightId());
		return customerDto;
	}

	@Override
	public List<CustomerDto> getCustomerByFlightId(Long flightId) {
		List<CustomerDto> customerDtoList = new ArrayList<CustomerDto>();
		List<Customer> listCustomer = customerRepository.findByFlightId(flightId);
		for(Customer customer: listCustomer) {
			CustomerDto customerDto = new CustomerDto();
			customerDto.setId(customer.getId());
			customerDto.setName(customer.getName());
			customerDto.setFlightId(customer.getFlightId());
			customerDtoList.add(customerDto);
		}
		return customerDtoList;
	}

	@Override
	public List<CustomerDto> getCustomerByHotelId(Long hotelId) {
		List<CustomerDto> customerDtoList = new ArrayList<CustomerDto>();
		List<Customer> listCustomer = customerRepository.findByHotelId(hotelId);
		for(Customer customer: listCustomer) {
			CustomerDto customerDto = new CustomerDto();
			customerDto.setId(customer.getId());
			customerDto.setName(customer.getName());
			customerDto.setHotelId(customer.getHotelId());
			customerDtoList.add(customerDto);
		}
		return customerDtoList;
	}
	
	@Override
	public CustomerDto getCustomerByPaymentId(Long paymentId) {
		Customer customer = customerRepository.findByPaymentId(paymentId);
		CustomerDto customerDto = new CustomerDto();
		if(customer != null) {
			customerDto.setId(customer.getId());
			customerDto.setName(customer.getName());
			customerDto.setPaymentId(customer.getPaymentId());
			customerDto.setId(customer.getId());
		}
		return customerDto;
	}
//	@Override
//	public CustomerDto getCustomerByHotelIdAndPaymentId(Long paymentId) {
//		Customer customer = customerRepository.findByPaymentId(paymentId);
//		CustomerDto customerDto = new CustomerDto();
//		if(customer != null) {
//			customerDto.setId(customer.getId());
//			customerDto.setName(customer.getName());
//			customerDto.setPaymentId(customer.getPaymentId());
//			customerDto.setId(customer.getId());
//		}
//		return customerDto;
//	}
}
