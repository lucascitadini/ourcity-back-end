package com.citadini.ourcity.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.citadini.ourcity.dto.ComentarioOcorrenciaDTO;
import com.citadini.ourcity.resources.exceptions.FieldMessage;
import com.citadini.ourcity.service.OcorrenciaService;

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