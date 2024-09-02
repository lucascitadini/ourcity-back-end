package com.citadini.ourcity.service.validation;

import com.citadini.ourcity.controllers.dto.OccurrenceRequest;
import com.citadini.ourcity.domain.OccurrenceEntity;
import com.citadini.ourcity.domain.enums.StatusEnum;
import com.citadini.ourcity.controllers.exceptions.FieldMessage;
import com.citadini.ourcity.service.CategoryService;
import com.citadini.ourcity.service.OccurrenceService;
import com.citadini.ourcity.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UpdateOccurrenceValidator implements ConstraintValidator<UpdateOccurrence, OccurrenceRequest> {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private StatusService statusService;
	
	@Autowired
	private OccurrenceService occurrenceService;

	@Override
	public boolean isValid(OccurrenceRequest objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		if (objDto.id() == null)
			list.add(new FieldMessage("id", "Id cannot be blank"));
		OccurrenceEntity occurrence = occurrenceService.find(objDto.id());
		if (!occurrence.getStatus().getId().equals(StatusEnum.PENDING.getCod()))
			list.add(new FieldMessage("status", "Status other than Created, cannot change the Occurrence."));
		if (occurrence.getSupportSize() != 0) {
			list.add(new FieldMessage("supports", "Occurrence is already being supported, it cannot be changed."));
		}
		
		if (objDto.latitude() == null)
			list.add(new FieldMessage("latitude", "Latitude cannot be blank"));
		if (objDto.longitude() == null)
			list.add(new FieldMessage("longitude", "Longitude cannot be blank"));
		if (objDto.categoryId() == null)
			list.add(new FieldMessage("categoryId", "Category Id cannot be blank"));
		categoryService.find(objDto.categoryId());
		if (objDto.statusId() == null)
			list.add(new FieldMessage("statusId", "Status ID cannot be blank"));
		statusService.find(objDto.statusId());
		
		return list.isEmpty();
	}
}