package com.citadini.ourcity.repositories;

import com.citadini.ourcity.domain.OccurrenceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OccurrenceRepository extends JpaRepository<OccurrenceEntity, Long> {

	@Transactional(readOnly= true)
	Page<OccurrenceEntity> findDistinctByFullAddressContainingIgnoreCase(String fullAddress, Pageable pageable);

}
