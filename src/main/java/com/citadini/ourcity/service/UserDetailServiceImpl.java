package com.citadini.ourcity.service;

import com.citadini.ourcity.domain.UserEntity;
import com.citadini.ourcity.repositories.UserRepository;
import com.citadini.ourcity.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(user.getId(), user.getEmail(), user.getPassword(), user.getUserName());
	}

}
