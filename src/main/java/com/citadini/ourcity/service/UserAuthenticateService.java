package com.citadini.ourcity.service;

import com.citadini.ourcity.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserAuthenticateService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
	
}
