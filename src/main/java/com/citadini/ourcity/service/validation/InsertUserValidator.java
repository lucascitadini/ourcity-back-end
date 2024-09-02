package com.citadini.ourcity.service.validation;

import com.citadini.ourcity.domain.UserEntity;
import com.citadini.ourcity.controllers.dto.NewUserRequest;
import com.citadini.ourcity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class InsertUserValidator implements ConstraintValidator<InsertUser, NewUserRequest> {
	
	@Autowired
	private UserRepository repo;

	@Override
	public boolean isValid(NewUserRequest objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		UserEntity aux = repo.findByEmail(objDto.email());
		if (aux != null)
			list.add(new FieldMessage("email", "Email already exists"));
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.message()).addPropertyNode(e.fieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}