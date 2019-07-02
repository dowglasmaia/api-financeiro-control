package com.maia.project.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.expiration}")
	private String experation;

	@Value("${jwt.secret}")
	private String secret;

	public String generationToken(String email) {
		return Jwts.builder().setSubject(email) // usuario
				.setExpiration(new Date(System.currentTimeMillis() * 10)) // tempo de expiranção
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()) // algoritimo de Cryptografia
				.compact();
	}

	/* verificando se o tokem é valido */
	public boolean tokenValido(String token) {
		/* claims - armazena as os Dados do token */
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date experaouDate = claims.getExpiration();
			Date agora = new Date(System.currentTimeMillis());
			if (username != null && experaouDate != null && agora.before(experaouDate)) {
				return true;
			}
		}
		return false;
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// pegando apartir do token
	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}

}
