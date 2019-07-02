package com.maia.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maia.project.domain.User;
import com.maia.project.repository.UserRepoistory;
import com.maia.project.services.util.HashUtil;

@Service
public class UserService {

	@Autowired
	private UserRepoistory repoistory;

	// save
	public User save(User obj) {
		obj.setId(null);
		try {
			String hash = HashUtil.getSecurityHash(obj.getPassword());
			obj.setPassword(hash);
			return repoistory.save(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(" Operação Falhou!");

		}
	}

	// update
	public User update(User obj) {
		try {
			String hash = HashUtil.getSecurityHash(obj.getPassword());
			obj.setPassword(hash);
			return repoistory.save(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(" Operação Falhou!");

		}
	}

	// find for id
	public User findById(Long id) {
		Optional<User> obj = repoistory.findById(id);
		return obj.get();
	}

	// list All
	public List<User> findAll() {
		List<User> users = repoistory.findAll();
		return users;
	}

	/* Login
	public User login(String email, String password) {
		password = HashUtil.getSecurityHash(password);
		Optional<User> user = repoistory.login(email, password);
		return user.orElseThrow(
				() -> new RuntimeException("Usuário Não Encontrado! e-Mail: " + email + " - " + User.class.getName()));

	}
*/
}
