package com.citadini.ourcity.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String userName;
	
	private String nome;
	
	@Column(unique=true)
	private String email;
	
	@JsonIgnore
	private String senha;
	
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="usuario")
	private List<Ocorrencia> ocorrencias = new ArrayList<>();
	
	@OneToMany(mappedBy="usuario")
	private List<ConfiguracaoUsuario> configuracoes = new ArrayList<>();
	
	@OneToMany(mappedBy="usuario")
	private List<DispositivoUsuario> dispositivos = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="usuario")
	private List<ComentarioOcorrencia> comentarios = new ArrayList<>();

	public Usuario() {
		
	}
	
	public Usuario(Long id, String nome, String email, String senha, String userName) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.userName = userName;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public List<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(List<Ocorrencia> ocorrencia) {
		this.ocorrencias = ocorrencia;
	}

	public List<ConfiguracaoUsuario> getConfiguracoes() {
		return configuracoes;
	}

	public void setConfiguracoes(List<ConfiguracaoUsuario> configuracoes) {
		this.configuracoes = configuracoes;
	}

	public List<DispositivoUsuario> getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(List<DispositivoUsuario> dispositivos) {
		this.dispositivos = dispositivos;
	}

	public List<ComentarioOcorrencia> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentarioOcorrencia> comentarios) {
		this.comentarios = comentarios;
	}
}
