package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import model.User;

public class UserServiceTest {

	UserService userService;
	String firstname;
	String lastname;
	String email;
	String password;
	String repeatPassword;
	
	@BeforeEach
	public void persiapan() {
		userService = new UserServiceImpl();// polymorph
		firstname = "Antoni";
		lastname = "Kurniawan";
		email = "antoni@email.com";
		password = "rahasia";
		repeatPassword = "rahasia";
	}

	// green test / positive test
	@DisplayName("User object is created")
	@Test
//	@Timeout(value=2500,unit=TimeUnit.MILLISECONDS)
	public void testCreateUser_WhenDetailsProvide_ReturnUserObject() {
		// act
		User user = userService.createUser(firstname, lastname, email, password, repeatPassword);
		
		assertTimeout(Duration.ofMillis(500), ()->{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		// assert
		assertNotNull(user, "if createUser success shouldn't return null");
		assertEquals(firstname, user.getFirstName(), "User first name is incorrect");
		assertEquals(lastname, user.getLastName(), "User last name is incorrect");
		assertEquals(email, user.getEmail(), "User email is incorrect");
		assertNotNull(user.getId(), "User id cannot null");
	}

	// red test / negative test
	@DisplayName("Empty first name cause exception")
	@Test
	public void testCreateUser_WhenFirstNameIsEmpty_ThrowsIllegalException() {
		// arrange
		firstname = "";
		String expectedExceptionMessage = "User first name is empty";

		// act
		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.createUser(firstname, lastname, email, password, repeatPassword);
		}, "Empty first name cause Illegal Argument Exception");

		// assert
		assertEquals(expectedExceptionMessage, thrown.getMessage());
	}

	// red test / negative test
	@DisplayName("Empty last name cause exception")
	@Test
	public void testCreateUser_WhenLastNameIsEmpty_ThrowsIllegalException() {
		// arrange
		lastname = "";
		String expectedExceptionMessage = "User last name is empty";

		// act
		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.createUser(firstname, lastname, email, password, repeatPassword);
		}, "Empty last name cause Illegal Argument Exception");

		// assert
		assertEquals(expectedExceptionMessage, thrown.getMessage());
	}

	// red test / negative test
	@DisplayName("Empty email cause exception")
	@Test
	public void testCreateUser_WhenEmailIsEmpty_ThrowsIllegalException() {
		// arrange
		email = "";
		String expectedExceptionMessage = "User email is empty";

		// act
		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			userService.createUser(firstname, lastname, email, password, repeatPassword);
		}, "Empty email cause Illegal Argument Exception");

		// assert
		assertEquals(expectedExceptionMessage, thrown.getMessage());
	
	}
	
}