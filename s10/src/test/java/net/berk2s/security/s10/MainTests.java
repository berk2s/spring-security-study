package net.berk2s.security.s10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MainTests {

    @Autowired
    MockMvc mockMvc;

    @DisplayName("Logging With Valid Authority Returns Successfully")
    @Test
    void loggingWithValidAuthorityReturnsSuccessfully() throws Exception {
        mockMvc.perform(get("/hello").with(httpBasic("user1", "12345")))
                .andExpect(status().is2xxSuccessful());
    }

    @DisplayName("Unauthenticated Request Should Return 401")
    @Test
    void unauthenticatedRequestShouldReturn401() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isUnauthorized());
    }

    @DisplayName("Authenticated Request Should Returns Successfully")
    @WithMockUser
    @Test
    void authenticatedRequestShouldReturnSuccessfully() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().is2xxSuccessful());
    }

    @DisplayName("Authenticated Request With Username Returns Successfully")
    @WithMockUser(username = "john")
    @Test
    void authenticatedRequestWithUsernameReturnsSuccessfully() throws Exception {
        mockMvc.perform(get("/me"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Hello, john!"));
    }


    @DisplayName("Authenticated Request With Username Returns Successfully (RequestPostProcessor)")
    @Test
    void authenticatedRequestWithUsernameReturnsSuccessfullyViaRequestPostProcessor() throws Exception {
        mockMvc.perform(get("/me").with(user("john")))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Hello, john!"));
    }

    @DisplayName("Authenticated Request With Username Returns Successfully (WithUserDetails)")
    @WithUserDetails("user1")
    @Test
    void authenticatedRequestWithUsernameReturnsSuccessfullyViaWithUserDetails() throws Exception {
        mockMvc.perform(get("/me"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Hello, user1!"));
    }

    @DisplayName("Authenticated Request With Username Returns Successfully (WithCustomUser)")
    @WithCustomUser(username = "customuser")
    @Test
    void authenticatedRequestWithUsernameReturnsSuccessfullyViaWithCustomUser() throws Exception {
        mockMvc.perform(get("/me"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Hello, customuser!"));
    }
}
