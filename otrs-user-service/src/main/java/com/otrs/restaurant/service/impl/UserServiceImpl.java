/**
 * 
 */
package com.otrs.restaurant.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.otrs.restaurant.model.User;
import com.otrs.restaurant.repository.UserRepository;
import com.otrs.restaurant.service.UserService;
import com.otrs.restaurant.utils.SaveStatus;

/**
 * @author Kundan
 *
 */

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	private Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Override
	public String saveUser(User user) {
		try {
			userRepository.save(user);
		} catch(Exception e) {
			if (e instanceof DataIntegrityViolationException) {
				logger.error(e.getMessage());
				return SaveStatus.DUPLICATE.statusText();
			} 
			return SaveStatus.FAILED.statusText();
		}
		return SaveStatus.SUCCESS.statusText();
	}

	@Override
	public List<User> getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
