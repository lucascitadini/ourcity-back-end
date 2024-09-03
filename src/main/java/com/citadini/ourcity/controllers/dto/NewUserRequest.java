package com.citadini.ourcity.controllers.dto;

import com.citadini.ourcity.service.validation.InsertUser;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@InsertUser
public record NewUserRequest (
		@NotEmpty(message = "Name cannot be blank")
		@Length(min=5, max=120, message="Name must be between 5 and 120 characters")
		String name,
		@NotEmpty(message = "UserName cannot be blank")
		@Length(min=5, max=20, message="UserName must be between 5 and 20 characters")
		String userName,
		@NotEmpty(message = "Email cannot be blank")
		@Email(message = "Invalid Email")
		String email,
		@NotEmpty(message = "Password cannot be blank")
		String password,
		@NotEmpty(message = "Cellphone1 cannot be blank")
		String cellphone1,
		String cellphone2,
		String cellphone3) {
}
