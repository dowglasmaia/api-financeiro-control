package com.maia.project.config;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.maia.project.domain.Perfil;

/**
 * @author Dowglas Maia
 * 
 */

public class UserSS implements UserDetails {
	private static final long serialVersionUID = -4149492238566544986L;

	/* credencias para login */
	private Long id;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UserSS() {

	}

	public UserSS(Long id, String email, String password, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;

		// convertendo a lista de perfis para uma Collection de authorities -
		// pegando os pefis para o Sprg Security
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao()))
				.collect(Collectors.toSet());
	}

	public Long getId() {
		return id;
	}

	/* Autorizações */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/* Senha */
	@Override
	public String getPassword() {
		return password;
	}

	/* username */
	@Override
	public String getUsername() {
		return email;
	}

	/* Validação de Conta, Expirado ou não. */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/* Validação de Conta, Se estar Bloqueada */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/* Valida se asCredencias estão espiradas. */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/* Valida se o usuario estar ativo. */
	@Override
	public boolean isEnabled() {

		return true;
	}

	// Testando o Perfil do Usuario
	public boolean hasRole(Perfil perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}

}
