
package com.c4c.socialnetwork.userservice.rest;

import com.c4c.socialnetwork.userservice.UserFollowerConverter;
import com.c4c.socialnetwork.userservice.core.service.UserFollowerService;
import com.c4c.socialnetwork.userservice.entities.UserFollowerEntity;
import com.c4c.socialnetwork.userservice.resources.UserFollowerResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.c4c.socialnetwork.userservice.rest.UserFollowerController.BASE_URL;


@RestController( )
@RequestMapping(BASE_URL)
public class UserFollowerController {
    private final UserFollowerService userFollowerService;
    static final String BASE_URL = "/user-follower-service/api/v1";
    @Autowired
    public UserFollowerController(final UserFollowerService userFollowerService) {
        this.userFollowerService = userFollowerService;
    }

    @PostMapping("/follow")
    public ResponseEntity< UserFollowerResource> add(@RequestBody UserFollowerResource userFollowerResource){
        UserFollowerEntity userFollowerEntity = this.userFollowerService
                .save(UserFollowerConverter.fromUserFollowerResource(userFollowerResource));
        return ResponseEntity.created(URI.create(BASE_URL+"follow/"+userFollowerEntity.getId()))
                .body(UserFollowerConverter.fromUserFollowerEntity(userFollowerEntity));
    }

    @PutMapping("/follow")
    public ResponseEntity<UserFollowerResource> update(@RequestBody UserFollowerResource userFollowerResource){
        UserFollowerEntity userFollowerEntity = this.userFollowerService
                .update(UserFollowerConverter.fromUserFollowerResource(userFollowerResource));
        return ResponseEntity.ok(UserFollowerConverter.fromUserFollowerEntity(userFollowerEntity));
    }

}
