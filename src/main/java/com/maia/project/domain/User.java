package com.maia.project.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
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
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
@Audited
@AuditTable(value = "user_audit")
public class User implements Serializable {
	private static final long serialVersionUID = 4131782709653831971L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Campo nome é obrigatório")
	private String name;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@NotNull
	private LocalDateTime cadastro;

	@Column(length = 80, nullable = false)
	@NotEmpty(message = "Campo e-mail é obrigatório")
	@Email
	private String email;

	@Column(nullable = false)
	@NotEmpty(message = "Campo password é obrigatório")
	private String password;

	@JsonIgnore
	@ElementCollection(fetch = FetchType.EAGER) // garante que quando buscar o usuario no BD, Carregue tbm seus Perfis.
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();

	public User() {
		addPerfil(Perfil.USUARIO);
	}

	public User(Long id, String name, LocalDateTime cadastro, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.cadastro = cadastro;
		this.email = email;
		this.password = password;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCadastro() {
		return cadastro;
	}

	public void setCadastro(LocalDateTime cadastro) {
		this.cadastro = cadastro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}