package com.maia.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maia.project.domain.Category;
import com.maia.project.domain.Entry;
import com.maia.project.domain.Usuario;
import com.maia.project.domain.dto.EntryDTO;
import com.maia.project.repository.EntryRespository;

@Service
public class EntryService {

	@Autowired
	private EntryRespository repoistory;

	// save
	public Entry save(Entry obj) {
		obj.setId(null);

		try {
			return repoistory.save(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(" Operação Falhou!");
		}
	}

	public Entry fromDTO(EntryDTO objDTO) {
		Usuario user = objDTO.getUsuario();

		Category category = objDTO.getCategory();

		Entry entry = new Entry(null, objDTO.getName(), objDTO.getDescription(), objDTO.getType(), objDTO.getAmount(),
				objDTO.getDate(), objDTO.isPaid(), category, user);
		return entry;
	}

	// update
	public Entry update(Entry obj) {
		try {
			return repoistory.save(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(" Operação Falhou!");
		}
	}

	// find for id
	public Entry findById(Long id) {
		Optional<Entry> obj = repoistory.findById(id);
		return obj.orElseThrow(
				() -> new RuntimeException("Entry Não Encontrado! - id: " + id + " - " + Entry.class.getName()));

	}

	// list All
	public List<Entry> findAll(Long idUsuario) {
		List<Entry> entries = repoistory.findAll(idUsuario);
		return entries;
	}
	
	// list All
		public List<Entry> findAll() {
			List<Entry> entries = repoistory.findAll();
			return entries;
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
