package com.c4c.socialnetwork.userservice.core.service.impl;

import com.c4c.socialnetwork.userservice.core.repository.UserFollowerRepository;
import com.c4c.socialnetwork.userservice.core.service.UserFollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFollowerServiceImpl implements UserFollowerService {
    private final UserFollowerRepository repository;


    @Autowired
    public UserFollowerServiceImpl(final UserFollowerRepository repository) {
        this.repository = repository;
    }
}
