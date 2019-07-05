package com.maia.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.maia.project.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	/*
	 * Buscar pelo nome com Padrão do SpringJPA - com Padrão de mostra os 10
	 * Primeiros Registros
	 */
	public List<Category> findFirst10ByNameContaining(String name);

	//find by name and user
	@Query("select c from Category c where lower(c.name) like concat('%',lower(?1),'%') and c.usuario.id = ?2")
	List<Category> findByName(String name, Long idUsuario);

	// find by user id
	@Query("SELECT c FROM Category c WHERE c.usuario.id = ?1 ORDER BY c.description")
	public List<Category> findAll(Long idUsuario);

}
