package com.swimreco.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swimreco.api.domain.User;
import com.swimreco.api.service.UserService;
import com.swimreco.api.util.TestUser;
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

    private static final String API_PREFIX = "/services/v1/user";

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
        User user = TestUser.create();
        ArgumentMatcher<User> matcher = argument -> {
            assertEquals(user.getUserId(), argument.getUserId());
            return true;
        };
        Mockito.doNothing().when(service).addUser(Mockito.argThat(matcher));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(API_PREFIX)
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        Response result = mapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);

        assertEquals("ok", result.getResult());
        Mockito.verify(service, Mockito.times(1)).addUser(Mockito.argThat(matcher));
    }

    @Test
    void testGetWithSuccess() throws Exception {
        User user = TestUser.create();
        Mockito.doReturn(user).when(service).getUser(user.getUserId());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(API_PREFIX + "/" + user.getUserId()))
                .andExpect(status().isOk())
                .andReturn();

        User result = mapper.readValue(mvcResult.getResponse().getContentAsString(), User.class);

        assertEquals(user.getUserId(), result.getUserId());
        assertEquals(user.getFirstName(), result.getFirstName());
        assertEquals(user.getLastName(), result.getLastName());
        Mockito.verify(service, Mockito.times(1)).getUser(user.getUserId());
    }
}

class Response {
    private String result;
    public String getResult() {
        return result;
    }
}