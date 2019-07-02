package com.maia.project.config;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maia.project.domain.dto.UserLoginDTO;

/*
 * filtro de autenticação  - de Login
 * */

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	protected AuthenticationManager authenticationManager;

	protected JWTUtil jwtUtil;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	/* tenta autencicar */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {

		try {
			UserLoginDTO creds = new ObjectMapper().readValue(req.getInputStream(), UserLoginDTO.class);

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(),
					creds.getPassword(), new ArrayList<>());

			Authentication auth = authenticationManager.authenticate(authToken); // verifica se o usuario e senha

			return auth;
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/* se autenticação for bem sucedido é chamado. */
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String email = ((UserSS) auth.getPrincipal()).getUsername();
		String token = jwtUtil.generationToken(email);
		res.addHeader("Authorization", "Bearer " + token);

		res.addHeader("access-control-expose-headers", "Authorization"); // libera a Leitura do cabeçalho personalizado
																			// Authorization por CORS

	}

}