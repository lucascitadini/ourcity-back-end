package com.citadini.ourcity.controllers.dto;

import com.citadini.ourcity.service.validation.InsertOccurrence;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;

@InsertOccurrence
public record NewOccurrenceRequest (
		Double latitude,
		Double longitude,
		@NotEmpty(message = "FullAddress cannot be blank")
		@Length(min=2, max=248, message="FullAddress must be between 2 and 248 characters")
		String fullAddress,
		@NotEmpty(message = "Description cannot be blank")
		@Length(min=5, max=516, message="Description must be between 2 and 248 characters")
		String description,
		Integer categoryId,
		Integer statusId) {
}