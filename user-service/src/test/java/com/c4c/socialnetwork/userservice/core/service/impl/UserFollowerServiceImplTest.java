package com.c4c.socialnetwork.userservice.core.service.impl;

import com.c4c.socialnetwork.userservice.core.repository.UserFollowerRepository;
import com.c4c.socialnetwork.userservice.core.repository.UserRepository;
import com.c4c.socialnetwork.userservice.entities.UserEntity;
import com.c4c.socialnetwork.userservice.entities.UserFollowerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Calendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserFollowerServiceImplTest {
    public static final String MOBILE = "9898989898";
    @InjectMocks
    UserFollowerServiceImpl userFollowerService;
    @Mock
    UserFollowerRepository repository;

    @BeforeEach
    void setUp() {
        UserFollowerEntity entity = new UserFollowerEntity();
        entity.setId(1L);
        entity.setSourceId(1L);
        entity.setTargetId(2L);
        entity.setCreatedAt(Calendar.getInstance());

        Mockito.when(repository.save(ArgumentMatchers.any()))
                .thenReturn(entity);

       Mockito.when(repository.findById(1L))
                .thenReturn(Optional.of(entity));
       Mockito.when(repository.findById(ArgumentMatchers.eq(100L)))
                .thenReturn(Optional.empty());

    }
    @Test
    public void test_save_ok(){
        UserFollowerEntity userFollowerEntity = this.userFollowerService.save(new UserFollowerEntity());
        assertEquals(userFollowerEntity.getId(),1);
        assertEquals(userFollowerEntity.getSourceId(),1L);
        assertEquals(userFollowerEntity.getTargetId(),2L);
        assertNotNull(userFollowerEntity.getCreatedAt());
    }

    @Test
    public void test_get_ok(){
        UserFollowerEntity userFollowerEntity = this.userFollowerService.findById(1L);
        assertEquals(userFollowerEntity.getId(),1L);
        assertEquals(userFollowerEntity.getSourceId(),1L);
        assertEquals(userFollowerEntity.getTargetId(),2L);
        assertNotNull(userFollowerEntity.getCreatedAt());

        userFollowerEntity = this.userFollowerService.findById(100L);
        assertNull(userFollowerEntity);
    }

    @Test
    public void test_update_ok(){
        UserFollowerEntity userFollowerEntity = this.userFollowerService.update(new UserFollowerEntity());
        assertEquals(userFollowerEntity.getId(),1);
        assertEquals(userFollowerEntity.getSourceId(),1L);
        assertEquals(userFollowerEntity.getTargetId(),2L);
        assertNotNull(userFollowerEntity.getCreatedAt());
    }
}
