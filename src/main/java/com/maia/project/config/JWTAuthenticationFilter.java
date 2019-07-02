package com.maia.project.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationToken {

	public JWTAuthenticationFilter(Object principal, Object credentials) {
		super(principal, credentials);
		
	}

}
