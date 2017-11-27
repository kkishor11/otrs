/**
 * 
 */
package com.otrs.restaurant.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.otrs.restaurant.model.Booking;
import com.otrs.restaurant.model.ServiceResponse;
import com.otrs.restaurant.service.BookingService;
import com.otrs.restaurant.utils.SaveStatus;

/**
 * @author Kundan
 *
 */
@Controller
@RequestMapping("/api/bookings")
public class BookingController {

	@Autowired
	BookingService bookingService;
	
	@PostMapping(path="/")
	public @ResponseBody ServiceResponse addNewBooking (@Valid @RequestBody Booking booking) {
		String response = bookingService.saveBooking(booking);
		if(response.equals(SaveStatus.LIMIT_EXCEEDED.statusText())) {
			return new ServiceResponse(response, HttpStatus.UNPROCESSABLE_ENTITY.name());
		}
		return new ServiceResponse(response, HttpStatus.CREATED.name());
	}
	
	@GetMapping(path="/{email:.+}")
	public @ResponseBody Iterable<Booking> getBookingsForUser(@PathVariable String email) {
		return bookingService.getBookingsForUser(email);
	}
	
	@DeleteMapping(path="/{bookingId}")
	public @ResponseBody ServiceResponse clearBooking(@PathVariable Integer bookingId) {
		String response = bookingService.clearBooking(bookingId);
		return new ServiceResponse(response, HttpStatus.ACCEPTED.name());
	}
	
}
