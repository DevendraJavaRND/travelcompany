package com.microservices.hotelservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.hotelservice.client.CustomerClient;
import com.microservices.hotelservice.model.HotelDto;
import com.microservices.hotelservice.service.HotelService;
@RestController
@RequestMapping("/api")
public class HotelController {
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private CustomerClient customerClient;
	
	@GetMapping("/hotel")
	//@PreAuthorize("hasAnyAuthority('ROLE_STUDENT', 'ROLE_ADMIN')")
	public ResponseEntity<List<HotelDto>> getAllHotelHandler(){
		List<HotelDto> listHotelDto = null;
		try {
			listHotelDto = hotelService.getAllHotels();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(listHotelDto.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(listHotelDto);
	}


	@GetMapping("/hotel/{id}")
	//@PreAuthorize("hasAnyAuthority('ROLE_STUDENT', 'ROLE_ADMIN')")
	public  ResponseEntity<HotelDto> getHotelById(@PathVariable("id") Long id){
		HotelDto hotelDto = null;
		try {
			hotelDto = hotelService.getHotelById(id);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(hotelDto);
	}
	
//	@GetMapping("/hotel/{location}")
//	//@PreAuthorize("hasAnyAuthority('ROLE_STUDENT', 'ROLE_ADMIN')")
//	public ResponseEntity<List<HotelDto>> searchHotel(
//			@PathVariable("location") String location,
//            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date bookingDate){
//		
//		List<HotelDto> listHotelDto = null;
//		try {
//			listHotelDto = hotelService.searchHotel(location, bookingDate);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if(listHotelDto.size() <= 0) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.ok(listHotelDto);
//	}

	@PostMapping("/hotel")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<HotelDto> addHotel(@RequestBody HotelDto hotelDto){
		try {
			hotelService.addHotel(hotelDto);
			return ResponseEntity.ok(hotelDto);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/hotel")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<HotelDto> updateHotel(@RequestBody HotelDto hotelDto){
		try {
			hotelService.updateHotel(hotelDto);
			return ResponseEntity.ok(hotelDto);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
	}

	@DeleteMapping("/hotel/{id}")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> deleteHotel(@PathVariable("id") Long id){
		String message = "";
		try {
			message = hotelService.deleteHotel(id);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/with-customers")
	//@PreAuthorize("hasAnyAuthority('ROLE_STUDENT', 'ROLE_ADMIN')")
	public ResponseEntity<List<HotelDto>> getAllHotelsWithCustomers(){
		List<HotelDto> listHotelDto = null;
		try {
			listHotelDto = hotelService.getAllHotels();
			for(HotelDto hotelDto: listHotelDto) {
				hotelDto.setListCustomer(customerClient.findByHotel(hotelDto.getId()));
			}
			//listAuthorDto = customerClient.findByFlight(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(listHotelDto.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(listHotelDto);
	}
}
