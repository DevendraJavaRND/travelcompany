package com.microservices.hotelservice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HotelDto {
	private Long id;
	private Date bookingDate;
	private String location;
	private String name;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
