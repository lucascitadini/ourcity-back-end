package com.citadini.ourcity.service;

import com.citadini.ourcity.domain.Usuario;
import com.citadini.ourcity.repositories.UsuarioRepository;
import com.citadini.ourcity.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		Usuario cliente = usuarioRepo.findByEmail(email);
		if (cliente == null) 
			throw new ObjectNotFoundException("Email não encontrado");
		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass));
		
		usuarioRepo.save(cliente);
//		emailService.sendNewPasswordEmail(cliente, newPass);
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
		if (opt == 0) {// gera um dígito
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) {// gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		} else {// gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
	
}
