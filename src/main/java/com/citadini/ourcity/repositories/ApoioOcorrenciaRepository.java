package com.citadini.ourcity.repositories;

import com.citadini.ourcity.domain.ApoioOcorrencia;
import com.citadini.ourcity.domain.ApoioOcorrenciaPK;
import com.citadini.ourcity.domain.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ApoioOcorrenciaRepository extends JpaRepository<ApoioOcorrencia, ApoioOcorrenciaPK> {

	@Transactional(readOnly= true)
	List<ApoioOcorrencia> findByIdOcorrencia(Ocorrencia ocorrencia);

}