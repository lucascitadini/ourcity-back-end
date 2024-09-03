package com.citadini.ourcity.repositories;

import com.citadini.ourcity.domain.OccurrenceCommentEntity;
import com.citadini.ourcity.domain.OccurrenceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OccurrenceCommentRepository extends JpaRepository<OccurrenceCommentEntity, Long> {
	
	@Transactional(readOnly= true)
	Page<OccurrenceCommentEntity> findByOccurrence(OccurrenceEntity occurrence, Pageable pageRequest);

}