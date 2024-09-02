package com.citadini.ourcity.service;

import com.citadini.ourcity.domain.CategoryEntity;
import com.citadini.ourcity.domain.OccurrenceEntity;
import com.citadini.ourcity.domain.StatusEntity;
import com.citadini.ourcity.domain.UserEntity;
import com.citadini.ourcity.controllers.dto.NewOccurrenceRequest;
import com.citadini.ourcity.repositories.CategoryRepository;
import com.citadini.ourcity.repositories.OccurrenceRepository;
import com.citadini.ourcity.repositories.StatusRepository;
import com.citadini.ourcity.repositories.UserRepository;
import com.citadini.ourcity.security.UserSS;
import com.citadini.ourcity.service.exceptions.AuthorizationException;
import com.citadini.ourcity.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Date;
import java.util.Optional;

@Service
public class OccurrenceService {

	@Autowired
	private OccurrenceRepository occurrenceRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private S3Service s3service;
	
	@Value("${img.prefix.occurrence.profile}")
	private String prefix;
	
	@Value("${img.occurrence.size}")
	private Integer size;
	
	public OccurrenceEntity find(Long id) {
		Optional<OccurrenceEntity> obj = occurrenceRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				String.format("Object not found: Id: %d, Type: %s", id, OccurrenceEntity.class.getName())));
	}

	public Page<OccurrenceEntity> search(String address, Integer page, Integer linesPerPage,
										 String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return occurrenceRepository.findDistinctByFullAddressContainingIgnoreCase(address, pageRequest);
	}

	public OccurrenceEntity fromDto(@Valid NewOccurrenceRequest objDto) {
		UserSS userSS = UserAuthenticateService.authenticated();
		Optional<UserEntity> user = userRepository.findById(userSS.getId());
		Optional<CategoryEntity> cat = categoryRepository.findById(objDto.categoryId());
		Optional<StatusEntity> status = statusRepository.findById(objDto.statusId());
        return new OccurrenceEntity(null,
				new Date(),
				objDto.latitude(),
				objDto.longitude(),
				objDto.fullAddress(),
				objDto.description(),
				user.orElseThrow( () -> new ObjectNotFoundException(
						String.format("Object not found: Id: %d, Type: %s", userSS.getId(), UserEntity.class.getName()))),
				cat.orElseThrow( () -> new ObjectNotFoundException(
						String.format("Object not found: Id: %d, Type: %s", objDto.categoryId(), CategoryEntity.class.getName()))),
				status.orElseThrow( () -> new ObjectNotFoundException(
						String.format("Object not found: Id: %d, Type: %s", objDto.statusId(), StatusEntity.class.getName())))
				);
	}

	public OccurrenceEntity insert(OccurrenceEntity obj) {
		obj.setId(null);
		obj = occurrenceRepository.save(obj);
		return obj;
	}

	public void delete(Long id) {
		UserSS user = UserAuthenticateService.authenticated();
		OccurrenceEntity occurrence = find(id);
		if (user == null || !occurrence.getUser().getId().equals(user.getId()))
			throw new AuthorizationException("Access denied");
		occurrenceRepository.deleteById(id);
	}

	public URI uploadOccurrencePicture(MultipartFile file, Long id) {
		OccurrenceEntity occurrence = find(id);
		UserSS user = UserAuthenticateService.authenticated();
		if (occurrence.getUser().getId() != user.getId())
			throw new AuthorizationException("Access denied");

		BufferedImage jpgImage = imageService.getJpgImageFromFile(file);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		String fileName = prefix + occurrence.getId() + ".jpg";
		
		return s3service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}

	public Page<OccurrenceEntity> search(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return occurrenceRepository.findAll(pageRequest);
	}

}
