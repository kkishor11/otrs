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
import javax.persistence.UniqueConstraint;

/**
 * @author S714272
 *
 */

@Entity
public class Pricing implements Serializable{

	private static final long serialVersionUID = -2190220144288660460L;

	@Id
	private Integer restaurantId;
	private Double pricePerTable;
	
	public Pricing() {
	}

	public Pricing(Integer restaurantId, Double pricePerTable) {
		this.restaurantId = restaurantId;
		this.pricePerTable = pricePerTable;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Double getPricePerTable() {
		return pricePerTable;
	}

	public void setPricePerTable(Double pricePerTable) {
		this.pricePerTable = pricePerTable;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pricing [restaurantId=");
		builder.append(restaurantId);
		builder.append(", pricePerTable=");
		builder.append(pricePerTable);
		builder.append("]");
		return builder.toString();
	}
	
}
