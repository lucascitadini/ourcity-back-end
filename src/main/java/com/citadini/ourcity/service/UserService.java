package com.citadini.ourcity.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.citadini.ourcity.security.UserSS;

public class UserService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
	
}
