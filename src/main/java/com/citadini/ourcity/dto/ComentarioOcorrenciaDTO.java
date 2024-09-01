package com.citadini.ourcity.dto;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public class ComentarioOcorrenciaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long ocorrenciaId;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min=5, max=256, message="O tamanho deve ser entre 5 e 256 caracteres")
	private String descricao;
	
	public ComentarioOcorrenciaDTO() {
	}

	public Long getOcorrenciaId() {
		return ocorrenciaId;
	}

	public void setOcorrenciaId(Long ocorrenciaId) {
		this.ocorrenciaId = ocorrenciaId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
