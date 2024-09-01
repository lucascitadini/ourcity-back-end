package com.citadini.ourcity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class ConfiguracaoUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nomeConfig;
	
	private String valueParam;
	
	private String descricao;
	
	@JsonIgnore
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	public ConfiguracaoUsuario() {
	}

	public ConfiguracaoUsuario(Long id, String nomeConfig, String valueParam, String descricao, Usuario usuario) {
		super();
		this.id = id;
		this.nomeConfig = nomeConfig;
		this.valueParam = valueParam;
		this.descricao = descricao;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeConfig() {
		return nomeConfig;
	}

	public void setNomeConfig(String nomeConfig) {
		this.nomeConfig = nomeConfig;
	}

	public String getValueParam() {
		return valueParam;
	}

	public void setValueParam(String valueParam) {
		this.valueParam = valueParam;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
