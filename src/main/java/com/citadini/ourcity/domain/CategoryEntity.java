package com.citadini.ourcity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CategoryEntity implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy="category")
	private List<OccurrenceEntity> occurrences = new ArrayList<OccurrenceEntity>();
	
	public CategoryEntity() {
	}
	
	public CategoryEntity(String nome) {
		this.name = nome;
	}
	
	public CategoryEntity(Integer id, String nome) {
		this.id = id;
		this.name = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		CategoryEntity other = (CategoryEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public List<OccurrenceEntity> getOccurrences() {
		return occurrences;
	}

	public void setOccurrences(List<OccurrenceEntity> occurrences) {
		this.occurrences = occurrences;
	}
}
