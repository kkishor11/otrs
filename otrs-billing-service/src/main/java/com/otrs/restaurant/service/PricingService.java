/**
 * 
 */
package com.otrs.restaurant.service;

import java.util.List;

import com.otrs.restaurant.model.Pricing;

/**
 * @author Kundan
 *
 */
public interface PricingService {

	public String savePricing(Pricing pricing);
	public List<Pricing> getPricing(Integer restaurantId);
	
}
