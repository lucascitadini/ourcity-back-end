package com.citadini.ourcity.controllers;

import com.citadini.ourcity.domain.StatusEntity;
import com.citadini.ourcity.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/status")
public class StatusController {

	@Autowired
	private StatusService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<StatusEntity> find(@PathVariable Integer id) {
		StatusEntity status = service.find(id);
		return ResponseEntity.ok(status);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<StatusEntity>> findAll() {
		List<StatusEntity> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@RequestMapping(value="/page" ,method=RequestMethod.GET)
	public ResponseEntity<Page<StatusEntity>> findPage(@RequestParam(value="page", defaultValue="0") Integer page,
                                                       @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
                                                       @RequestParam(value="orderBy", defaultValue="name") String orderBy,
                                                       @RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<StatusEntity> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(list);
	}
}
