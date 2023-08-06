package com.c4c.socialnetwork.userservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;

@Entity(name = "user_follower")
@Getter
@Setter
@NoArgsConstructor
public class UserFollowerEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sourceId",  nullable = false)
    private Long sourceId;
    @Column(name = "targetId",  nullable = false)
    private Long targetId;

    @Column(name = "targetId",  nullable = false)
    private int type;

    @Column(name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdAt;

    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar updatedAt;
}
