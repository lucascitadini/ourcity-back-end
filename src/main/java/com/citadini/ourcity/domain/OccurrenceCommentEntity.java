package com.citadini.ourcity.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
public class OccurrenceCommentEntity implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm")
	private Date creationTime;

	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name="occurrence_id")
	@JsonIgnore
	private OccurrenceEntity occurrence;
	
	private String description;
	
	public OccurrenceCommentEntity() {
	}
	
	public OccurrenceCommentEntity(Long id, UserEntity userEntity, OccurrenceEntity occurrenceEntity, String description, Date creationTime) {
		super();
		this.id = id;
		this.user = userEntity;
		this.occurrence = occurrenceEntity;
		this.description = description;
		this.creationTime = creationTime;
	}
	
	public Long getUserId() {
		return this.user.getId();
	}
	
	public String getUsername() {
		return this.user.getName();
	}
	
	public Long getOccurrenceId() {
		return this.occurrence.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public OccurrenceEntity getOccurrence() {
		return occurrence;
	}

	public void setOccurrence(OccurrenceEntity occurrence) {
		this.occurrence = occurrence;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
