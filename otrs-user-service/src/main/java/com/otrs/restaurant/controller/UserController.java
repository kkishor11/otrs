/**
 * 
 */
package com.otrs.restaurant.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.otrs.restaurant.model.ServiceResponse;
import com.otrs.restaurant.model.User;
import com.otrs.restaurant.service.UserService;

/**
 * @author Kundan
 *
 */
@Controller
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping(path="/register")
	public @ResponseBody ServiceResponse register (@Valid @RequestBody User user) {
		String response = userService.saveUser(user);
		return new ServiceResponse(response, HttpStatus.CREATED.name());
	}

	@GetMapping(path="/profile/{email:.+}")
	public @ResponseBody Iterable<User> getUserByEmail(@PathVariable String email) {
		return userService.getUserByEmail(email);
	}
	
}
