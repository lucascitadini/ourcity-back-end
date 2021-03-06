package com.citadini.ourcity.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class OcorrenciaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Double latitude;
	
	private Double longitude;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min=2, max=248, message="O tamanho deve ser entre 2 e 248 caracteres")
	private String enderecoCompleto;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min=5, max=516, message="O tamanho deve ser entre 5 e 516 caracteres")
	private String descricao;
	
	private Integer categoriaId;
	
	private Integer statusId;
	
	public OcorrenciaDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}

	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
}