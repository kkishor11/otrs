/**
 * 
 */
package com.otrs.restaurant.service;

import java.util.List;

import com.otrs.restaurant.model.Restaurant;
import com.otrs.restaurant.utils.SaveStatus;

/**
 * @author Kundan
 *
 */
public interface RestaurantService {

	public String saveRestaurant(Restaurant restaurant);
	public List<Restaurant> getAllRestaurants();
	public List<Restaurant> getRestaurantsByCity(String city);
	
}
