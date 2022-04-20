package com.eureka.discovery.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eureka.discovery.exception.InvalidContactNumberException;
import com.eureka.discovery.exception.InvalidEmailException;
import com.eureka.discovery.exception.InvalidException;
import com.eureka.discovery.exception.UserNotExistException;
import com.eureka.discovery.model.ChangePassword;
import com.eureka.discovery.model.Credentials;
import com.eureka.discovery.model.Ticket;
import com.eureka.discovery.model.User;
import com.eureka.discovery.model.UserForm1;
import com.eureka.discovery.reposoitory.CredentialRepository;
import com.eureka.discovery.reposoitory.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	// *------------------------ Exception Messages ---------------------*
	public String secret_key = "user-management-system";
	public String bankNotExistException = " !!! There is no Bank Exist from this name Kindly Check the name that are listed here !!!";
	public String invalidContactNumberException = "!!! Invalid Number !!! , Please take care that the number should be length : 10 and all Digits";
	public String invalidEmailException = "!!! Invalid Email Address !!! Please Take of this Format email@mail.com";
	public String userNotExistException = "!!! User is not in database , please first register yourselves or check user_id ";

//	@Autowired
//	private UserService userService;

	@Autowired
	private User user;

	@Autowired
	private Credentials credentials;

	@Autowired
	private EmailServiceImpl emailService;

	@Autowired
	private CredentialRepository credentialRepository;

	@Autowired
	private DataSequenceGeneratorService sequenceGeneratorService;

	@Autowired
	private UserRepository userRepository;

	@Override
	public String getEmailAddress(long id) {
		if (userRepository.existsById(id)) {
			return userRepository.findById(id).get().getEmail_id();
		}
		var userList = userRepository.findAll().parallelStream().filter(p -> p.getAccount_no().equals(id))
				.collect(Collectors.toList());
		if (userList.isEmpty()) {
			return "null";
		}
		return userList.get(0).getEmail_id();
	}

	@Override
	public String addUser(User user) {
		user.setUser_id(sequenceGeneratorService.getUserSequenceNumber("user_sequence"));
		userRepository.save(user);
		return "data Saved";
	}

	@Override
	public String addAllUser(List<User> users) {
		users.forEach(p -> p.setUser_id(sequenceGeneratorService.getUserSequenceNumber("user_sequence")));
		userRepository.saveAll(users);
		return "List of User saved";
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();

	}

//------------------------------------------------Validation-----------------------------------	
	public Boolean isNumeric(String info) {
		try {
			Double.parseDouble(info);
		} catch (NumberFormatException nfe) {
			return false;
		}

		return true;
	}

	public Boolean isEmailValid(String email) {
		String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
//------------------------------------------------Validation-----------------------------------	

	@Override
	public String createUser(UserForm1 userForm1) {
		try {
			if (userForm1.getContact_no().length() != 10 && isNumeric(userForm1.getContact_no())) {
				throw new InvalidContactNumberException(invalidContactNumberException);
			}
			if (!isEmailValid(userForm1.getEmail_address())) {
				throw new InvalidEmailException(invalidEmailException);
			}
			InternetAddress internetAddress = new InternetAddress(userForm1.getEmail_address());
			internetAddress.validate();

		} catch (InvalidContactNumberException | InvalidEmailException | AddressException e) {
			return e.getMessage();
		}
		user.setUser_id(sequenceGeneratorService.getUserSequenceNumber("user-sequence"));
		user.setFull_name(userForm1.getFull_name());
		user.setAge(userForm1.getAge());
		user.setContact_no(userForm1.getContact_no());
		credentials.setUser_id(user.getUser_id());
		credentials.setUsername(
				"user" + Arrays.stream(userForm1.getFull_name().split(" ")).collect(Collectors.toList()).get(0)
						+ new Random().nextInt(10000));
		user.setEmail_id(userForm1.getEmail_address());
		user.setBank_name("no bank addded");
		user.setAccount_no(0L);
		user.setCredit_card("null");
		user.setExpiry_date(LocalDate.now());
		user.setTickets(new HashMap<>());
		String password = RandomStringUtils.randomAlphanumeric(new Random().nextInt(3) + 8);
		Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder();
		credentials.setPassword(encoder.encode(password));

		if (secret_key.equals(userForm1.getSecret_key())) {
			user.setRoles("ADMIN");
		} else {
			user.setRoles("USER");
		}

		credentials.setRoles(user.getRoles());
		userRepository.save(user);
		credentialRepository.save(credentials);
		emailService.sendSimpleEmail(userForm1.getEmail_address(), "Dear " + userForm1.getFull_name()
				+ ",\nThank you for registering on Railway application System\n\nWith Regards\nRailway Developer\nrailway.reservation.system@gmail.com",
				"Welcome to Railway Reservation System");
		emailService.sendSimpleEmail(
				userForm1.getEmail_address(), "User Created Successfully \n\n Your Credentials are here \n Username : "
						+ credentials.getUsername() + " \n Password : " + password,
				"Your user account has been Created");
		return "Your User Account has been created";

	}

	@Override
	public boolean userExistById(Long user_id) {
		return userRepository.existsById(user_id);
	}

	@Override
	public String updateUser(User user) {
		try {
			if (!userRepository.existsById(user.getUser_id())) {
				throw new UserNotExistException(userNotExistException);
			}
			if (!isEmailValid(user.getEmail_id())) {
				throw new InvalidEmailException(invalidEmailException);
			}
			if (user.getAge() < 0) {
				throw new InvalidException("Please Check Your Age");
			}
			if (user.getFull_name().isBlank()) {
				throw new InvalidException("Name Cannot Be Blank");
			}

		} catch (UserNotExistException | InvalidEmailException | InvalidException e) {
			return e.getMessage();
		}
		userRepository.save(user);
		return "User Details Updated for this user id : " + user.getUser_id();
	}

	@Override
	public User getUser(long user_id) {
		if (!userRepository.existsById(user_id)) {
			return new User(0L, "No User Exist", 0, "null", "null", "null", 0L, "null", "null", LocalDate.now(), "null",
					new HashMap<>());
		} else {
			return userRepository.findById(user_id).get();
		}
	}

	@Override
	public String changePassword(ChangePassword changePassword) {
		try {
			if (!userRepository.existsById(changePassword.getUser_id())) {
				throw new UserNotExistException(userNotExistException);
			}
			if (!changePassword.getNew_password().equals(changePassword.getConfirm_password())) {
				throw new InvalidException("Password Mismatched");
			}
		} catch (UserNotExistException | InvalidException e) {
			return e.getMessage();
		}
		Credentials credentials = credentialRepository.findAll().parallelStream()
				.filter(p -> p.getUser_id().equals(changePassword.getUser_id())).collect(Collectors.toList()).get(0);
		credentials.setPassword(changePassword.getNew_password());
		credentials.setRoles(user.getRoles());
		credentialRepository.save(credentials);
		return "Your password Changed Successfully for this user_id : " + changePassword.getUser_id();
	}

	@Override
	public String saveTicket(Long account_no, long pnr, Ticket ticket) {
		try {
			if (userRepository.findAll().parallelStream().noneMatch(p -> p.getAccount_no().equals(account_no))) {
				throw new UserNotExistException(userNotExistException);
			}
			if (pnr < 0) {
				throw new InvalidException("Invalid PNR");
			}
		} catch (UserNotExistException | InvalidException e) {
			return e.getMessage();
		}
		user = userRepository.findAll().parallelStream().filter(p -> p.getAccount_no().equals(account_no))
				.collect(Collectors.toList()).get(0);
		user.getTickets().put(pnr, ticket);
		userRepository.save(user);
		return "Ticket added successfully";
	}

	@Override
	public String getCredentials(long user_id) {
		if (credentialRepository.findAll().parallelStream().noneMatch(p -> p.getUser_id().equals(user_id))) {
			return "Invalid User ID";
		}
		Credentials credentials = credentialRepository.findAll().parallelStream()
				.filter(p -> p.getUser_id().equals(user_id)).collect(Collectors.toList()).get(0);
		emailService.sendSimpleEmail(userRepository.findById(user_id).get().getEmail_id(),
				"Dear " + Arrays.stream(userRepository.findById(user_id).get().getFull_name().split(" "))
						.collect(Collectors.toList()).get(0) + "\n\n Your Credentials are here \n Username : "
						+ credentials.getUsername() + "\n Password : " + credentials.getPassword()
						+ "\n\nWith Regards\nUser Management Service\nuser.development.service@gmail.com",
				"Your credentials");
		return "Your Credential is " + "Username " + credentials.getUsername() + " and Password "
				+ credentials.getPassword();

	}
}
