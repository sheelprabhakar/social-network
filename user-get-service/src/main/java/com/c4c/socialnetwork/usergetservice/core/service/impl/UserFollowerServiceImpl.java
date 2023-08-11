package com.c4c.socialnetwork.usergetservice.core.service.impl;

import com.c4c.socialnetwork.usergetservice.core.repository.UserFollowerRepository;
import com.c4c.socialnetwork.usergetservice.core.service.UserFollowerService;
import com.c4c.socialnetwork.userservice.entities.UserFollowerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFollowerServiceImpl implements UserFollowerService {
    private final UserFollowerRepository repository;
    @Autowired
    public UserFollowerServiceImpl(final UserFollowerRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserFollowerEntity findById(long id) {
        return this.repository.findById(id).orElse(null);
    }

}
