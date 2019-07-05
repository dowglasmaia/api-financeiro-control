package com.maia.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.maia.project.domain.Usuario;
import com.maia.project.repository.UsuarioRepository;

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
		Optional<Usuario> obj = repoistory.findById(id);
		return obj.orElseThrow(
				() -> new RuntimeException("Usuario Não Encontrado! - id: " + id + " - " + Usuario.class.getName()));

	}

	// list All
	public List<Usuario> findAll() {
		try {
			List<Usuario> categories = repoistory.findAll();
			return categories;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(" Operação Falhou!");
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
