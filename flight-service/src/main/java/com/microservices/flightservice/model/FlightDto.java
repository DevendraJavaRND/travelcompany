package com.microservices.flightservice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightDto {
	private Long id;
	private Date bookingDate;
	private String origin;
	private String destination;
	private List<CustomerDto> listCustomer = new ArrayList<CustomerDto>();
	public List<CustomerDto> getListCustomer() {
		return listCustomer;
	}
	public void setListCustomer(List<CustomerDto> listCustomer) {
		this.listCustomer = listCustomer;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
}
