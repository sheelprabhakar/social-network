package com.c4c.socialnetwork.userservice.rest;


import com.c4c.socialnetwork.userservice.entities.UserFollowerEntity;
import com.c4c.socialnetwork.userservice.resources.UserFollowerResource;
import com.c4c.socialnetwork.userservice.resources.UserResource;
import com.c4c.socialnetwork.userservice.utils.TestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Calendar;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserFollowerControllerTest extends AbstractIntegrationTest{
    public static final String MOBILE = "9898989898";
    private final String BASE_URL="/user-follower-service/api/v1/";
    private final String USER_BASE_URL="/user-service/api/v1/";
    private long[] intiUser(int start, int end) throws Exception {
        long [] id = new long[2];
        int idx=0;
        for(int i =start; i <= end; ++i) {
            MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                            .post(USER_BASE_URL + "users")
                            .content(TestUtils.convertObjectToJsonString(this.getNewUser(i)))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andReturn();
            UserResource userResource = TestUtils
                    .convertJsonStringToObject(mvcResult.getResponse()
                            .getContentAsString(), UserResource.class);
            id[idx++] = userResource.getId();
        }
        return id;
    }
    @Test
    public void test_add_user_follower_ok() throws Exception {
        long [] ids = this.intiUser(0,1);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post(BASE_URL+"follow")
                        .content(TestUtils.convertObjectToJsonString( this.getNewFollower(ids)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.targetId").value(ids[1]));
    }

    @Test
    public void test_update_user_follower_ok() throws Exception {
        long [] ids = this.intiUser(2,3);

        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                        .post(BASE_URL + "follow")
                        .content(TestUtils.convertObjectToJsonString(this.getNewFollower(ids)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.targetId").value(ids[1]))
                .andReturn();

        UserFollowerResource userFollowerResource = TestUtils
                .convertJsonStringToObject(mvcResult.getResponse()
                        .getContentAsString(), UserFollowerResource.class);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .put(BASE_URL + "follow")
                        .content(TestUtils.convertObjectToJsonString(userFollowerResource))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.targetId").value(ids[1]));
    }

    @Test
    public void test_delete_user_follower_ok() throws Exception {
        long [] ids = this.intiUser(4,5);

        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                        .post(BASE_URL + "follow")
                        .content(TestUtils.convertObjectToJsonString(this.getNewFollower(ids)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.targetId").value(ids[1]))
                .andReturn();

        UserFollowerResource userFollowerResource = TestUtils
                .convertJsonStringToObject(mvcResult.getResponse()
                        .getContentAsString(), UserFollowerResource.class);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete(BASE_URL + "follow")
                        .content(TestUtils.convertObjectToJsonString(userFollowerResource))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    private UserFollowerResource getNewFollower(long[]ids){
        UserFollowerResource resource = new UserFollowerResource();
        resource.setSourceId(ids[0]);
        resource.setTargetId(ids[1]);
        resource.setCreatedAt(Calendar.getInstance());
        return resource;
    }

    private UserResource getNewUser(int i){
        UserResource userResource = new UserResource();
        userResource.setEmail(""+i+"ssp@c4c.com");
        userResource.setIntro("");
        userResource.setMobile(MOBILE+i);
        userResource.setProfile("");
        userResource.setLastLogin(null);
        userResource.setRegisteredAt(Calendar.getInstance());
        userResource.setLastName("prabhakar");
        userResource.setMiddleName("s");
        userResource.setPasswordHash("");
        userResource.setFirstName("sheel");
        return userResource;
    }
}
