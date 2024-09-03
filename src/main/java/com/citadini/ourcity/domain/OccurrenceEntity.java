package com.citadini.ourcity.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class OccurrenceEntity implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Date creationDate;
	private Double latitude;
	private Double longitude;
	private String fullAddress;
	private String description;
	@ManyToOne
	@JoinColumn(name="creation_user_id")
	@JsonIgnore
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private CategoryEntity category;
	
	@ManyToOne
	@JoinColumn(name="status_id")
	private StatusEntity status;
	
	@OneToMany(mappedBy="occurrence")
	@JsonIgnore
	private List<OccurrenceCommentEntity> comments = new ArrayList<OccurrenceCommentEntity>();
	
	@OneToMany(mappedBy="id.occurrence")
	@JsonIgnore
	private List<OccurrenceSupportEntity> supports = new ArrayList<OccurrenceSupportEntity>();
	
	public OccurrenceEntity() {
	}
	
	public OccurrenceEntity(Long id, Date creationDate, Double latitude, Double longitude, String fullAddress,
							String description, UserEntity userEntity, CategoryEntity categoryEntity, StatusEntity status) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.fullAddress = fullAddress;
		this.description = description;
		this.user = userEntity;
		this.category = categoryEntity;
		this.status = status;
	}
	
	public Integer getSupportSize() {
		return this.supports.size();
	}
	
	public Integer getCommentsSize() {
		return this.comments.size();
	}

	public Long getUserId() {
		return this.user.getId();
	}
	
	public String getUserName() {
		return this.user.getName();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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
	
	public String getFullAddress() {
		return fullAddress;
	}
	
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
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

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public StatusEntity getStatus() {
		return status;
	}

	public void setStatus(StatusEntity status) {
		this.status = status;
	}

	public List<OccurrenceCommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<OccurrenceCommentEntity> comments) {
		this.comments = comments;
	}

	public List<OccurrenceSupportEntity> getSupports() {
		return supports;
	}

	public void setSupports(List<OccurrenceSupportEntity> supports) {
		this.supports = supports;
	}
}