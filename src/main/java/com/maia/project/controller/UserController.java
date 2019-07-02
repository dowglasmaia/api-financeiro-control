package com.maia.project.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.maia.project.domain.User;
import com.maia.project.domain.dto.UserLoginDTO;
import com.maia.project.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService service;

	// save
	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody User obj) {
		obj = service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}

	// update
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name = "id") Long id, @RequestBody User obj) {
		obj.setId(id);
		User updateObj = service.update(obj);
		return ResponseEntity.ok(updateObj);
	}

	// findById
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	// findAll
	@GetMapping
	public ResponseEntity<List<User>> listAll() {
		List<User> users = service.findAll();
		return ResponseEntity.ok().body(users);

	}

	// login
	/*
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLoginDTO userDTO) {
		User loggedUser = service.login(userDTO.getEmail(), userDTO.getPassword());
		return ResponseEntity.ok(loggedUser);

	}
*/
}
