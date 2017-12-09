/**
 * 
 */
package com.otrs.restaurant.service.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

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
import org.springframework.scheduling.annotation.Scheduled;
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
			if (tablesAvailable(booking)) {
				bookingRepository.save(booking);
				sendBookingEmail(booking.getUserId(), booking.getPrice(), booking.getRestaurantName(),
						booking.getNoOfBookedTables());
			} else {
				return SaveStatus.LIMIT_EXCEEDED.statusText();
			}
		} catch (Exception e) {
			if (e instanceof DataIntegrityViolationException) {
				logger.error(e.getMessage());
				return SaveStatus.DUPLICATE.statusText();
			}
			return SaveStatus.FAILED.statusText();
		}
		return SaveStatus.SUCCESS.statusText();
	}

	private boolean tablesAvailable(Booking booking) {
		List<Booking> bookings = bookingRepository.findBookingByRestaurant(booking.getRestaurantId());
		//Filtering booking for the said date only.
		bookings = bookings.stream().filter(e -> {
			Calendar bookingDate = Calendar.getInstance();
			bookingDate.setTime(e.getBookingDate());
			bookingDate.set(Calendar.HOUR_OF_DAY, 0);
			bookingDate.set(Calendar.MINUTE, 0);
			bookingDate.set(Calendar.SECOND, 0);
			bookingDate.set(Calendar.MILLISECOND, 0);
			
			Calendar dbBookingDate = Calendar.getInstance();
			dbBookingDate.setTime(booking.getBookingDate());
			dbBookingDate.set(Calendar.HOUR_OF_DAY, 0);
			dbBookingDate.set(Calendar.MINUTE, 0);
			dbBookingDate.set(Calendar.SECOND, 0);
			dbBookingDate.set(Calendar.MILLISECOND, 0);
			
			return bookingDate.compareTo(dbBookingDate) == 0;
		}).collect(Collectors.toList());
		
		//If the number of booking is less than the number of tables available. Booking will be permitted
		if(booking.getTotalTables() > bookings.size()) {
			return true;
		} else {
			//Otherwise check the bookings for the exact timing
			return bookings.stream().anyMatch((e -> {
				
				Calendar dbBookingDatePlusTwo = Calendar.getInstance();
				dbBookingDatePlusTwo.setTime(e.getBookingDate());
				dbBookingDatePlusTwo.set(Calendar.HOUR_OF_DAY, dbBookingDatePlusTwo.get(Calendar.HOUR_OF_DAY));
				
				Calendar bookingDatePlusTwo = Calendar.getInstance();
				bookingDatePlusTwo.setTime(booking.getBookingDate());
				bookingDatePlusTwo.set(Calendar.HOUR_OF_DAY, bookingDatePlusTwo.get(Calendar.HOUR_OF_DAY));
				
				if (booking.getBookingDate().after(e.getBookingDate())
						&& booking.getBookingDate().before(dbBookingDatePlusTwo.getTime())
						&& bookingDatePlusTwo.getTime().after(e.getBookingDate())
						&& bookingDatePlusTwo.getTime().before(dbBookingDatePlusTwo.getTime())) {
					return false;
				}
				
				return true;
			}));
		}
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

		//Kindly put any other Gmail id and the password. I have removed mine for security purposes.
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("kundanmca123@gmail.com", "<PASSWORD>");
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("kundanmca123@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailId));
			message.setSubject("Your booking Details");
			message.setText("Dear User," + "\nThis is a confirmation email for your booking." + "\nRestaurant Name : "
					+ restaurantName + "\nTables Booked : " + tables + "\nAmount Payable : " + amount
					+ "\n\nThank you for booking with OTRS." + "\nRegards,\nOTRS");

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	@Scheduled(cron = "${booking.clean.scheduler}")
	public void clearPreviousBookings() {
		logger.info("Started booking scheduler");
		Calendar now = Calendar.getInstance();
		now.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY) - 2);
		List<Booking> bookings = bookingRepository.findAll();
		logger.info("Found total " + bookings.size() + " bookings");
		List<Booking> bookingsToClear = Optional.ofNullable(bookings).orElseGet(Collections::emptyList).stream()
				.filter(e -> e.getBookingDate().compareTo(now.getTime()) <= 0).collect(Collectors.toList());
		if (!CollectionUtils.isEmpty(bookingsToClear)) {
			logger.info("Clearing total " + bookingsToClear.size() + " bookings");
			bookingRepository.delete(bookingsToClear);
		}
		logger.info("Job Done");
	}

}
