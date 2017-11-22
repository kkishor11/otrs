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

import com.otrs.restaurant.model.Pricing;
import com.otrs.restaurant.service.PricingService;

/**
 * @author Kundan
 *
 */
@Controller
@RequestMapping("/api/pricing")
public class PricingController {

	@Autowired
	PricingService pricingService;
	
	@PostMapping(path="/")
	public @ResponseBody String addNewRestaurant (@Valid @RequestBody Pricing pricing) {
		return pricingService.savePricing(pricing);
	}

	@GetMapping(path="/{restaurantId}")
	public @ResponseBody Iterable<Pricing> getRestaurantByCity(@PathVariable Integer restaurantId) {
		return pricingService.getPricing(restaurantId);
	}
	
}
