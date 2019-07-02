package com.maia.project.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
@Audited
@AuditTable(value = "entry_audit")
public class Entry implements Serializable {
	private static final long serialVersionUID = 7370646139519352990L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 80, nullable = false)
	@NotEmpty(message = " Campo name é obrigatório")
	private String name;

	@Column(length = 80, nullable = false)
	private String description;

	@Column(length = 20, nullable = false)
	@NotEmpty(message = " Campo type é obrigatório")
	private String type;

	@Column(length = 80, nullable = false, columnDefinition = "DECIMAL(18,2) DEFAULT 0.00")
	@NotNull(message = " Campo valor é obrigatório")
	@NumberFormat(style = Style.CURRENCY)
	@JsonFormat(pattern = "#,##0.00")
	private BigDecimal amount;

	@Column(length = 80, nullable = false)
	@NotNull(message = " Campo data é obrigatório")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;

	private boolean paid;

	@ManyToOne
	private Category category;

	public Entry() {

	}

	@JsonCreator
	public Entry(Long id, String name, String description, String type, BigDecimal amount, LocalDate date, boolean paid,
			Category category) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.paid = paid;
		this.category = category;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
		Entry other = (Entry) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
