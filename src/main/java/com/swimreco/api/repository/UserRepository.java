package com.swimreco.api.repository;

import com.swimreco.api.domain.User;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Repository;

public interface UserRepository {
    public void set(User user, String collectionPath) throws Exception;
    public User get(String userId, String collectionPath)
            throws ExecutionException, InterruptedException, NoSuchElementException;
}
