/**
 * 
 */
package com.otrs.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.otrs.restaurant.model.Pricing;

/**
 * @author Kundan
 *
 */
public interface PricingRepository extends JpaRepository<Pricing, Long> {
	
	@Query("select pricing from Pricing pricing where pricing.restaurantId = ?")
	List<Pricing> getPricing(Integer restaurantId);

}
