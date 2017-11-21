/**
 * 
 */
package com.otrs.restaurant.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.otrs.restaurant.model.Restaurant;
import com.otrs.restaurant.service.RestaurantService;

/**
 * @author Kundan
 *
 */
@Controller
@RequestMapping("/api/restaurant")
public class RestaurantController {

	@Autowired
	RestaurantService restaurantService;
	
	@PostMapping(path="/")
	public @ResponseBody String addNewRestaurant (@Valid @RequestBody Restaurant restaurant) {
		restaurantService.saveRestaurant(restaurant);
		return "Saved";
	}

	@GetMapping(path="/")
	public @ResponseBody Iterable<Restaurant> getAllRestaurants() {
		return restaurantService.getAllRestaurants();
	}
	
	@GetMapping(path="/{city}")
	public @ResponseBody Iterable<Restaurant> getRestaurantByCity(@PathVariable String city) {
		return restaurantService.getRestaurantsByCity(city);
	}
	
}
