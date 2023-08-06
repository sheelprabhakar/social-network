package com.c4c.socialnetwork.userservice.rest;

import com.c4c.socialnetwork.userservice.UserConverter;
import com.c4c.socialnetwork.userservice.core.service.UserService;
import com.c4c.socialnetwork.userservice.entities.UserEntity;
import com.c4c.socialnetwork.userservice.resources.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.c4c.socialnetwork.userservice.rest.UserController.BASE_URL;

@RestController( )
@RequestMapping(BASE_URL)
public class UserController {
    private final UserService userService;
    static final String BASE_URL = "/user-service/api/v1";
    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity< UserResource> add(@RequestBody UserResource userResource){
        UserEntity userEntity = this.userService.save(UserConverter.fromUserResource(userResource));
        return ResponseEntity.created(URI.create(BASE_URL+"users/"+userEntity.getId()))
                .body(UserConverter.fromUserEntity(userEntity));
    }

    @GetMapping("/users")
    public ResponseEntity< String> hello(){
        return ResponseEntity.ok("hello");
    }

}
