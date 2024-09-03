package com.citadini.ourcity.service;

import com.citadini.ourcity.domain.CategoryEntity;
import com.citadini.ourcity.domain.OccurrenceEntity;
import com.citadini.ourcity.domain.StatusEntity;
import com.citadini.ourcity.domain.UserEntity;
import com.citadini.ourcity.repositories.CategoryRepository;
import com.citadini.ourcity.repositories.OccurrenceRepository;
import com.citadini.ourcity.repositories.StatusRepository;
import com.citadini.ourcity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OccurrenceRepository occurrenceRepository;
	
	public void instantiateDatabase() throws ParseException {
		CategoryEntity cat1 = new CategoryEntity(null, "Buraco na Rua");
		CategoryEntity cat2 = new CategoryEntity(null, "Iluminação pública");
		CategoryEntity cat3 = new CategoryEntity(null, "Rodovias");
		CategoryEntity cat4 = new CategoryEntity(null, "Rede de água e Esgoto");
		CategoryEntity cat5 = new CategoryEntity(null, "Coleta de Lixo");
		CategoryEntity cat6 = new CategoryEntity(null, "Transporte público");
		CategoryEntity cat7 = new CategoryEntity(null, "Outros");
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
	
		StatusEntity status1 = new StatusEntity(null, "Criado");
		StatusEntity status2 = new StatusEntity(null, "Finalizado");
		statusRepository.saveAll(Arrays.asList(status1, status2));
		
		UserEntity user1 = new UserEntity(null, "Maria da Silva", "maria@gmail.com", pe.encode("123"), "MariaSilva");
		user1.getPhones().addAll(Arrays.asList("27363323", "93838393"));
		UserEntity user2 = new UserEntity(null, "João da Silva Barros", "joao@gmail.com", pe.encode("123"), "JoaoBarros");
		user2.getPhones().addAll(Arrays.asList("93883321", "34252625"));
		UserEntity user3 = new UserEntity(null, "Lucas Ian Citadini", "l.ian.citadini@gmail.com", pe.encode("123"), "LucasCitadini");
		user3.getPhones().add("047991902030");
		userRepository.saveAll(Arrays.asList(user1, user2, user3));
		
		OccurrenceEntity occurrence1 = new OccurrenceEntity(null, new Date(), -26.8692457, -49.0549484,
				"Rua Leocádia Kasulke - Tribess, Blumenau - SC", "Terreno precisando de polda agora", 
				user3, cat7, status1);
		OccurrenceEntity occurrence2 = new OccurrenceEntity(null, new Date(), -26.9184535, -49.0663338,
				"Av. Pres. Castelo Branco, 875-751 - Centro Blumenau - SC", "Buraco na rua", 
				user1, cat1, status1);
		occurrenceRepository.saveAll(Arrays.asList(occurrence1, occurrence2));
	}
}
