package com.citadini.ourcity.controllers.dto;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public record OccurrenceCommentRequest(
	Long occurrenceId,
	
	@NotEmpty(message = "Description cannot be blank")
	@Length(min=5, max=256, message="Description must be between 5 and 256 characters")
	String description
	){
}
