package com.c4c.socialnetwork.userservice.core.service;

import com.c4c.socialnetwork.userservice.entities.UserFollowerEntity;

public interface UserFollowerService {
    UserFollowerEntity save(UserFollowerEntity userFollowerEntity);

    UserFollowerEntity update(UserFollowerEntity userFollowerEntity);

    UserFollowerEntity findById(long l);

    void delete(UserFollowerEntity userFollowerEntity);
}
