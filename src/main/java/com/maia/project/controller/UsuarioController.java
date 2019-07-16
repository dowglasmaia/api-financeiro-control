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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.maia.project.domain.Usuario;
import com.maia.project.services.UsuarioService;

@RestController
@RequestMapping(value = "/users")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	// save
	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody Usuario obj) {
		obj = service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// update
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> update(@PathVariable(name = "id") Long id, @RequestBody Usuario obj) {
		
		obj.setId(id);
		
		Usuario updateObj = service.update(obj);
		return ResponseEntity.ok(updateObj);
	}

	// findById
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
		Usuario obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	// findAll
	@GetMapping
	public ResponseEntity<List<Usuario>> listAll() {
		List<Usuario> result = service.findAll();
		return ResponseEntity.ok().body(result);

	}

	// find
	@GetMapping("/email")
	public ResponseEntity<Usuario> findByEmail(@RequestParam(name = "email") String email) {
		Usuario user = service.findByEmaill(email);
		return ResponseEntity.ok().body(user);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
