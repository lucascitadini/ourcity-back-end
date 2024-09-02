package com.citadini.ourcity.service;

import com.citadini.ourcity.domain.StatusEntity;
import com.citadini.ourcity.exceptions.NotFoundException;
import com.citadini.ourcity.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {
	
	@Autowired
	private StatusRepository statusRepository;

	public StatusEntity find(Integer id) {
		Optional<StatusEntity> obj = statusRepository.findById(id);
		return obj.orElseThrow(() -> new NotFoundException(
				String.format("Object not found: Id: %d, Type: %s", id, StatusEntity.class.getName())));
	}

	public List<StatusEntity> findAll() {
		return statusRepository.findAll();
	}

	public Page<StatusEntity> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return statusRepository.findAll(pageRequest);
	}

}
