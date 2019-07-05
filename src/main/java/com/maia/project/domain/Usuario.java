package com.maia.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table
@Audited
@AuditTable(value = "usuario_audit")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 80, nullable = false)
	@NotEmpty(message = "Campo Nome é Obrigatórido")
	private String nome;

	@Column(length = 80, nullable = false)
	@NotEmpty(message = "Campo e-Mail Obrigatórido")
	@Email(message = "e-Mail Ínvalido")
	private String email;

	private String senha;

	@Transient
	private String token;

	@JsonIgnore
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();

	@OneToMany(mappedBy = "usuario")
	private List<Entry> entries = new ArrayList<>();

	@OneToMany(mappedBy = "usuario")
	private List<Category> categories = new ArrayList<>();

	public Usuario() {
		addPerfil(Perfil.USUARIO); // Definindo Perfil Padrão para Todos os usuario Cadastrados

	}

	public Usuario(Long id, @NotEmpty(message = "Campo Nome é Obrigatórido") String nome,
			@NotEmpty(message = "Campo e-Mail Obrigatórido") @Email(message = "e-Mail Ínvalido") String email,
			String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;

		addPerfil(Perfil.USUARIO);

	}

	// Retorna os Perfis dos Clientes do Enum Perfil
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	/* Add Perfil */
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getSenha() {
		return senha;
	}

	@JsonProperty
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public List<Category> getCategories() {
		return categories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
