package com.swimreco.api.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import com.swimreco.api.domain.User;
import com.swimreco.api.util.TestUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRepositoryImplTest {

    private UserRepositoryImpl target;

    @BeforeEach
    void before() {
        this.target = new UserRepositoryImpl();
    }

    @Test
    void testSet() throws Exception {
        User testUser = TestUser.create();

        target.set(testUser, "test-users");
    }

    @Test
    void testGetWithSuccess() throws Exception {
        User testUser = TestUser.create();
        String testUserId = testUser.getUserId();

        User user = target.get(testUserId, "test-users");

        assertNotNull(user);
        assertEquals(testUserId, user.getUserId());
        assertEquals(testUser.getManagerFlag(), user.getManagerFlag());
    }

    @Test
    void testGetWithInvalidUserId() throws Exception {
        String userId = "abc@gmail.com";  // 存在しないuserId

        User user = target.get(userId, "test-users");

        assertNull(user);
    }

}