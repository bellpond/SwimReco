package com.swimreco.api.util;

import com.swimreco.api.domain.Sex;
import com.swimreco.api.domain.Style;
import com.swimreco.api.domain.User;

public class TestUser {
    public static User create() {
        User user = new User();
        user.setUserId("test@gmail.com");
        user.setFirstName("test");
        user.setLastName("user");
        user.setClassYear(100);
        user.setManagerFlag(false);
        user.setSex(Sex.Male);
        user.setS1(Style.FreeStyle);
        return user;
    }
}
