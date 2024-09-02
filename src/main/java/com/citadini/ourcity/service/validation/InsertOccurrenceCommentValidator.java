package com.citadini.ourcity.service.validation;

import com.citadini.ourcity.controllers.dto.OccurrenceCommentRequest;
import com.citadini.ourcity.controllers.exceptions.FieldMessage;
import com.citadini.ourcity.service.OccurrenceService;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class InsertOccurrenceCommentValidator implements ConstraintValidator<InsertOccurrenceComment, OccurrenceCommentRequest> {
	
	@Autowired
	private OccurrenceService occurrenceService;

	@Override
	public boolean isValid(OccurrenceCommentRequest objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		if (objDto.occurrenceId() == null)
			list.add(new FieldMessage("occurrenceId", "OccurrenceId cannot be blank"));
		occurrenceService.find(objDto.occurrenceId());
		return list.isEmpty();
	}
}