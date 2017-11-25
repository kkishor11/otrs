/**
 * 
 */
package com.otrs.restaurant.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.otrs.restaurant.model.Restaurant;
import com.otrs.restaurant.service.RestaurantService;

/**
 * @author Kundan
 *
 */
@RestController
@RequestMapping("/api/restaurant")
public class RestaurantResource {

	@Autowired
	RestaurantService restaurantService;
	
	@PostMapping(path="")
	public @ResponseBody String addNewRestaurant (@Valid @RequestBody Restaurant restaurant) {
		return restaurantService.saveRestaurant(restaurant);
	}

	@GetMapping(path="")
	public @ResponseBody Iterable<Restaurant> getAllRestaurants() {
		return restaurantService.getAllRestaurants();
	}
	
	@GetMapping(path="/{city}")
	public @ResponseBody Iterable<Restaurant> getRestaurantByCity(@PathVariable String city) {
		return restaurantService.getRestaurantsByCity(city);
	}
	
}
