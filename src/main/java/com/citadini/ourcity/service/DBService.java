package com.citadini.ourcity.service;

import com.citadini.ourcity.domain.Categoria;
import com.citadini.ourcity.domain.Ocorrencia;
import com.citadini.ourcity.domain.Status;
import com.citadini.ourcity.domain.Usuario;
import com.citadini.ourcity.repositories.CategoriaRepository;
import com.citadini.ourcity.repositories.OcorrenciaRepository;
import com.citadini.ourcity.repositories.StatusRepository;
import com.citadini.ourcity.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;
	
	public void instantiateDatabase() throws ParseException {
		Categoria cat1 = new Categoria(null, "Buraco na Rua");
		Categoria cat2 = new Categoria(null, "Iluminação pública");
		Categoria cat3 = new Categoria(null, "Rodovias");
		Categoria cat4 = new Categoria(null, "Rede de água e Esgoto");
		Categoria cat5 = new Categoria(null, "Coleta de Lixo");
		Categoria cat6 = new Categoria(null, "Transporte público");
		Categoria cat7 = new Categoria(null, "Outros");
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
	
		Status status1 = new Status(null, "Criado");
		Status status2 = new Status(null, "Finalizado");		
		statusRepository.saveAll(Arrays.asList(status1, status2));
		
		Usuario user1 = new Usuario(null, "Maria da Silva", "maria@gmail.com", pe.encode("123"), "MariaSilva");
		user1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		Usuario user2 = new Usuario(null, "João da Silva Barros", "joao@gmail.com", pe.encode("123"), "JoaoBarros");
		user2.getTelefones().addAll(Arrays.asList("93883321", "34252625"));
		Usuario user3 = new Usuario(null, "Lucas Ian Citadini", "l.ian.citadini@gmail.com", pe.encode("123"), "LucasCitadini");
		user3.getTelefones().addAll(Arrays.asList("047991902030"));
		usuarioRepository.saveAll(Arrays.asList(user1, user2, user3));
		
		Ocorrencia ocorrencia1 = new Ocorrencia(null, new Date(), -26.8692457, -49.0549484, 
				"Rua Leocádia Kasulke - Tribess, Blumenau - SC", "Terreno precisando de polda agora", 
				user3, cat7, status1);
		Ocorrencia ocorrencia2 = new Ocorrencia(null, new Date(), -26.9184535, -49.0663338, 
				"Av. Pres. Castelo Branco, 875-751 - Centro Blumenau - SC", "Buraco na rua", 
				user1, cat1, status1);
		ocorrenciaRepository.saveAll(Arrays.asList(ocorrencia1, ocorrencia2));
	}
}
