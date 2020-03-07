package com.example.Betting.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Betting.model.User;
import com.example.Betting.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	private UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	@GetMapping("/user/{id}")
	ResponseEntity<?> getUser(@PathVariable Long id){
		Optional<User> user = userRepository.findById(id);
		return user.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
