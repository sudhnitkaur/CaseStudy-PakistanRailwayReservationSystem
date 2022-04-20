package com.eureka.discovery.configuration;

import java.io.Serializable;

public class AuthenticateResponse implements Serializable {

	private final String jwt;

	public String getJwt() {
		return jwt;
	}

	public AuthenticateResponse(String jwt) {
		super();
		this.jwt = jwt;
	}
	
	
	
}
