/**
 * 
 */
package com.otrs.restaurant.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Kundan
 *
 */

@Entity
public class Booking implements Serializable{

	private static final long serialVersionUID = 6588340604150444967L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingId;
	private Integer restaurantId;
	private Integer noOfBookedTables;
	private String userId;
	private Date bookingDate;
	
	public Booking() {
	}
	public Booking(Integer bookingId, Integer restaurantId, Integer noOfBookedTables, String userId, Date bookingDate) {
		this.bookingId = bookingId;
		this.restaurantId = restaurantId;
		this.noOfBookedTables = noOfBookedTables;
		this.userId = userId;
		this.bookingDate = bookingDate;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public Integer getNoOfBookedTables() {
		return noOfBookedTables;
	}
	public void setNoOfBookedTables(Integer noOfBookedTables) {
		this.noOfBookedTables = noOfBookedTables;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Booking [bookingId=");
		builder.append(bookingId);
		builder.append(", restaurantId=");
		builder.append(restaurantId);
		builder.append(", noOfBookedTables=");
		builder.append(noOfBookedTables);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", bookingDate=");
		builder.append(bookingDate);
		builder.append("]");
		return builder.toString();
	}
	
}