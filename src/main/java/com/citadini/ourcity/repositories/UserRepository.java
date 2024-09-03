package com.citadini.ourcity.repositories;

import com.citadini.ourcity.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	@Transactional(readOnly= true)
    UserEntity findByEmail(String email);

	@Transactional(readOnly= true)
    UserEntity findByUserName(String userName);

}
