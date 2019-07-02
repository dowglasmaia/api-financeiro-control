package com.maia.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.maia.project.domain.Entry;

@Repository
public interface EntryRespository extends JpaRepository<Entry, Long> {
	/* Buscar por email */
	public Entry findByName(String email);


}
