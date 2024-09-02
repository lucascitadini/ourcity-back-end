package com.citadini.ourcity.controllers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;

public record EmailRequest (
	@NotEmpty(message = "Email cannot be blank")
	@Email(message = "Invalid Email")
	String email ){
}
