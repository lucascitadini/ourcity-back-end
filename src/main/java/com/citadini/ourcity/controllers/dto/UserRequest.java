package com.citadini.ourcity.controllers.dto;

import com.citadini.ourcity.service.validation.UpdateUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

@UpdateUser
public record UserRequest (
		Long id,
		@NotEmpty(message = "Name cannot be name")
		@Length(min=5, max=120, message="Name must be between 5 and 120 characters")
		String name,
		@NotEmpty(message = "Email cannot be email")
		@Email(message = "Invalid Email")
		String email ) {
}
