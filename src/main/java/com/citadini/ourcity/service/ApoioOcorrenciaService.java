package com.citadini.ourcity.service;

import com.citadini.ourcity.domain.ApoioOcorrencia;
import com.citadini.ourcity.domain.ApoioOcorrenciaPK;
import com.citadini.ourcity.domain.Ocorrencia;
import com.citadini.ourcity.domain.Usuario;
import com.citadini.ourcity.repositories.ApoioOcorrenciaRepository;
import com.citadini.ourcity.security.UserSS;
import com.citadini.ourcity.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ApoioOcorrenciaService {

	@Autowired
	private ApoioOcorrenciaRepository repo;
	@Autowired
	private OcorrenciaService ocorrenciaService;
	@Autowired
	private UsuarioService usuarioService;
	
	public List<ApoioOcorrencia> findByIdOcorrencia(Long idOcorrencia) {
		Ocorrencia ocorrencia = ocorrenciaService.find(idOcorrencia);
		return repo.findByIdOcorrencia(ocorrencia);
	}

	public ApoioOcorrencia fromIdOcorrencia(Long idOcorrencia) {
		UserSS userSS = UserService.authenticated();
		Usuario user = usuarioService.find(userSS.getId());
		Ocorrencia ocorrencia = ocorrenciaService.find(idOcorrencia);
		ApoioOcorrencia apoioOcorrencia = new ApoioOcorrencia(user, ocorrencia, new Date());
		return apoioOcorrencia;
	}

	public ApoioOcorrencia insert(ApoioOcorrencia obj) {
		return repo.save(obj);
	}
	
	public ApoioOcorrencia find(ApoioOcorrenciaPK id) {
		Optional<ApoioOcorrencia> apoioOcorrencia = repo.findById(id);
		return apoioOcorrencia.orElseThrow( () -> new ObjectNotFoundException(
				String.format("Objeto não encontrado: IdOcorrencia: %d, IdUsuario: %d, Tipo: %s", 
						id.getOcorrencia().getId(), id.getUsuario().getId(), ApoioOcorrencia.class.getName())));
	}
	
	public void delete(Long idOcorrencia) {
		UserSS userSS = UserService.authenticated();
		if (idOcorrencia == null) {
			throw new ObjectNotFoundException("ID não pode ser nulo");
		}
		ApoioOcorrenciaPK pk = new ApoioOcorrenciaPK();
		pk.setOcorrencia(ocorrenciaService.find(idOcorrencia));
		pk.setUsuario(usuarioService.find(userSS.getId()));
		find(pk);
		repo.deleteById(pk);
	}

}
