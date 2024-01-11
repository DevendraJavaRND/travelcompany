package com.microservices.flightservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservices.flightservice.entity.Flight;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long>{
	List<Flight> findByOriginAndDestination(String origin, String destination);
}
