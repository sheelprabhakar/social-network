package com.c4c.socialnetwork.usergetservice.core.repository;

import com.c4c.socialnetwork.userservice.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
