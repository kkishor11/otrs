/**
 * 
 */
package com.otrs.restaurant.service;

import java.util.List;

import com.otrs.restaurant.model.User;

/**
 * @author Kundan
 *
 */
public interface UserService {

	public String saveUser(User user);
	public List<User> getUserByEmail(String email);
	
}
