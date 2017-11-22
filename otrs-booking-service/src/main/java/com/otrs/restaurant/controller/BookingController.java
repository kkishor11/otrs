/**
 * 
 */
package com.otrs.restaurant.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.otrs.restaurant.model.Booking;
import com.otrs.restaurant.service.BookingService;

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
	public @ResponseBody String addNewBooking (@Valid @RequestBody Booking booking) {
		return bookingService.saveBooking(booking);
	}
	
	@GetMapping(path="/{email}")
	public @ResponseBody Iterable<Booking> getRestaurantByCity(@PathVariable String email) {
		return bookingService.getBookingsForUser(email);
	}
	
	@DeleteMapping(path="/{bookingId}")
	public @ResponseBody String clearBooking(@PathVariable Integer bookingId) {
		return bookingService.clearBooking(bookingId);
	}
	
}
