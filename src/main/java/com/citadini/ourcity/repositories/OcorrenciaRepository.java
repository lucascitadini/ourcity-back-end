package com.citadini.ourcity.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.citadini.ourcity.domain.Ocorrencia;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {

	@Transactional(readOnly= true)
	Page<Ocorrencia> findDistinctByEnderecoCompletoContainingIgnoreCase(String enderecoCompleto, Pageable pageable);

}
