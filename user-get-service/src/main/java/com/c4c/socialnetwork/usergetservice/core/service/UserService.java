package com.c4c.socialnetwork.usergetservice.core.service;

import com.c4c.socialnetwork.userservice.entities.UserEntity;

public interface UserService {
    UserEntity findById(long id);
}
