package com.microservices.hotelservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.hotelservice.entity.Hotel;
import com.microservices.hotelservice.model.HotelDto;
import com.microservices.hotelservice.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public HotelDto addHotel(HotelDto hotelDto) {
		Hotel hotel= new Hotel();
		hotel.setBookingDate(hotelDto.getBookingDate());
		hotel.setLocation(hotelDto.getLocation());
		hotel.setName(hotelDto.getName());
		hotelRepository.save(hotel);
		hotelDto.setId(hotel.getId());
		return hotelDto;
	}

	@Override
	public HotelDto updateHotel(HotelDto hotelDto) {
		Hotel hotel = new Hotel();
		Optional<Hotel> optionalHotel = hotelRepository.findById(hotelDto.getId());
		if(optionalHotel.isPresent()) {
			hotel.setId(hotelDto.getId());
			hotel.setBookingDate(hotelDto.getBookingDate());
			hotel.setLocation(hotelDto.getLocation());
			hotel.setName(hotelDto.getName());
		}
		hotelRepository.save(hotel);
		hotelDto.setId(hotel.getId());
		return hotelDto;
	}

	@Override
	public String deleteHotel(Long id) {
		String message = "Not deleted";
		Optional<Hotel> optionalHotel = hotelRepository.findById(id);
		if(optionalHotel.isPresent()) {
			hotelRepository.deleteById(id);
			message = "Deleted";
		}
		return message;
	}

	@Override
	public String bookHotel(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HotelDto> searchHotel(String location, Date bookingDate) {
		List<Hotel> hotelList = new ArrayList<Hotel>();
		List<HotelDto> hotelDtoList = new ArrayList<HotelDto>();
		hotelList = hotelRepository.findByLocationAndBookingDate(location, bookingDate);
		for(Hotel hotel: hotelList) {
			HotelDto hotelDto = new HotelDto();
			hotelDto.setBookingDate(hotel.getBookingDate());
			hotelDto.setLocation(hotel.getLocation());
			hotelDto.setName(hotel.getName());
			hotelDto.setId(hotel.getId());
			hotelDtoList.add(hotelDto);
		}
		return hotelDtoList;
	}

	@Override
	public List<HotelDto> getAllHotels() {
		List<HotelDto> hotelDtoList = new ArrayList<>();
		List<Hotel> hotelList = (List<Hotel>) hotelRepository.findAll();
		for(Hotel hotel: hotelList) {
			HotelDto hotelDto = new HotelDto();
			hotelDto.setBookingDate(hotel.getBookingDate());
			hotelDto.setLocation(hotel.getLocation());
			hotelDto.setName(hotel.getName());
			hotelDto.setId(hotel.getId());
			hotelDtoList.add(hotelDto);
		}
		return hotelDtoList;
	}

	@Override
	public HotelDto getHotelById(Long id) {
		HotelDto hotelDto = new HotelDto();
		Optional<Hotel> optionalHotelDto = hotelRepository.findById(id);
		Hotel hotel = optionalHotelDto.get();
		hotelDto.setBookingDate(hotel.getBookingDate());
		hotelDto.setLocation(hotel.getLocation());
		hotelDto.setName(hotel.getName());
		hotelDto.setId(hotel.getId());
		return hotelDto;
	}	

}
