package com.citadini.ourcity.service.validation;

import com.citadini.ourcity.controllers.dto.NewOccurrenceRequest;
import com.citadini.ourcity.service.CategoryService;
import com.citadini.ourcity.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class InsertOccurrenceValidator implements ConstraintValidator<InsertOccurrence, NewOccurrenceRequest> {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private StatusService statusService;

	@Override
	public boolean isValid(NewOccurrenceRequest objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		if (objDto.latitude() == null)
			list.add(new FieldMessage("latitude", "Latitude cannot be blank"));
		if (objDto.longitude() == null)
			list.add(new FieldMessage("longitude", "Longitude cannot be blank"));
		if (objDto.categoryId() == null)
			list.add(new FieldMessage("categoryId", "Category ID  cannot be blank"));
		else 
			categoryService.find(objDto.categoryId());
		if (objDto.statusId() == null)
			list.add(new FieldMessage("statusId", "Status ID  cannot be blank"));
		else
			statusService.find(objDto.statusId());
		
		return list.isEmpty();
	}
}