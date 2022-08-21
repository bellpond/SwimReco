package com.swimreco.api.repository;

import com.swimreco.api.domain.User;
import org.springframework.stereotype.Repository;

public interface UserRepository {
    public void set(User user, String collectionPath) throws Exception;
}
