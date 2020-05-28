package com.example.Betting.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Betting.model.User;
import com.example.Betting.service.UserService;

/**
 * The Class UserController.
 */
@RestController
@RequestMapping("/api")
public class UserController {

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Gets the user.
	 *
	 * @param id the id
	 * @return the user
	 */
	@GetMapping("/user/{id}")
	ResponseEntity<?> getUser(@PathVariable final Long id) {
		Optional<User> user = userService.findUserById(id);
		return user.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
