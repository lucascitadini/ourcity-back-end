package com.citadini.ourcity.service;

import com.citadini.ourcity.domain.UserEntity;
import com.citadini.ourcity.controllers.dto.UserRequest;
import com.citadini.ourcity.controllers.dto.NewUserRequest;
import com.citadini.ourcity.exceptions.AccessDeniedException;
import com.citadini.ourcity.exceptions.NotFoundException;
import com.citadini.ourcity.repositories.UserRepository;
import com.citadini.ourcity.security.UserSS;
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
public class UserService {

	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private UserRepository repo;
	@Autowired
	private S3Service s3service;
	@Autowired
	private ImageService imageService;
	@Value("${img.prefix.user.profile}")
	private String prefix;
	@Value("${img.profile.size}")
	private Integer size;
	
	public UserEntity find(Long id) {
		UserSS user = UserAuthenticateService.authenticated();
		if (user == null || !id.equals(user.getId())) {
			throw new AccessDeniedException("Access denied");
		}
		
		Optional<UserEntity> obj = repo.findById(id);
		return obj.orElseThrow( () -> new NotFoundException(
				String.format("Object not found: Id: %d, Type: %s", id, UserEntity.class.getName())));
	}

	public UserEntity findByEmail(String email) {
		UserSS user = UserAuthenticateService.authenticated();
		if (user == null || !email.equals(user.getUsername())) {
			throw new AccessDeniedException("Access denied");
		}
		
		UserEntity obj = repo.findByEmail(email);
		if (obj == null)
			throw new NotFoundException(
					String.format("Object not found: Email: %s, Type: %s", email, UserEntity.class.getName()));
		return obj;
	}

	public UserEntity insert(UserEntity obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public UserEntity update(UserEntity obj) {
		UserEntity newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(UserEntity newObj, UserEntity obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = getUserSS();

		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		String fileName = prefix + user.getId() + ".jpg";
		
		return s3service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}

	private static UserSS getUserSS() {
		UserSS user = UserAuthenticateService.authenticated();
		if (user == null) {
			throw new AccessDeniedException("Access denied");
		}
		return user;
	}

	public UserEntity fromDto(@Valid UserRequest objDto) {
		return new UserEntity(objDto.id(), objDto.name(), objDto.email(), null, null);
	}

	public UserEntity fromDto(@Valid NewUserRequest objDto) {
		UserEntity obj = new UserEntity(null, objDto.name(), objDto.email(),
				pe.encode(objDto.password()), objDto.userName());
		obj.getPhones().add(objDto.cellphone1());
		if (objDto.cellphone2() != null)
			obj.getPhones().add(objDto.cellphone2());
		if (objDto.cellphone3() != null)
			obj.getPhones().add(objDto.cellphone3());
		return obj;
	}

}