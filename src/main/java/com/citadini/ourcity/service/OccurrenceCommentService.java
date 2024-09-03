package com.citadini.ourcity.service;

import com.citadini.ourcity.domain.OccurrenceCommentEntity;
import com.citadini.ourcity.domain.OccurrenceEntity;
import com.citadini.ourcity.domain.UserEntity;
import com.citadini.ourcity.controllers.dto.OccurrenceCommentRequest;
import com.citadini.ourcity.exceptions.AccessDeniedException;
import com.citadini.ourcity.exceptions.NotFoundException;
import com.citadini.ourcity.repositories.OccurrenceCommentRepository;
import com.citadini.ourcity.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

import java.util.Date;
import java.util.Optional;

@Service
public class OccurrenceCommentService {

	@Autowired
	private OccurrenceCommentRepository occurrenceCommentRepository;
	
	@Autowired
	private OccurrenceService occurrenceService;
	
	@Autowired
	private UserService userService;
	
	public Page<OccurrenceCommentEntity> search(Integer page, Integer linesPerPage, String orderBy, String direction, Long occurrenceId) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		OccurrenceEntity occurrence = occurrenceService.find(occurrenceId);
		return occurrenceCommentRepository.findByOccurrence(occurrence, pageRequest);
	}

	public OccurrenceCommentEntity fromDto(@Valid OccurrenceCommentRequest objDto) {
		UserSS userSS = UserAuthenticateService.authenticated();
		UserEntity user = userService.find(userSS.getId());
		OccurrenceEntity occurrence = occurrenceService.find(objDto.occurrenceId());
        return new OccurrenceCommentEntity(
				null,
				user,
				occurrence,
				objDto.description(),
				new Date());
	}
	
	public OccurrenceCommentEntity insert(OccurrenceCommentEntity obj) {
		return occurrenceCommentRepository.save(obj);
	}

	public OccurrenceCommentEntity find(Long id) {
		Optional<OccurrenceCommentEntity> occurrenceComment = occurrenceCommentRepository.findById(id);
		return occurrenceComment.orElseThrow( () -> new NotFoundException(
				String.format("Object not found: Id: %d, Type: %s", id, OccurrenceCommentEntity.class.getName())));
	}
	
	public void delete(Long id) {
		UserSS user = UserAuthenticateService.authenticated();
		OccurrenceCommentEntity occurrenceComment = find(id);
		if (user == null || !occurrenceComment.getUser().getId().equals(user.getId())) {
			throw new AccessDeniedException("Access denied");
		}
		occurrenceCommentRepository.deleteById(id);
	}
	
}