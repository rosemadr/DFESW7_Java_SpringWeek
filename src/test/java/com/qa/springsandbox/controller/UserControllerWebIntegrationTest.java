package com.qa.springsandbox.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.springsandbox.data.entity.User;
import com.qa.springsandbox.service.UserService;

// @SpringBootTest // this will start a full application context
@WebMvcTest(UserController.class) // specify class we are testing
// Will start an application context with only beans for controller layer
public class UserControllerWebIntegrationTest {

	@Autowired // field injection
	private UserController controller;

	// we need a dummy UserService
	// we can use Mockito to create a mock object
	@MockBean
	private UserService userService;

	// we need some data for our tests
	private List<User> users;

	@BeforeEach // junit5 (jupiter) annotation to run this method before every test
	public void init() {
		users.addAll(List.of(new User(1, "bob", "le", 21), new User(1, "bobb", "lee", 22),
				new User(2, "bobby", "leee", 23)));
	}

	@Test
	public void getAllUsersTest() {
		ResponseEntity<List<User>> expected = new ResponseEntity<List<User>>(users, HttpStatus.OK);
		// given some initial conditions - this is being performed by init()

		// when (the action does occur)
		when(userService.getAll()).thenReturn(users);

		// then (assert)
		ResponseEntity<List<User>> actual = controller.getUsers();
		assertThat(expected).isEqualTo(actual);

		// we then need to verify that the service was called by the controller
		verify(userService, times(1)).getAll();
	}

}
