package com.maia.project.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
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
		
		setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
		
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
	
	/* == Correção de Erro de Resposta de Error 403 para 401 == */
	@SuppressWarnings("unused")
	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {
			response.setStatus(401);
			response.setContentType("application/json");
			response.getWriter().append(json());
		}

		private String json() {
			long date = new Date().getTime();
			return "{\"timestamp\": " + date + ", " + "\"status\": 401, " + "\"error\": \"Não autorizado\", "
					+ "\"message\": \"Email e/ou senha inválidos\", " + "\"path\": \"/login\"}";
		}
	}

}