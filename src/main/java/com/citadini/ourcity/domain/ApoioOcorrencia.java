package com.citadini.ourcity.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ApoioOcorrencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private ApoioOcorrenciaPK id = new ApoioOcorrenciaPK();
	
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm")
	private Date instante;
	
	public ApoioOcorrencia() {
		
	}

	public ApoioOcorrencia(Usuario usuario, Ocorrencia ocorrencia, Date instante) {
		super();
		this.id.setUsuario(usuario);
		this.id.setOcorrencia(ocorrencia);
		this.instante = instante;
	}

	public Long getUsuarioId() {
		return this.id.getUsuario().getId();
	}
	
	public String getNomeUsuario() {
		return this.id.getUsuario().getNome();
	}
	
	public Long getOcorrenciaId() {
		return this.id.getOcorrencia().getId();
	}
	
	public ApoioOcorrenciaPK getId() {
		return id;
	}

	public void setId(ApoioOcorrenciaPK id) {
		this.id = id;
	}
	
	@JsonIgnore
	public Ocorrencia getOcorrencia() {
		return this.id.getOcorrencia();
	}
	
	public void setOcorrencia(Ocorrencia ocorrencia) {
		id.setOcorrencia(ocorrencia);
	}
	
	@JsonIgnore
	public Usuario getUsuario() {
		return this.id.getUsuario();
	}
	
	public void setUsuario(Usuario usuario) {
		id.setUsuario(usuario);
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}
}
