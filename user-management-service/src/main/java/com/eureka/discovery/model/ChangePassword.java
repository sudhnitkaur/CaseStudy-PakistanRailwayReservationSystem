package com.eureka.discovery.model;

public class ChangePassword {

	private long user_id;
	private String new_password;
	private String confirm_password;

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getNew_password() {
		return new_password;
	}

	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

	public ChangePassword(long user_id, String new_password, String confirm_password) {
		super();
		this.user_id = user_id;
		this.new_password = new_password;
		this.confirm_password = confirm_password;
	}

	public ChangePassword() {
		super();
		// TODO Auto-generated constructor stub
	}

}
