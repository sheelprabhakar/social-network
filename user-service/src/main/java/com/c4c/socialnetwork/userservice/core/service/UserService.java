package com.c4c.socialnetwork.userservice.core.service;

import com.c4c.socialnetwork.userservice.entities.UserEntity;

public interface UserService {
    UserEntity save(UserEntity userEntity);

    UserEntity findById(long id);
}
