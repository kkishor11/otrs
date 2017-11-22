/**
 * 
 */
package com.otrs.restaurant.service;

import java.util.List;

import com.otrs.restaurant.model.Booking;

/**
 * @author Kundan
 *
 */
public interface BookingService {

	public String saveBooking(Booking booking);
	public List<Booking> getBookingsForUser(String email);
	public String clearBooking(Integer bookingId);
}
