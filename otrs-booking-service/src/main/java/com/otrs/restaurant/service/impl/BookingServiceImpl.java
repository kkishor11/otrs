/**
 * 
 */
package com.otrs.restaurant.service.impl;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.otrs.restaurant.model.Booking;
import com.otrs.restaurant.repository.BookingRepository;
import com.otrs.restaurant.service.BookingService;
import com.otrs.restaurant.utils.SaveStatus;

/**
 * @author Kundan
 *
 */

@Service("bookingService")
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepository bookingRepository;

	private Logger logger = Logger.getLogger(BookingServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.otrs.restaurant.service.RestaurantService#saveRestaurant(com.otrs.
	 * restaurant.model.Restaurant)
	 */
	@Override
	public String saveBooking(Booking booking) {
		try {
			bookingRepository.save(booking);
		} catch (Exception e) {
			if (e instanceof DataIntegrityViolationException) {
				logger.error(e.getMessage());
				return SaveStatus.DUPLICATE.statusText();
			}
			return SaveStatus.FAILED.statusText();
		}
		return SaveStatus.SUCCESS.statusText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.otrs.restaurant.service.RestaurantService#getRestaurantsByCity(java.lang.
	 * String)
	 */
	@Override
	public List<Booking> getBookingsForUser(String email) {
		return bookingRepository.findBookingsForUser(email);
	}

	@Override
	public String clearBooking(Integer bookingId) {
		List<Booking> bookings = bookingRepository.findBookingById(bookingId);
		if (!CollectionUtils.isEmpty(bookings)) {
			bookingRepository.delete(bookings.get(0));
			return "Booking Cleared";
		}
		return "Error";
	}

}
