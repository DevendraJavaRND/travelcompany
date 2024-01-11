package com.microservices.flightservice.service;

import java.util.List;

import com.microservices.flightservice.model.FlightDto;

public interface FlightService {
	public FlightDto addFlight(FlightDto flightDto);
	public FlightDto updateFlight(FlightDto flightDto);
	public String deleteFlight(Long id);
	public String bookFlight(Long id);
	public List<FlightDto> searchFlight(String origin, String destination);
	public List<FlightDto> getAllFlights();
	public FlightDto getFlightById(Long id);
}
