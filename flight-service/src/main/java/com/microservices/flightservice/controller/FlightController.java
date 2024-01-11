package com.microservices.flightservice.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.flightservice.client.CustomerClient;
import com.microservices.flightservice.model.FlightDto;
import com.microservices.flightservice.service.FlightService;
@RestController
@RequestMapping("/api")
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private CustomerClient customerClient;
	
	@GetMapping("/flight")
	//@PreAuthorize("hasAnyAuthority('ROLE_STUDENT', 'ROLE_ADMIN')")
	public ResponseEntity<List<FlightDto>> getAllFlightHandler(){
		List<FlightDto> listAuthorDto = null;
		try {
			listAuthorDto = flightService.getAllFlights();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(listAuthorDto.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(listAuthorDto);
	}


	@GetMapping("/flight/{id}")
	//@PreAuthorize("hasAnyAuthority('ROLE_STUDENT', 'ROLE_ADMIN')")
	public  ResponseEntity<FlightDto> getFlightByIdHandler(@PathVariable("id") Long id){
		FlightDto flightDto = null;
		try {
			flightDto = flightService.getFlightById(id);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(flightDto);
	}
	
	@GetMapping("/flight/{origin}/{destination}")
	//@PreAuthorize("hasAnyAuthority('ROLE_STUDENT', 'ROLE_ADMIN')")
	public ResponseEntity<List<FlightDto>> searchFlight(
			@PathVariable String origin,
            @PathVariable String destination){
		
		List<FlightDto> listFlightDto = null;
		try {
			listFlightDto = flightService.searchFlight(origin, destination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(listFlightDto.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(listFlightDto);
	}

	@PostMapping("/flight")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<FlightDto> addFlight(@RequestBody FlightDto flightDto){
		try {
			flightService.addFlight(flightDto);
			return ResponseEntity.ok(flightDto);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/flight")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<FlightDto> updateFlight(@RequestBody FlightDto flightDto){
		try {
			flightService.updateFlight(flightDto);
			return ResponseEntity.ok(flightDto);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
	}

	@DeleteMapping("/flight/{id}")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> deleteFlight(@PathVariable("id") Long id){
		String message = "";
		try {
			message = flightService.deleteFlight(id);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/with-customers")
	//@PreAuthorize("hasAnyAuthority('ROLE_STUDENT', 'ROLE_ADMIN')")
	public ResponseEntity<List<FlightDto>> getAllFlightWithCustomers(){
		List<FlightDto> listFlightDto = null;
		try {
			listFlightDto = flightService.getAllFlights();
			for(FlightDto flightDto: listFlightDto) {
				flightDto.setListCustomer(customerClient.findByFlight(flightDto.getId()));
			}
			//listAuthorDto = customerClient.findByFlight(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(listFlightDto.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(listFlightDto);
	}
}
