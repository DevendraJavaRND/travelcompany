package com.microservices.flightservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.flightservice.entity.Flight;
import com.microservices.flightservice.model.FlightDto;
import com.microservices.flightservice.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService{

	@Autowired
	private FlightRepository flightRepository;
	@Override
	public FlightDto addFlight(FlightDto flightDto) {
		Flight flight= new Flight();
		flight.setBookingDate(flightDto.getBookingDate());
		flight.setDestination(flightDto.getDestination());
		flight.setOrigin(flightDto.getOrigin());
		flightRepository.save(flight);
		flightDto.setId(flight.getId());
		return flightDto;
	}

	@Override
	public FlightDto updateFlight(FlightDto flightDto) {
		Flight flight= new Flight();
		Optional<Flight> optionalFlight = flightRepository.findById(flightDto.getId());
		if(optionalFlight.isPresent()) {
			flight.setId(flightDto.getId());
			flight.setBookingDate(flightDto.getBookingDate());
			flight.setDestination(flightDto.getDestination());
			flight.setOrigin(flightDto.getOrigin());
			flightRepository.save(flight);
		}
		//flightDto.setId(flight.getId());
		return flightDto;	}

	@Override
	public String deleteFlight(Long id) {
		String message = "Not deleted";
		Optional<Flight> optionalFlight = flightRepository.findById(id);
		if(optionalFlight.isPresent()) {
			flightRepository.deleteById(id);
			message = "Deleted";
		}
		return message;
	}
	
	@Override
	public String bookFlight(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FlightDto> searchFlight(String origin, String destination) {
		List<Flight> flightList = new ArrayList<Flight>();
		List<FlightDto> flightDtoList = new ArrayList<FlightDto>();
		flightList = flightRepository.findByOriginAndDestination(origin, destination);
		for(Flight flight: flightList) {
			FlightDto flightDto = new FlightDto();
			flightDto.setId(flight.getId());
			flightDto.setOrigin(flight.getOrigin());
			flightDto.setDestination(flight.getDestination());
			flightDto.setBookingDate(flight.getBookingDate());
			flightDtoList.add(flightDto);
		}
		return flightDtoList;
	}

	public List<FlightDto> getAllFlights() {
		List<FlightDto> flightDtoList = new ArrayList<>();
		List<Flight> listFlight = (List<Flight>) flightRepository.findAll();
		for(Flight flight: listFlight) {
			FlightDto flightDto = new FlightDto();
			flightDto.setId(flight.getId());
			flightDto.setBookingDate(flight.getBookingDate());
			flightDto.setDestination(flight.getDestination());
			flightDto.setOrigin(flight.getOrigin());
			flightDtoList.add(flightDto);
		}
		return flightDtoList;
	}

	@Override
	public FlightDto getFlightById(Long id) {
		FlightDto flightDto = new FlightDto();
		Optional<Flight> optionalFlightDto = flightRepository.findById(id);
		Flight flight = optionalFlightDto.get();
		flightDto.setId(flight.getId());
		flightDto.setBookingDate(flight.getBookingDate());
		flightDto.setDestination(flight.getDestination());
		flightDto.setOrigin(flight.getOrigin());
		return flightDto;
	}
	
	

}
