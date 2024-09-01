package com.citadini.ourcity.service;

import com.citadini.ourcity.domain.Usuario;
import com.citadini.ourcity.dto.UsuarioDTO;
import com.citadini.ourcity.dto.UsuarioNewDTO;
import com.citadini.ourcity.repositories.UsuarioRepository;
import com.citadini.ourcity.security.UserSS;
import com.citadini.ourcity.service.exceptions.AuthorizationException;
import com.citadini.ourcity.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Optional;

@Service
public class UsuarioService {

	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private UsuarioRepository repo;
	@Autowired
	private S3Service s3service;
	@Autowired
	private ImageService imageService;
	@Value("${img.prefix.usuario.profile}")
	private String prefix;
	@Value("${img.profile.size}")
	private Integer size;
	
	public Usuario find(Long id) {
		UserSS user = UserService.authenticated();
		if (user == null || !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				String.format("Objeto não encontrado: Id: %d, Tipo: %s", id, Usuario.class.getName())));
	}

	public Usuario findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null || !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Usuario obj = repo.findByEmail(email);
		if (obj == null)
			throw new ObjectNotFoundException(
					String.format("Objeto não encontrado: Email: %s, Tipo: %s", email, Usuario.class.getName()));
		return obj;
	}

	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		String fileName = prefix + user.getId() + ".jpg";
		
		return s3service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}

	public Usuario fromDto(@Valid UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	public Usuario fromDto(@Valid UsuarioNewDTO objDto) {
		Usuario obj = new Usuario(null, objDto.getNome(), objDto.getEmail(), 
				pe.encode(objDto.getSenha()), objDto.getUserName());
		obj.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null)
			obj.getTelefones().add(objDto.getTelefone2());
		if (objDto.getTelefone3() != null)
			obj.getTelefones().add(objDto.getTelefone3());
		return obj;
	}

}