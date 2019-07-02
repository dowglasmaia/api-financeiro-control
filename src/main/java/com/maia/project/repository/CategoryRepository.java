package com.maia.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maia.project.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	/* Buscar pelo nome com Padrão do SpringJPA - com Padrão de mostra os 10 Primeiros Registros*/
	public List<Category>findFirst10ByNameContaining(String name);
	
	

}
