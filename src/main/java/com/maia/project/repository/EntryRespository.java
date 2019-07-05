package com.maia.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.maia.project.domain.Entry;

@Repository
public interface EntryRespository extends JpaRepository<Entry, Long> {

	/* Buscar por nome */
	public Entry findByName(String email);
	
	@Query("SELECT e FROM Entry e WHERE e.usuario.id = ?1 ORDER BY e.description")
	public List<Entry>findAll(Long idUsuario);
	
	


}
