package com.citadini.ourcity.service;

import com.citadini.ourcity.domain.UserEntity;
import com.citadini.ourcity.repositories.UserRepository;
import com.citadini.ourcity.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	private final Random rand = new Random();
	
	public void sendNewPassword(String email) {
		UserEntity user = userRepository.findByEmail(email);
		if (user == null)
			throw new ObjectNotFoundException("Email not found");
		String newPass = newPassword();
		user.setPassword(pe.encode(newPass));
		
		userRepository.save(user);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}
		return null;
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) {
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) {
			return (char) (rand.nextInt(26) + 65);
		} else {
			return (char) (rand.nextInt(26) + 97);
		}
	}
	
}
