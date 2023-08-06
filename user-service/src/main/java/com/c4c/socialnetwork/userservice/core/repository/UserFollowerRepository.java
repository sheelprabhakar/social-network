package com.c4c.socialnetwork.userservice.core.repository;

import com.c4c.socialnetwork.userservice.entities.UserFollowerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFollowerRepository extends CrudRepository<UserFollowerEntity, Long> {
}
