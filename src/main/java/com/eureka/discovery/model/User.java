package com.eureka.discovery.model;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;


@Document(collection = "User")
@Component
public class User {

	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";

	@Id
	private long user_id;
	private String full_name;
	private int age;
	private String contact_no;
	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}

	private String email_id;
	private String bank_name;
	private Long account_no;
	private String credit_card;
	private String cvv;
	private LocalDate expiry_date;
	private String roles;
	private Map<Long, Ticket> tickets;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



	public User(long user_id, String full_name, int age, String contact_no, String email_id, String bank_name,
			Long account_no, String credit_card, String cvv, LocalDate expiry_date, String roles,
			Map<Long, Ticket> tickets) {
		super();
		this.user_id = user_id;
		this.full_name = full_name;
		this.age = age;
		this.contact_no = contact_no;
		this.email_id = email_id;
		this.bank_name = bank_name;
		this.account_no = account_no;
		this.credit_card = credit_card;
		this.cvv = cvv;
		this.expiry_date = expiry_date;
		this.roles = roles;
		this.tickets = tickets;
	}



	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public Long getAccount_no() {
		return account_no;
	}

	public void setAccount_no(Long account_no) {
		this.account_no = account_no;
	}

	public String getCredit_card() {
		return credit_card;
	}

	public void setCredit_card(String credit_card) {
		this.credit_card = credit_card;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public LocalDate getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(LocalDate expiry_date) {
		this.expiry_date = expiry_date;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Map<Long, Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Map<Long, Ticket> tickets) {
		this.tickets = tickets;
	}

}
