package com.eureka.discovery.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.eureka.discovery.model.ChangePassword;
import com.eureka.discovery.model.Ticket;
import com.eureka.discovery.model.User;
import com.eureka.discovery.model.UserForm1;

@Service
public interface UserService {
	
	public String getEmailAddress(long id);
	
    public String addUser(User user);

    public String addAllUser(List<User> users);

    public List<User> getAllUser();

    public String createUser(UserForm1 userForm1);

    public boolean userExistById(Long user_id);

    public String updateUser(User user);

    public User getUser(long user_id);

    public String changePassword(ChangePassword changePassword);

    public String saveTicket(Long account_no, long pnr, Ticket ticket);

    public String getCredentials(long user_id);

}
