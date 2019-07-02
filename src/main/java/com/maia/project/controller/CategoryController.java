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

import com.maia.project.domain.Category;
import com.maia.project.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;

	// save
	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody Category obj) {
		obj = service.save(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}

	// update
	@PutMapping("/{id}")
	public ResponseEntity<Category> update(@PathVariable(name = "id") Long id, @RequestBody Category obj) {
		obj.setId(id);
		Category updateObj = service.update(obj);
		return ResponseEntity.ok(updateObj);
	}

	// findById
	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	// findAll
	@GetMapping
	public ResponseEntity<List<Category>> listAll() {
		List<Category> categories = service.findAll();
		return ResponseEntity.ok().body(categories);

	}

	// find list by Name
	@GetMapping("/lista")
	public ResponseEntity<List<Category>> listByName(@RequestParam(value = "name") String name) {
		List<Category> categories = service.listByName(name);
		return ResponseEntity.ok().body(categories);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
