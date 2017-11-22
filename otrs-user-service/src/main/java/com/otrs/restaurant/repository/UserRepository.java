/**
 * 
 */
package com.otrs.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.otrs.restaurant.model.User;

/**
 * @author Kundan
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("select user from User user where user.email = ?")
	List<User> findByEmail(String email);

}
