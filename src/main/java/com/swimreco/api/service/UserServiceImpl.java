package com.swimreco.api.service;

import com.swimreco.api.domain.User;
import com.swimreco.api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private static final String USER_COLLECTION = "users";

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addUser(User user) throws Exception {
        repository.set(user, USER_COLLECTION);
    }
}
