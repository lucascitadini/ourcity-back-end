package com.citadini.ourcity.service.validation;

import com.citadini.ourcity.domain.UserEntity;
import com.citadini.ourcity.controllers.dto.UserRequest;
import com.citadini.ourcity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UpdateUserValidator implements ConstraintValidator<UpdateUser, UserRequest> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UserRepository repo;

	@SuppressWarnings("unchecked")
	@Override
	public boolean isValid(UserRequest objDto, ConstraintValidatorContext context) {
		
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer urlId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		UserEntity aux = repo.findByEmail(objDto.email());
		if (aux != null && !aux.getId().equals(urlId))
			list.add(new FieldMessage("email", "Email already exists"));
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.message()).addPropertyNode(e.fieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}