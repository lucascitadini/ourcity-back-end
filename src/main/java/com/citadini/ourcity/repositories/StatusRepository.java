package com.citadini.ourcity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citadini.ourcity.domain.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

}