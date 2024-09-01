package com.citadini.ourcity.repositories;

import com.citadini.ourcity.domain.ComentarioOcorrencia;
import com.citadini.ourcity.domain.Ocorrencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ComentarioOcorrenciaRepository extends JpaRepository<ComentarioOcorrencia, Long> {
	
	@Transactional(readOnly= true)
	Page<ComentarioOcorrencia> findByOcorrencia(Ocorrencia ocorrencia, Pageable pageRequest);

}