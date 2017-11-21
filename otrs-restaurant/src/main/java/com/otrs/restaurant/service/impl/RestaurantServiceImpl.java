/**
 * 
 */
package com.otrs.restaurant.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otrs.restaurant.model.Restaurant;
import com.otrs.restaurant.repository.RestaurantRepository;
import com.otrs.restaurant.service.RestaurantService;

/**
 * @author Kundan
 *
 */

@Service("restarantService")
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	RestaurantRepository restaurantRepository;

	/* (non-Javadoc)
	 * @see com.otrs.restaurant.service.RestaurantService#saveRestaurant(com.otrs.restaurant.model.Restaurant)
	 */
	@Override
	public boolean saveRestaurant(Restaurant restaurant) {
		restaurantRepository.save(restaurant);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.otrs.restaurant.service.RestaurantService#getAllRestaurants()
	 */
	@Override
	public List<Restaurant> getAllRestaurants() {
		return restaurantRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.otrs.restaurant.service.RestaurantService#getRestaurantsByCity(java.lang.String)
	 */
	@Override
	public List<Restaurant> getRestaurantsByCity(String city) {
		return restaurantRepository.findByCity(city);
	}

}
