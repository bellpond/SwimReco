package com.swimreco.api.repository;

import static org.junit.jupiter.api.Assertions.*;
import com.google.cloud.firestore.DocumentReference;
import com.swimreco.api.domain.Sex;
import com.swimreco.api.domain.Style;
import com.swimreco.api.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRepositoryImplTest {

    private UserRepositoryImpl target;

    @BeforeEach
    void before() {
        this.target = new UserRepositoryImpl();
    }

    @Test
    void testSet() {
        User testUser = new User();
        testUser.setUserId("test@gmail.com");
        testUser.setFirstName("test");
        testUser.setLastName("user");
        testUser.setClassYear(100);
        testUser.setManagerFlag(false);
        testUser.setSex(Sex.Male);
        testUser.setS1(Style.FreeStyle);

        try {
            target.set(testUser, "test-users");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}