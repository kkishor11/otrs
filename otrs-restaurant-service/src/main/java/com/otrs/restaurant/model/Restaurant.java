/**
 * 
 */
package com.otrs.restaurant.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author S714272
 *
 */

@Entity
public class Restaurant implements Serializable{

	private static final long serialVersionUID = -1380637404858800893L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer restaurantId;
	private String name;
	private String city;
	private Long noOfTables;
	
	public Restaurant() {
	}
	
	public Restaurant(Integer restaurantId, String name, String city, Long noOfTables) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.city = city;
		this.noOfTables = noOfTables;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Long getNoOfTables() {
		return noOfTables;
	}
	public void setNoOfTables(Long noOfTables) {
		this.noOfTables = noOfTables;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Restaurant [restaurantId=");
		builder.append(restaurantId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", city=");
		builder.append(city);
		builder.append(", noOfTables=");
		builder.append(noOfTables);
		builder.append("]");
		return builder.toString();
	}

}
