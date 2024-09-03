package com.citadini.ourcity.controllers;

import com.citadini.ourcity.controllers.dto.OccurrenceRequest;
import com.citadini.ourcity.domain.OccurrenceSupportEntity;
import com.citadini.ourcity.domain.OccurrenceCommentEntity;
import com.citadini.ourcity.domain.OccurrenceEntity;
import com.citadini.ourcity.controllers.dto.OccurrenceCommentRequest;
import com.citadini.ourcity.controllers.dto.NewOccurrenceRequest;
import com.citadini.ourcity.controllers.utils.URL;
import com.citadini.ourcity.service.OccurrenceSupportService;
import com.citadini.ourcity.service.OccurrenceCommentService;
import com.citadini.ourcity.service.OccurrenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/occurrences")
public class OccurrenceController {
	
	@Autowired
	private OccurrenceService occurrenceService;
	
	@Autowired
	private OccurrenceCommentService occurrenceCommentService;
	
	@Autowired
	private OccurrenceSupportService occurrenceSupportService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<OccurrenceEntity> find(@PathVariable Long id) {
		OccurrenceEntity obj = occurrenceService.find(id);
		return ResponseEntity.ok(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<OccurrenceEntity>> findAll(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="creationDate") String orderBy,
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		Page<OccurrenceEntity> list = occurrenceService.search(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(list);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<OccurrenceEntity>> findPage(
			@RequestParam(value="address", defaultValue="") String address,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="creationDate") String orderBy,
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		String enderecoDecoded = "";
		if (address != null && !address.isEmpty()) {
			enderecoDecoded = URL.decodeParam(address);
		}
		Page<OccurrenceEntity> list = occurrenceService.search(enderecoDecoded, page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(list);
	}
	
	@RequestMapping(method=RequestMethod.GET, value= "/comments/page")
	public ResponseEntity<Page<OccurrenceCommentEntity>> findCommentsPage(
			@RequestParam(value="occurrenceId", defaultValue="0") Long occurrenceId,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="creationDate") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<OccurrenceCommentEntity> list = occurrenceCommentService.search(page, linesPerPage, orderBy, direction, occurrenceId);
		return ResponseEntity.ok(list);
	}

	@RequestMapping(value="/supports/{occurrenceId}", method=RequestMethod.GET)
	public ResponseEntity<List<OccurrenceSupportEntity>> findSupports(@PathVariable Long occurrenceId) {
		List<OccurrenceSupportEntity> obj = occurrenceSupportService.findByOccurrenceId(occurrenceId);
		return ResponseEntity.ok(obj);
	}
	
	@RequestMapping(value="/comments/{id}", method=RequestMethod.GET)
	public ResponseEntity<OccurrenceCommentEntity> findComment(@PathVariable Long id) {
		OccurrenceCommentEntity obj = occurrenceCommentService.find(id);
		return ResponseEntity.ok(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody NewOccurrenceRequest objDto) {
		OccurrenceEntity obj = occurrenceService.fromDto(objDto);
		obj = occurrenceService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/supports/{occurrenceId}", method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@PathVariable Long occurrenceId) {
		OccurrenceSupportEntity obj = occurrenceSupportService.fromOccurrenceId(occurrenceId);
		obj = occurrenceSupportService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/comments", method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody OccurrenceCommentRequest objDto) {
		OccurrenceCommentEntity obj = occurrenceCommentService.fromDto(objDto);
		obj = occurrenceCommentService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.PUT) 
	public ResponseEntity<Void> update(@Valid @RequestBody OccurrenceRequest request) {
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<OccurrenceEntity> delete(@PathVariable Long id) {
		occurrenceService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/supports/{occurrenceId}", method=RequestMethod.DELETE)
	public ResponseEntity<OccurrenceSupportEntity> deleteSupport(@PathVariable Long occurrenceId) {
		occurrenceSupportService.delete(occurrenceId);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/comments/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<OccurrenceCommentEntity> deleteComment(@PathVariable Long id) {
		occurrenceCommentService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/picture/{id}", method=RequestMethod.POST)
	public ResponseEntity<Void> uploadPicture(
			@PathVariable Long id,
			@RequestParam(name="file") MultipartFile file
			) {
		URI uri = occurrenceService.uploadOccurrencePicture(file, id);
		return ResponseEntity.created(uri).build();
	}
}