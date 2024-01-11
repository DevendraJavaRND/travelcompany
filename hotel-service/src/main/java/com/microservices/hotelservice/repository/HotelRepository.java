package com.microservices.hotelservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservices.hotelservice.entity.Hotel;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long>{
	List<Hotel> findByLocationAndBookingDate(String location, Date bookingDate);
}
