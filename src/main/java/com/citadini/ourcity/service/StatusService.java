package com.citadini.ourcity.service;

import com.citadini.ourcity.domain.Status;
import com.citadini.ourcity.repositories.StatusRepository;
import com.citadini.ourcity.service.exceptions.ObjectNotFoundException;
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
	private StatusRepository repo;

	public Status find(Integer id) {
		Optional<Status> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				String.format("Objeto não encontrado: Id: %d, Tipo: %s", id, Status.class.getName())));
	}

	public List<Status> findAll() {
		return repo.findAll();
	}

	public Page<Status> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

}
