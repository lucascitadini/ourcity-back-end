package com.citadini.ourcity.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.citadini.ourcity.dto.OcorrenciaNewDTO;
import com.citadini.ourcity.resources.exceptions.FieldMessage;
import com.citadini.ourcity.service.CategoriaService;
import com.citadini.ourcity.service.StatusService;

public class OcorrenciaInsertValidator implements ConstraintValidator<OcorrenciaInsert, OcorrenciaNewDTO> {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private StatusService statusService;
	
	@Override
	public void initialize(OcorrenciaInsert ann) {
		
	}

	@Override
	public boolean isValid(OcorrenciaNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		if (objDto.getLatitude() == null)
			list.add(new FieldMessage("latitude", "Latitude não pode ser nulo"));
		if (objDto.getLongitude() == null)
			list.add(new FieldMessage("longitude", "Longitude não pode ser nulo"));
		if (objDto.getCategoriaId() == null)
			list.add(new FieldMessage("categoriaId", "Categoria ID não pode ser nulo"));
		else 
			categoriaService.find(objDto.getCategoriaId());
		if (objDto.getStatusId() == null)
			list.add(new FieldMessage("statusId", "Status ID não pode ser nulo"));
		else
			statusService.find(objDto.getStatusId());
		
		return list.isEmpty();
	}
}