package com.citadini.ourcity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.citadini.ourcity.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Transactional(readOnly= true)
	Usuario findByEmail(String email);

	@Transactional(readOnly= true)
	Usuario findByUserName(String userName);

}
