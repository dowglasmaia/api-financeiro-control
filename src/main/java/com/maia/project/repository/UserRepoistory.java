package com.maia.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.maia.project.domain.User;

@Repository
public interface UserRepoistory extends JpaRepository<User, Long> {

	/* Buscar por email */
	public User findByEmail(String email);

	/* Buscar por email e senha = neste caso para fazer o login */
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public Optional<User> login(String email);

}
