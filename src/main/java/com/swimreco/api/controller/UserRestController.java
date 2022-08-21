package com.swimreco.api.controller;

import com.swimreco.api.domain.User;
import com.swimreco.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("services/v1/user")
public class UserRestController {

    private final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private final UserService service;

    public UserRestController(UserService service) {
        this.service = service;
    }

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String add(@RequestBody User user) {
        try {
            service.addUser(user);
            return "{\"result\":\"ok\"}";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "{\"result\":\"error\"}";
        }
    }
}
