package com.maia.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maia.project.domain.Category;
import com.maia.project.repository.CategoryRepository;
import com.maia.project.services.exception.ErrorHttp400;

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

	// find list by name - com base em seu Usuario
	public List<Category> listByName(String name, Long idUsuario) {
		try {
			List<Category> obj = repoistory.findByName(name, idUsuario);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(" Operação Falhou!");
		}
	}

	// list All - com base em seu Usuario
	public List<Category> findAll(Long idUsuario) {
		try {
			List<Category> categories = repoistory.findAll(idUsuario);
			return categories;
		} catch (ErrorHttp400 e) {
			e.printStackTrace();
			throw new ErrorHttp400("Operação não realizada, verifique os parametros da pesquisa!");
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
