package com.citadini.ourcity.repositories;

import com.citadini.ourcity.domain.OccurrenceSupportEntity;
import com.citadini.ourcity.domain.OccurrenceSupportPK;
import com.citadini.ourcity.domain.OccurrenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OccurrenceSupportRepository extends JpaRepository<OccurrenceSupportEntity, OccurrenceSupportPK> {

	@Transactional(readOnly= true)
	List<OccurrenceSupportEntity> findByIdOccurrence(OccurrenceEntity occurrence);

}