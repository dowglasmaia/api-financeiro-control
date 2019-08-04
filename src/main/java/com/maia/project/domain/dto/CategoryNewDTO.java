package com.maia.project.domain.dto;

import com.maia.project.domain.Category;
import com.maia.project.domain.Usuario;

public class CategoryNewDTO {

	private Long id;

	private String name;

	private String description;

	private Usuario usuario;

	public CategoryNewDTO() {

	}

	public CategoryNewDTO(Category obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.description = obj.getDescription();
		this.usuario = obj.getUsuario();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
