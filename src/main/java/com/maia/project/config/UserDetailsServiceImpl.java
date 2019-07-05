package com.maia.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.maia.project.domain.Usuario;
import com.maia.project.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepo;

	/* buscarndo usuario pelo email, e autenticando o mesmo */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Usuario user = (Usuario) userRepo.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Usuário com e-mail :" + email + "- não encontrado!");
		}
		return new UserSS(user.getId(), user.getEmail(), user.getSenha(), user.getPerfis());
	}

}
