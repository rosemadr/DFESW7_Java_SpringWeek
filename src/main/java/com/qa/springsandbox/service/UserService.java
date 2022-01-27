package com.qa.springsandbox.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.springsandbox.data.entity.User;
import com.qa.springsandbox.data.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getAll() {
		return this.userRepository.findAll();
	}

	public User getById(Long id) {
		return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	public User create(User user) {
		User newUser = userRepository.save(user);
		return newUser;

	}

	public User update(Long id, User user) {
		// check for user
		if (userRepository.existsById(id)) {
			// get that user
			User userInDb = userRepository.getById(id);
			// update
			userInDb.setAge(user.getAge());
			userInDb.setForename(user.getForename());
			userInDb.setSurname(user.getSurname());

			return userRepository.save(userInDb);
		} else {
			throw new EntityNotFoundException();
		}
	}

	public void delete(Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			System.out.println("The user with id " + " has been deleted.");
		} else {
			throw new EntityNotFoundException();
		}
	}

	// etc

}
