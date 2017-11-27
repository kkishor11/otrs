/**
 * 
 */
package com.otrs.restaurant.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.otrs.restaurant.model.Booking;

/**
 * @author Kundan
 *
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	@Query("select booking from Booking booking where booking.userId = ?")
	List<Booking> findBookingsForUser(String email);
	
	@Query("select booking from Booking booking where booking.bookingId = ?")
	List<Booking> findBookingById(Integer bookignId);
	
	@Query("select booking from Booking booking where booking.restaurantId = ? ")
	List<Booking> findBookingByRestaurant(Integer restaurantId);


}
