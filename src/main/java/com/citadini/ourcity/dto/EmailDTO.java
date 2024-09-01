package com.citadini.ourcity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;

public class EmailDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email Inválido")
	private String email;

	public EmailDTO() {
		
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
