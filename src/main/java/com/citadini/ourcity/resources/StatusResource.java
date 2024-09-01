package com.citadini.ourcity.resources;

import com.citadini.ourcity.domain.Status;
import com.citadini.ourcity.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/status")
public class StatusResource {

	@Autowired
	private StatusService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Status> find(@PathVariable Integer id) {
		Status categoria = service.find(id);
		return ResponseEntity.ok(categoria);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Status>> findAll() {
		List<Status> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@RequestMapping(value="/page" ,method=RequestMethod.GET)
	public ResponseEntity<Page<Status>> findPage(@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Status> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(list);
	}
}
