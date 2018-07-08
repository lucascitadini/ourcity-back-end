package com.citadini.ourcity.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.citadini.ourcity.domain.Ocorrencia;
import com.citadini.ourcity.domain.enums.StatusEnum;
import com.citadini.ourcity.dto.OcorrenciaDTO;
import com.citadini.ourcity.resources.exceptions.FieldMessage;
import com.citadini.ourcity.service.CategoriaService;
import com.citadini.ourcity.service.OcorrenciaService;
import com.citadini.ourcity.service.StatusService;

public class OcorrenciaUpdateValidator implements ConstraintValidator<OcorrenciaUpdate, OcorrenciaDTO> {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private StatusService statusService;
	
	@Autowired
	private OcorrenciaService ocorrenciaService;
	
	@Override
	public void initialize(OcorrenciaUpdate ann) {
	}

	@Override
	public boolean isValid(OcorrenciaDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		if (objDto.getId() == null)
			list.add(new FieldMessage("id", "ID não pode ser nulo"));
		Ocorrencia ocorrencia = ocorrenciaService.find(objDto.getId());
		if (!ocorrencia.getStatus().getId().equals(StatusEnum.CRIADOR.getCod())) 
			list.add(new FieldMessage("status", "Status diferente de Criado, não pode alterar a Ocorrência."));
		if (ocorrencia.getTotalApoios() != 0) {
			list.add(new FieldMessage("apoios", "Ocorrência já está sendo apoiada, não pode ser alterada."));
		}
		
		if (objDto.getLatitude() == null)
			list.add(new FieldMessage("latitude", "Latitude não pode ser nulo"));
		if (objDto.getLongitude() == null)
			list.add(new FieldMessage("longitude", "Longitude não pode ser nulo"));
		if (objDto.getCategoriaId() == null)
			list.add(new FieldMessage("categoriaId", "Categoria ID não pode ser nulo"));
		categoriaService.find(objDto.getCategoriaId());
		if (objDto.getStatusId() == null)
			list.add(new FieldMessage("statusId", "Status ID não pode ser nulo"));
		statusService.find(objDto.getStatusId());
		
		return list.isEmpty();
	}
}