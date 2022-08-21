package com.swimreco.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swimreco.api.domain.User;
import com.swimreco.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("services/v1/user")
public class UserRestController {

    private final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private final UserService service;

    private final ObjectMapper mapper;

    public UserRestController(UserService service) {
        this.service = service;
        this.mapper = new ObjectMapper();
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

    @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> get(@PathVariable String userId) {
        User user = null;
        try {
            user = service.getUser(userId);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
