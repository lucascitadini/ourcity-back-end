package com.citadini.ourcity.controllers;

import com.citadini.ourcity.domain.CategoryEntity;
import com.citadini.ourcity.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<CategoryEntity> find(@PathVariable Integer id) {
		CategoryEntity category = service.find(id);
		return ResponseEntity.ok(category);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoryEntity>> findAll() {
		List<CategoryEntity> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@RequestMapping(value="/page" ,method=RequestMethod.GET)
	public ResponseEntity<Page<CategoryEntity>> findPage(@RequestParam(value="page", defaultValue="0") Integer page,
														 @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
														 @RequestParam(value="orderBy", defaultValue="nome") String orderBy,
														 @RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<CategoryEntity> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(list);
	}
}
