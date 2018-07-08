package com.citadini.ourcity.service;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.citadini.ourcity.domain.Categoria;
import com.citadini.ourcity.domain.Ocorrencia;
import com.citadini.ourcity.domain.Status;
import com.citadini.ourcity.domain.Usuario;
import com.citadini.ourcity.dto.OcorrenciaNewDTO;
import com.citadini.ourcity.repositories.CategoriaRepository;
import com.citadini.ourcity.repositories.OcorrenciaRepository;
import com.citadini.ourcity.repositories.StatusRepository;
import com.citadini.ourcity.repositories.UsuarioRepository;
import com.citadini.ourcity.security.UserSS;
import com.citadini.ourcity.service.exceptions.AuthorizationException;
import com.citadini.ourcity.service.exceptions.ObjectNotFoundException;

@Service
public class OcorrenciaService {

	@Autowired
	private OcorrenciaRepository repo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private StatusRepository statusRepo;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private S3Service s3service;
	
	@Value("${img.prefix.ocorrencia.profile}")
	private String prefix;
	
	@Value("${img.ocorrencia.size}")
	private Integer size;
	
	public Ocorrencia find(Long id) {
		Optional<Ocorrencia> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				String.format("Objeto não encontrado: Id: %d, Tipo: %s", id, Ocorrencia.class.getName())));
	}

	public Page<Ocorrencia> search(String endereco, Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findDistinctByEnderecoCompletoContainingIgnoreCase(endereco, pageRequest);
	}

	public Ocorrencia fromDto(@Valid OcorrenciaNewDTO objDto) {
		UserSS userSS = UserService.authenticated();
		Optional<Usuario> user = usuarioRepo.findById(userSS.getId());
		Optional<Categoria> cat = categoriaRepo.findById(objDto.getCategoriaId());
		Optional<Status> status = statusRepo.findById(objDto.getStatusId());
		Ocorrencia ocorrencia = new Ocorrencia(null, 
				new Date(), 
				objDto.getLatitude(), 
				objDto.getLongitude(),
				objDto.getEnderecoCompleto(), 
				objDto.getDescricao(), 
				user.orElseThrow( () -> new ObjectNotFoundException(
						String.format("Objeto não encontrado: Id: %d, Tipo: %s", userSS.getId(), Usuario.class.getName()))),
				cat.orElseThrow( () -> new ObjectNotFoundException(
						String.format("Objeto não encontrado: Id: %d, Tipo: %s", objDto.getCategoriaId(), Categoria.class.getName()))),
				status.orElseThrow( () -> new ObjectNotFoundException(
						String.format("Objeto não encontrado: Id: %d, Tipo: %s", objDto.getStatusId(), Status.class.getName())))
				);
		return ocorrencia;
	}

	public Ocorrencia insert(Ocorrencia obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public void delete(Long id) {
		UserSS user = UserService.authenticated();
		Ocorrencia ocorrencia = find(id);
		if (user == null || !ocorrencia.getUsuario().getId().equals(user.getId())) 
			throw new AuthorizationException("Acesso negado");
		repo.deleteById(id);
	}

	public URI uploadOcorrenciaPicture(MultipartFile file, Long id) {
		Ocorrencia ocorrencia = find(id);
		UserSS user = UserService.authenticated();
		if (ocorrencia.getUsuario().getId() != user.getId())
			throw new AuthorizationException("Acesso negado");

		BufferedImage jpgImage = imageService.getJpgImageFromFile(file);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		String fileName = prefix + ocorrencia.getId() + ".jpg";
		
		return s3service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}

	public Page<Ocorrencia> search(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

}
