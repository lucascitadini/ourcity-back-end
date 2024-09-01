package com.citadini.ourcity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class DispositivoUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String numeroDispositivo;
	
	private String numeroRegistroPush;
	
	private String descricaoPush;
	
	@JsonIgnore
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	public DispositivoUsuario() {
		
	}
	
	public DispositivoUsuario(Long id, String nome, String numeroDispositivo, String numeroRegistroPush,
			String descricaoPush, Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.numeroDispositivo = numeroDispositivo;
		this.numeroRegistroPush = numeroRegistroPush;
		this.descricaoPush = descricaoPush;
		this.usuario = usuario;
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

	public String getNumeroDispositivo() {
		return numeroDispositivo;
	}

	public void setNumeroDispositivo(String numeroDispositivo) {
		this.numeroDispositivo = numeroDispositivo;
	}

	public String getNumeroRegistroPush() {
		return numeroRegistroPush;
	}

	public void setNumeroRegistroPush(String numeroRegistroPush) {
		this.numeroRegistroPush = numeroRegistroPush;
	}

	public String getDescricaoPush() {
		return descricaoPush;
	}

	public void setDescricaoPush(String descricaoPush) {
		this.descricaoPush = descricaoPush;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
