package com.citadini.ourcity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
public class UserConfigurationEntity implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String configName;
	
	private String valueParam;
	
	private String description;
	
	@JsonIgnore
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	public UserConfigurationEntity() {
	}

	public UserConfigurationEntity(Long id, String configName, String valueParam, String description, UserEntity userEntity) {
		super();
		this.id = id;
		this.configName = configName;
		this.valueParam = valueParam;
		this.description = description;
		this.user = userEntity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getValueParam() {
		return valueParam;
	}

	public void setValueParam(String valueParam) {
		this.valueParam = valueParam;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
