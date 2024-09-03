package com.citadini.ourcity.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
public class OccurrenceSupportEntity implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private OccurrenceSupportPK id = new OccurrenceSupportPK();
	
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm")
	private Date creationDate;
	
	public OccurrenceSupportEntity() {
		
	}

	public OccurrenceSupportEntity(UserEntity userEntity, OccurrenceEntity occurrenceEntity, Date creationDate) {
		super();
		this.id.setUser(userEntity);
		this.id.setOccurrence(occurrenceEntity);
		this.creationDate = creationDate;
	}

	public Long getUsuarioId() {
		return this.id.getUser().getId();
	}
	
	public String getNomeUsuario() {
		return this.id.getUser().getName();
	}
	
	public Long getOcorrenciaId() {
		return this.id.getOccurrence().getId();
	}
	
	public OccurrenceSupportPK getId() {
		return id;
	}

	public void setId(OccurrenceSupportPK id) {
		this.id = id;
	}
	
	@JsonIgnore
	public OccurrenceEntity getOcorrencia() {
		return this.id.getOccurrence();
	}
	
	public void setOcorrencia(OccurrenceEntity ocorrencia) {
		id.setOccurrence(ocorrencia);
	}
	
	@JsonIgnore
	public UserEntity getUsuario() {
		return this.id.getUser();
	}
	
	public void setUsuario(UserEntity usuario) {
		id.setUser(usuario);
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
