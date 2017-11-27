/**
 * 
 */
package com.otrs.restaurant.service.impl;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
			sendBookingEmail(booking.getUserId(), booking.getPrice(), booking.getRestaurantName(), booking.getNoOfBookedTables());
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

	private void sendBookingEmail(String emailId, Double amount, String restaurantName, Integer tables) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("kundanmca123@gmail.com", "pepfpeeyovdwersh");
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("kundanmca123@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailId));
			message.setSubject("Your booking Details");
			message.setText("Dear User," 
					+ "\nThis is a confirmation email for your booking." 
					+ "\nRestaurant Name : " + restaurantName
					+ "\nTables Booked : " + tables
					+ "\nAmount Payable : " + amount
					+ "\n\nThank you for booking with OTRS."
					+ "\nRegards,\nOTRS");

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
