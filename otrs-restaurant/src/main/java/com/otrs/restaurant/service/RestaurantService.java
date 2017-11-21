/**
 * 
 */
package com.otrs.restaurant.service;

import java.util.List;

import com.otrs.restaurant.model.Restaurant;

/**
 * @author Kundan
 *
 */
public interface RestaurantService {

	public boolean saveRestaurant(Restaurant restaurant);
	public List<Restaurant> getAllRestaurants();
	public List<Restaurant> getRestaurantsByCity(String city);
	
}
