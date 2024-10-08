package com.citadini.ourcity.controllers;

import com.citadini.ourcity.domain.UserEntity;
import com.citadini.ourcity.controllers.dto.UserRequest;
import com.citadini.ourcity.controllers.dto.NewUserRequest;
import com.citadini.ourcity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserEntity> find(@PathVariable Long id) {
		UserEntity user = service.find(id);
		return ResponseEntity.ok(user);
	}
	
	@RequestMapping(value="/email", method=RequestMethod.GET)
	public ResponseEntity<UserEntity> find(@RequestParam(value = "value") String email) {
		UserEntity user = service.findByEmail(email);
		return ResponseEntity.ok(user);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody NewUserRequest objDto) {
		UserEntity obj = service.fromDto(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody UserRequest objDto) {
		UserEntity obj = service.fromDto(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/picture", method=RequestMethod.POST)
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file) {
		URI uri = service.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}
	
}
