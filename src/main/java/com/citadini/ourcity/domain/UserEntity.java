package com.citadini.ourcity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String userName;
	
	private String name;
	
	@Column(unique=true)
	private String email;
	
	@JsonIgnore
	private String password;
	
	@ElementCollection
	@CollectionTable(name="phone")
	private Set<String> phones = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<OccurrenceEntity> occurrences = new ArrayList<>();
	
	@OneToMany(mappedBy="user")
	private List<UserConfigurationEntity> configurations = new ArrayList<>();
	
	@OneToMany(mappedBy="user")
	private List<UserDeviceEntity> devices = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<OccurrenceCommentEntity> comments = new ArrayList<>();

	public UserEntity() {
		
	}
	
	public UserEntity(Long id, String name, String email, String password, String userName) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<String> getPhones() {
		return phones;
	}

	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}

	public List<OccurrenceEntity> getOccurrences() {
		return occurrences;
	}

	public void setOccurrences(List<OccurrenceEntity> ocorrencia) {
		this.occurrences = ocorrencia;
	}

	public List<UserConfigurationEntity> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(List<UserConfigurationEntity> configurations) {
		this.configurations = configurations;
	}

	public List<UserDeviceEntity> getDevices() {
		return devices;
	}

	public void setDevices(List<UserDeviceEntity> devices) {
		this.devices = devices;
	}

	public List<OccurrenceCommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<OccurrenceCommentEntity> comments) {
		this.comments = comments;
	}
}
