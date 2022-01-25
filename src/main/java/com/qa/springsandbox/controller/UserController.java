package com.qa.springsandbox.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

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

@RestController
@RequestMapping(path = "/user")

public class UserController {

	private static long counter = 0;

	private List<User> users = new ArrayList<>(
			List.of(new User(counter++, "Peter", "Parker", 20), new User(counter++, "Bruce", "Banner", 35)));

	@GetMapping // localhost:8080/user
	public List<User> getUsers() {
		return users;
	}

	// {id} is a path variable
	@RequestMapping(path = "/{id}", method = { RequestMethod.GET })
	public User getUserById(@PathVariable("id") int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		throw new EntityNotFoundException("Entity with id " + id + " was not found.");
	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		user.setId(counter++);
		users.add(user);
		return user;
	}

	@PutMapping("/{id}")
	public User updateUser(@PathVariable("id") long id, @RequestBody User user) {
		// TODO: Update user in list if they exist
		return null;
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") long id) {
		// TODO: Update user in list if they exist
	}

	private boolean userExists(long id) {
		for(User user : users)
	}

}
