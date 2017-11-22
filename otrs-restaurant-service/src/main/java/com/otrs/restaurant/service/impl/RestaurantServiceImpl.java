/**
 * 
 */
package com.otrs.restaurant.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.otrs.restaurant.model.Restaurant;
import com.otrs.restaurant.repository.RestaurantRepository;
import com.otrs.restaurant.service.RestaurantService;
import com.otrs.restaurant.utils.SaveStatus;

/**
 * @author Kundan
 *
 */

@Service("restarantService")
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	private Logger logger = Logger.getLogger(RestaurantServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.otrs.restaurant.service.RestaurantService#saveRestaurant(com.otrs.restaurant.model.Restaurant)
	 */
	@Override
	public String saveRestaurant(Restaurant restaurant) {
		try {
			restaurantRepository.save(restaurant);
		} catch(Exception e) {
			if (e instanceof DataIntegrityViolationException) {
				logger.error(e.getMessage());
				return SaveStatus.DUPLICATE.statusText();
			}
			return SaveStatus.FAILED.statusText();
		}
		return SaveStatus.SUCCESS.statusText();
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
