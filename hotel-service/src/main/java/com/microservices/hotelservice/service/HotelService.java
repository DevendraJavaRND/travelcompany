package com.microservices.hotelservice.service;

import java.util.Date;
import java.util.List;

import com.microservices.hotelservice.model.HotelDto;

public interface HotelService {
	public HotelDto addHotel(HotelDto hotelDto);
	public HotelDto updateHotel(HotelDto hotelDto);
	public String deleteHotel(Long id);
	public String bookHotel(Long id);
	public List<HotelDto> searchHotel(String location, Date bookingDate);
	public List<HotelDto> getAllHotels();
	public HotelDto getHotelById(Long id);
}
