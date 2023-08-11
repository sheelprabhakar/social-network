package com.c4c.socialnetwork.usergetservice.rest;

import com.c4c.socialnetwork.userservice.UserConverter;
import com.c4c.socialnetwork.usergetservice.core.service.UserService;
import com.c4c.socialnetwork.userservice.entities.UserEntity;
import com.c4c.socialnetwork.userservice.resources.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController( )
@RequestMapping(UserController.BASE_URL)
public class UserController {
    private final UserService userService;
    static final String BASE_URL = "/user-getservice/api/v1";
    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity< UserResource> add(@RequestBody UserResource userResource){
        UserEntity userEntity = this.userService.save(UserConverter.fromUserResource(userResource));
        return ResponseEntity.created(URI.create(BASE_URL+"users/"+userEntity.getId()))
                .body(UserConverter.fromUserEntity(userEntity));
    }

}
