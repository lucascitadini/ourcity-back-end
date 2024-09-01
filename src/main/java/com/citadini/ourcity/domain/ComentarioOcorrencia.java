package com.citadini.ourcity.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class ComentarioOcorrencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm")
	private Date instante;

	@ManyToOne
	@JoinColumn(name="usuario_id")
	@JsonIgnore
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="ocorrencia_id")
	@JsonIgnore
	private Ocorrencia ocorrencia;
	
	private String descricao;
	
	public ComentarioOcorrencia() {
	}
	
	public ComentarioOcorrencia(Long id, Usuario usuario, Ocorrencia ocorrencia, String descricao, Date instante) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.ocorrencia = ocorrencia;
		this.descricao = descricao;
		this.instante = instante;
	}
	
	public Long getIdUsuario() {
		return this.usuario.getId();
	}
	
	public String getNomeUsuario() {
		return this.usuario.getNome();
	}
	
	public Long getIdOcorrencia() {
		return this.ocorrencia.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
