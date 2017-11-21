/**
 * 
 */
package com.otrs.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.otrs.restaurant.model.Restaurant;

/**
 * @author Kundan
 *
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	
	@Query("select restaurant from Restaurant restaurant where restaurant.city = ?")
	List<Restaurant> findByCity(String city);

}
