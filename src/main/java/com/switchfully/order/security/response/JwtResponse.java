package com.switchfully.order.security.response;

import lombok.Data;

@Data
public class JwtResponse {
	private String token;
	private String id;
	private String username;

	public JwtResponse(String token, String id, String email) {
		this.token = token;
		this.id = id;
		this.username = email;
	}
}
