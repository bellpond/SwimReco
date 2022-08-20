package com.swimreco.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swimreco.api.domain.User;
import com.swimreco.api.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class UserRestControllerTest {

    @Mock
    private UserService service;

    @InjectMocks
    private UserRestController target;

    private MockMvc mockMvc;

    private ObjectMapper mapper;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(target).build();
        mapper = new ObjectMapper();
    }

    @Test
    void testAddWithSuccess() throws Exception {
        User user = new User();
        String userId = "test@gmail.com";
        user.setUserId(userId);
        ArgumentMatcher<User> matcher = argument -> {
            assertEquals(userId, argument.getUserId());
            return true;
        };
        Mockito.doNothing().when(service).addUser(Mockito.argThat(matcher));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/services/v1/user")
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        Response result = mapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);

        assertEquals("ok", result.getResult());
        Mockito.verify(service, Mockito.times(1)).addUser(Mockito.argThat(matcher));
    }
}

class Response {
    private String result;

    public String getResult() {
        return result;
    }
}