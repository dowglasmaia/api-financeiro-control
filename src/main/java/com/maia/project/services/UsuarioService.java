package com.maia.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.maia.project.config.UserSS;
import com.maia.project.domain.Perfil;
import com.maia.project.domain.Usuario;
import com.maia.project.repository.UsuarioRepository;
import com.maia.project.services.exception.AuthorizationException;
import com.maia.project.services.util.UserService;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repoistory;

	@Autowired
	private BCryptPasswordEncoder encoder;

	// save
	public Usuario save(Usuario obj) {
		obj.setId(null);
		try {
			obj.setSenha(encoder.encode(obj.getSenha()));
			return repoistory.save(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(" Operação Falhou!");
		}
	}

	// update
	public Usuario update(Usuario obj) {
		try {
			obj.setSenha(encoder.encode(obj.getSenha()));
			return repoistory.save(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(" Operação Falhou!");
		}
	}

	// find for id
	public Usuario findById(Long id) {
		UserSS user = UserService.authenticated(); // pegando Usuario Logado

		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado");
		}

		Optional<Usuario> obj = repoistory.findById(id);
		return obj.orElseThrow(
				() -> new AuthorizationException("Usuario Não Encontrado! - id: " + id + " - " + Usuario.class.getName()));

	}

	// list All
	public List<Usuario> findAll() {
		UserSS user = UserService.authenticated(); // pegando Usuario Logado

		if (user != null && user.hasRole(Perfil.ADMIN)) {
			List<Usuario> result = repoistory.findAll();
			return result;
		} else  {
			throw new AuthorizationException(" Acesso Negado");
		}
	}

	// delete
	public void delete(Long id) {
		findById(id);
		try {
			repoistory.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(" Operação Falhou!");
		}
	}

}
