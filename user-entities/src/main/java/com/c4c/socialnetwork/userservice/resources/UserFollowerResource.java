package com.c4c.socialnetwork.userservice.resources;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@NoArgsConstructor
public class UserFollowerResource {
    private Long id;

    private Long sourceId;

    private Long targetId;

    private int type;

    private Calendar createdAt;

    private Calendar updatedAt;

}
