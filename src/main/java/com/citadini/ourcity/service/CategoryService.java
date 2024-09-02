package com.citadini.ourcity.service;

import com.citadini.ourcity.domain.CategoryEntity;
import com.citadini.ourcity.repositories.CategoryRepository;
import com.citadini.ourcity.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repo;

	public CategoryEntity find(Integer id) {
		Optional<CategoryEntity> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				String.format("Object Not Found: Id: %d, Type: %s", id, CategoryEntity.class.getName())));
	}

	public List<CategoryEntity> findAll() {
		return repo.findAll();
	}

	public Page<CategoryEntity> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

}
