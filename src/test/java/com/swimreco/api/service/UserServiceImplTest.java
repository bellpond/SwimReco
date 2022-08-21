package com.swimreco.api.service;

import static org.junit.jupiter.api.Assertions.*;
import com.swimreco.api.domain.User;
import com.swimreco.api.repository.UserRepository;
import com.swimreco.api.util.TestUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl target;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUser() throws Exception {
        User user = new User();
        String userCollection = "users";
        Mockito.doNothing().when(userRepository).set(user, userCollection);

        target.addUser(user);

        Mockito.verify(userRepository, Mockito.times(1)).set(user, userCollection);
    }

    @Test
    public void testGetUser() throws Exception {
        User user = TestUser.create();
        String userCollection = "users";
        Mockito.doReturn(user).when(userRepository).get(user.getUserId(), userCollection);

        User result = target.getUser(user.getUserId());

        assertEquals(user, result);
        Mockito.verify(userRepository, Mockito.times(1)).get(user.getUserId(), userCollection);
    }
}