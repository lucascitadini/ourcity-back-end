package com.citadini.ourcity.service;

import com.citadini.ourcity.domain.ComentarioOcorrencia;
import com.citadini.ourcity.domain.Ocorrencia;
import com.citadini.ourcity.domain.Usuario;
import com.citadini.ourcity.dto.ComentarioOcorrenciaDTO;
import com.citadini.ourcity.repositories.ComentarioOcorrenciaRepository;
import com.citadini.ourcity.security.UserSS;
import com.citadini.ourcity.service.exceptions.AuthorizationException;
import com.citadini.ourcity.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import java.util.Date;
import java.util.Optional;

@Service
public class ComentarioOcorrenciaService {

	@Autowired
	private ComentarioOcorrenciaRepository repo;
	
	@Autowired
	private OcorrenciaService ocorrenciaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Page<ComentarioOcorrencia> search(Integer page, Integer linesPerPage, String orderBy, String direction, Long idOcorrencia) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Ocorrencia ocorrencia = ocorrenciaService.find(idOcorrencia);
		return repo.findByOcorrencia(ocorrencia, pageRequest);
	}

	public ComentarioOcorrencia fromDto(@Valid ComentarioOcorrenciaDTO objDto) {
		UserSS userSS = UserService.authenticated();
		Usuario user = usuarioService.find(userSS.getId());
		Ocorrencia ocorrencia = ocorrenciaService.find(objDto.getOcorrenciaId());
		ComentarioOcorrencia comentarioOcorrencia = new ComentarioOcorrencia(
				null,
				user, 
				ocorrencia,
				objDto.getDescricao(),
				new Date());
		return comentarioOcorrencia;
	}
	
	public ComentarioOcorrencia insert(ComentarioOcorrencia obj) {
		return repo.save(obj);
	}

	public ComentarioOcorrencia find(Long id) {
		Optional<ComentarioOcorrencia> comentarioOcorrencia = repo.findById(id);
		return comentarioOcorrencia.orElseThrow( () -> new ObjectNotFoundException(
				String.format("Objeto não encontrado: Id: %d, Tipo: %s", id, ComentarioOcorrencia.class.getName())));
	}
	
	public void delete(Long id) {
		UserSS user = UserService.authenticated();
		ComentarioOcorrencia comentarioOcorrencia = find(id);
		if (user == null || !comentarioOcorrencia.getUsuario().getId().equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		repo.deleteById(id);
	}
	
}