package com.c4c.socialnetwork.userservice.rest;


import com.c4c.socialnetwork.userservice.resources.UserResource;
import com.c4c.socialnetwork.userservice.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Calendar;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserControllerTest extends AbstractIntegrationTest{
    public static final String MOBILE = "9898989898";
    private final String BASE_URL="/user-service/api/v1/";
    @Test
    public void test_add_user_ok() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post(BASE_URL+"users")
                        .content(TestUtils.convertObjectToJsonString( this.getNewUser()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mobile").value(MOBILE));
    }

    private UserResource getNewUser(){
        UserResource userResource = new UserResource();
        userResource.setEmail("ssp@c4c.com");
        userResource.setIntro("");
        userResource.setMobile(MOBILE);
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
