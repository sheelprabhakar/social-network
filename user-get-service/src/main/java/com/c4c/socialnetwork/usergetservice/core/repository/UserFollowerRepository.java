package com.c4c.socialnetwork.usergetservice.core.repository;

import com.c4c.socialnetwork.userservice.entities.UserFollowerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface UserFollowerRepository extends CrudRepository<UserFollowerEntity, Long> {
    List<UserFollowerEntity> findBySourceId(Long sourceId);
}
