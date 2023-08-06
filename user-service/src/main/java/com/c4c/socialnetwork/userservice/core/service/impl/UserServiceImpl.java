package com.c4c.socialnetwork.userservice.core.service.impl;

import com.c4c.socialnetwork.userservice.core.repository.UserRepository;
import com.c4c.socialnetwork.userservice.core.service.UserService;
import com.c4c.socialnetwork.userservice.entities.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity save(UserEntity userEntity){
        return this.userRepository.save(userEntity);
    }

    @Override
    public UserEntity findById(long id) {
        return this.userRepository.findById(id).orElse(null);
    }
}
