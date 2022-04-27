package com.eureka.discovery.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.eureka.discovery.model.ChangePassword;
import com.eureka.discovery.model.User;
import com.eureka.discovery.model.UserForm1;

@SpringBootTest
class UserServiceImplTest {

	@Autowired
	private UserForm1 userForm1;
	
	@Autowired
	private ChangePassword changePassword;

	@Autowired
	private User user;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Test
	void testGetEmailAddress1() {
		String excepted = "User Not Exist";
		String result = userServiceImpl.getEmailAddress(4);
		Assertions.assertEquals(excepted, result);

	}

	@Test
	void testGetEmailAddress2() {
		String excepted = "harshkaurav37@gmail.com";
		String result = userServiceImpl.getEmailAddress(1);
		Assertions.assertEquals(excepted, result);

	}

	@Test
	void testUserExistById1() {
		Assertions.assertEquals(false, userServiceImpl.userExistById(10001L));
	}

	@Test
	void testUserExistById2() {
		Assertions.assertEquals(true, userServiceImpl.userExistById(1L));
	}

	@Test
	void testUpdateUser1() {
		user.setUser_id(123L);
		Assertions.assertEquals("!!! User is not in database , please first register yourselves or check user_id ",
				userServiceImpl.updateUser(user));
	}

	@Test
	void testUpdateUser2() {
		user.setUser_id(1L);
		user.setEmail_id("asdas");
		Assertions.assertEquals("!!! Invalid Email Address !!! Please Take of this Format email@mail.com",
				userServiceImpl.updateUser(user));
	}

	@Test
	void testUpdateUser3() {
		user.setUser_id(1L);
		user.setEmail_id("harshkaurav37@gmail.com");
		user.setAge(-1);
		Assertions.assertEquals("Please Check Your Age", userServiceImpl.updateUser(user));
	}

	@Test
	public void testUpdateUser4() {
		user.setAge(21);
		user.setFull_name("");
		Assertions.assertEquals("Name Cannot Be Blank", userServiceImpl.updateUser(user));
	}


	@Test
	void testGetUser() {
		Assertions.assertEquals((new User(0L,"no User Exist",0,"null","null","null",0L,"null","null", LocalDate.now(),"null",new HashMap<>())).getUser_id(),userServiceImpl.getUser(10001).getUser_id());
	}

	@Test
	void testChangePassword1() {
		changePassword.setUser_id(1000000);
		Assertions.assertEquals(
				"!!! User is not in database , please first register yourselves or check user_id ",
				userServiceImpl.changePassword(changePassword));

	}
	
	
	
	@Test
	void testChangePassword2() {
		changePassword.setUser_id(1);
		changePassword.setNew_password("hello");
		changePassword.setConfirm_password("Hello");
		Assertions.assertEquals(
				"Password Mismatched",
				userServiceImpl.changePassword(changePassword));

	}
	
	
	


}
