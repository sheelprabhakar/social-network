package com.c4c.socialnetwork.userservice.core.repository;

import com.c4c.socialnetwork.userservice.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
