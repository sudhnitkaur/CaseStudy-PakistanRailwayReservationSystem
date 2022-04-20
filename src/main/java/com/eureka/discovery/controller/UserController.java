package com.eureka.discovery.controller;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.discovery.configuration.AuthenticateRequest;
import com.eureka.discovery.configuration.AuthenticateResponse;
import com.eureka.discovery.configuration.JwtUtil;
import com.eureka.discovery.model.ChangePassword;
import com.eureka.discovery.model.Ticket;
import com.eureka.discovery.model.User;
import com.eureka.discovery.model.UserForm1;
import com.eureka.discovery.reposoitory.CredentialRepository;
import com.eureka.discovery.service.CustomUserDetailService;
import com.eureka.discovery.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private CredentialRepository credentialRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailService custService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	private String token = "";
	
	
	
	@PostMapping("/auth")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticateRequest request){
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			System.out.println("wrong credential");
			return ResponseEntity.of(Optional.of("wrong username and password"));
		}
		final UserDetails userDetail = custService.loadUserByUsername(request.getUsername());
		final String jwt = jwtUtil.generateToken(userDetail);
		token = jwt;
		return ResponseEntity.ok(new AuthenticateResponse(jwt));
		
	}  
	
	
	
	@GetMapping("/admin/welcome")
	public String greeting() {
		return "Welcome to User Interface";
	}
	
	@PostMapping("/admin/addUser")
	public String addUser(@RequestBody User user) {
		return userServiceImpl.addUser(user);
		
	}
	
	@PostMapping("/admin/addAllUser")
	public String addAllUser(@RequestBody List<User> users) {
		return userServiceImpl.addAllUser(users);
	}
	
	

	@PostMapping("/public/createUser")
	public String createUser(@RequestBody UserForm1 UserForm1) {
		return userServiceImpl.createUser(UserForm1);
	}
	
	@GetMapping("/public/getUser{user_id}")
	public User getUser(@PathVariable("user_id") Long user_id) {
		return userServiceImpl.getUser(user_id);
	}
	
	@GetMapping("/admin/getAllUser")
	public List<User> getAllUser(){
		return userServiceImpl.getAllUser();
	}
	
	@GetMapping("/public/userExistById/{id}")
	public Boolean userExistById(@PathVariable("id") long id) {
		return userServiceImpl.userExistById(id);
	}
	
	@PostMapping("/public/updateUser")
	public String updateUser(@RequestBody User user) {
		return userServiceImpl.updateUser(user);
		
	}
	
	@GetMapping("/public/getCredential/{id}")
	public String getCredential(@PathVariable("id") long id) {
		return userServiceImpl.getCredentials(id);
	}
	
	@PostMapping("/public/changePassword")
	public String changePasswprd(@RequestBody ChangePassword changePassword) {
		return userServiceImpl.changePassword(changePassword);
	}
	
	@PostMapping("/public/saveTicket/{account_no}:{pnr}")
	public String saveTicket(@PathVariable("account_no") long account_no , @PathVariable("pnr") long pnr , @RequestBody Ticket ticket) {
		return userServiceImpl.saveTicket(account_no, pnr, ticket);
	}
	

}
