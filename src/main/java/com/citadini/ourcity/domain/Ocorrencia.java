package com.citadini.ourcity.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ocorrencia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date dataCriacao;
	private Double latitude;
	private Double longitude;
	private String enderecoCompleto;
	private String descricao;
	@ManyToOne
	@JoinColumn(name="usuario_criacao_id")
	@JsonIgnore
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="status_id")
	private Status status;
	
	@OneToMany(mappedBy="ocorrencia")
	@JsonIgnore
	private List<ComentarioOcorrencia> comentarios = new ArrayList<ComentarioOcorrencia>();
	
	@OneToMany(mappedBy="id.ocorrencia")
	@JsonIgnore
	private List<ApoioOcorrencia> apoios = new ArrayList<ApoioOcorrencia>();
	
	public Ocorrencia() {
	}
	
	public Ocorrencia(Long id, Date dataCriacao, Double latitude, Double longitude, String enderecoCompleto,
			String descricao, Usuario usuario, Categoria categoria, Status status) {
		super();
		this.id = id;
		this.dataCriacao = dataCriacao;
		this.latitude = latitude;
		this.longitude = longitude;
		this.enderecoCompleto = enderecoCompleto;
		this.descricao = descricao;
		this.usuario = usuario;
		this.categoria = categoria;
		this.status = status;
	}
	
	public Integer getTotalApoios() {
		return this.apoios.size();
	}
	
	public Integer getTotalComentarios() {
		return this.comentarios.size();
	}

	public Long getIdUsuario() {
		return this.usuario.getId();
	}
	
	public String getNomeUsuario() {
		return this.usuario.getNome();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
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
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<ComentarioOcorrencia> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentarioOcorrencia> comentarios) {
		this.comentarios = comentarios;
	}

	public List<ApoioOcorrencia> getApoios() {
		return apoios;
	}

	public void setApoios(List<ApoioOcorrencia> apoios) {
		this.apoios = apoios;
	}
}