package com.citadini.ourcity.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class OccurrenceSupportPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name = "occurrence_id")
	private OccurrenceEntity occurrence;
	
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((occurrence == null) ? 0 : occurrence.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OccurrenceSupportPK other = (OccurrenceSupportPK) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (occurrence == null) {
			if (other.occurrence != null)
				return false;
		} else if (!occurrence.equals(other.occurrence))
			return false;
		return true;
	}
}
