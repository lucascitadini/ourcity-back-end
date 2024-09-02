package com.citadini.ourcity.controllers;

import com.citadini.ourcity.controllers.dto.EmailRequest;
import com.citadini.ourcity.security.JWTUtil;
import com.citadini.ourcity.security.UserSS;
import com.citadini.ourcity.service.AuthService;
import com.citadini.ourcity.service.UserAuthenticateService;
import com.citadini.ourcity.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
	
	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthService service;
	
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserAuthenticateService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailRequest objDto) {
		service.sendNewPassword(objDto.email());
		return ResponseEntity.noContent().build();
	}

}
