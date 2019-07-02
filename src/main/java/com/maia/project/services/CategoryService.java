package com.maia.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maia.project.domain.Category;
import com.maia.project.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repoistory;

	// save
	public Category save(Category obj) {
		obj.setId(null);
		try {
			return repoistory.save(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(" Operação Falhou!");
		}
	}

	// update
	public Category update(Category obj) {
		try {
			return repoistory.save(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(" Operação Falhou!");
		}
	}

	// find for id
	public Category findById(Long id) {
		Optional<Category> obj = repoistory.findById(id);
		return obj.orElseThrow(
				() -> new RuntimeException("Category Não Encontrado! - id: " + id + " - " + Category.class.getName()));

	}

	// find list by name
	public List<Category> listByName(String name) {
		try {
			List<Category> obj = repoistory.findFirst10ByNameContaining(name);			
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(" Operação Falhou!");
		}
	}

	// list All
	public List<Category> findAll() {
		try {
			List<Category> categories = repoistory.findAll();
			return categories;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(" Operação Falhou!");
		}
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
