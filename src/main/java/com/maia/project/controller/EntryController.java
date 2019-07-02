package com.maia.project.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.maia.project.domain.Entry;
import com.maia.project.domain.dto.EntryDTO;
import com.maia.project.services.EntryService;

@RestController
@RequestMapping(value = "/entries")
public class EntryController {

	@Autowired
	private EntryService service;

	// save
	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody EntryDTO objDTO) {
		Entry obj = service.fromDTO(objDTO);
		service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}

	// update
	@PutMapping("/{id}")
	public ResponseEntity<Entry> update(@PathVariable(name = "id") Long id, @RequestBody EntryDTO objDTO) {
		Entry obj = service.fromDTO(objDTO);
		obj.setId(id);
		Entry updateObj = service.update(obj);
		return ResponseEntity.ok(updateObj);
	}

	// findById
	@GetMapping("/{id}")
	public ResponseEntity<Entry> findById(@PathVariable Long id) {
		Entry obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	// findAll
	@GetMapping
	public ResponseEntity<List<Entry>> listAll() {
		List<Entry> entries = service.findAll();
		return ResponseEntity.ok().body(entries);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
