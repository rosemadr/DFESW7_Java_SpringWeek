package com.qa.springsandbox.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qa.springsandbox.data.entity.User;
import com.qa.springsandbox.service.UserService;

@RestController
@RequestMapping(path = "/user")

public class UserController {

	private UserService userService;

	@Autowired // Indicates repository injected by dependency injection
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping // localhost:8080/user
	public ResponseEntity<List<User>> getUsers() {
		ResponseEntity<List<User>> users = ResponseEntity.ok(userService.getAll());
		return users;
	}

	// {id} is a path variable
	@RequestMapping(path = "/{id}", method = { RequestMethod.GET })
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
		User savedUser = userService.getById(id);

		ResponseEntity<User> response = ResponseEntity.status(HttpStatus.OK).body(savedUser); // ??

		return response;
	}

	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userService.create(user);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/user/ " + String.valueOf(savedUser.getId()));

		ResponseEntity<User> response = new ResponseEntity<User>(savedUser, headers, HttpStatus.CREATED);
		return response;
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @Valid @RequestBody User user) {
		User updatedUser = userService.update(id, user);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/user/ " + String.valueOf(updatedUser.getId()));

		return new ResponseEntity<User>(updatedUser, headers, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		userService.delete(id);
		return ResponseEntity.accepted().build();

	}

}
