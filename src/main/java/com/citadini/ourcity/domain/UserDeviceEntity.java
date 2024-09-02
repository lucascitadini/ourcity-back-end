package com.citadini.ourcity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
public class UserDeviceEntity implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String deviceNumber;
	
	private String pushRecordNumber;
	
	private String pushDescription;
	
	@JsonIgnore
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	public UserDeviceEntity() { }
	
	public UserDeviceEntity(Long id, String nome, String deviceNumber, String pushRecordNumber,
							String pushDescription, UserEntity userEntity) {
		super();
		this.id = id;
		this.nome = nome;
		this.deviceNumber = deviceNumber;
		this.pushRecordNumber = pushRecordNumber;
		this.pushDescription = pushDescription;
		this.user = userEntity;
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

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getPushRecordNumber() {
		return pushRecordNumber;
	}

	public void setPushRecordNumber(String pushRecordNumber) {
		this.pushRecordNumber = pushRecordNumber;
	}

	public String getPushDescription() {
		return pushDescription;
	}

	public void setPushDescription(String pushDescription) {
		this.pushDescription = pushDescription;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
