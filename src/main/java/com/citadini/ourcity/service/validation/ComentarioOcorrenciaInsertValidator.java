package com.citadini.ourcity.service.validation;

import com.citadini.ourcity.dto.ComentarioOcorrenciaDTO;
import com.citadini.ourcity.resources.exceptions.FieldMessage;
import com.citadini.ourcity.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ComentarioOcorrenciaInsertValidator implements ConstraintValidator<ComentarioOcorrenciaInsert, ComentarioOcorrenciaDTO> {
	
	@Autowired
	private OcorrenciaService ocorrenciaService;
	
	@Override
	public void initialize(ComentarioOcorrenciaInsert ann) {
	}

	@Override
	public boolean isValid(ComentarioOcorrenciaDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		if (objDto.getOcorrenciaId() == null)
			list.add(new FieldMessage("ocorrenciaId", "Ocorrencia ID não pode ser nulo"));
		ocorrenciaService.find(objDto.getOcorrenciaId());
		return list.isEmpty();
	}
}