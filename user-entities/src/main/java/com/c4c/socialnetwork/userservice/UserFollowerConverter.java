package com.c4c.socialnetwork.userservice;

import com.c4c.socialnetwork.userservice.entities.UserFollowerEntity;
import com.c4c.socialnetwork.userservice.resources.UserFollowerResource;

public class UserFollowerConverter {
    private UserFollowerConverter(){

    }

    public static UserFollowerResource fromUserFollowerEntity(UserFollowerEntity entity){
        UserFollowerResource resource = new UserFollowerResource();
        resource.setId(entity.getId());
        resource.setType(entity.getType());
        resource.setCreatedAt(entity.getCreatedAt());
        resource.setUpdatedAt(entity.getUpdatedAt());
        resource.setTargetId(entity.getTargetId());
        resource.setSourceId(entity.getSourceId());
        return resource;
    }

    public static UserFollowerEntity fromUserFollowerResource(UserFollowerResource resource){
        UserFollowerEntity entity = new UserFollowerEntity();
        entity.setId(resource.getId());
        entity.setType(resource.getType());
        entity.setCreatedAt(resource.getCreatedAt());
        entity.setUpdatedAt(resource.getUpdatedAt());
        entity.setTargetId(resource.getTargetId());
        entity.setSourceId(resource.getSourceId());
        return entity;
    }
}
