package com.ninabornemann.reqres_in_restclient_post.controller;

import com.ninabornemann.reqres_in_restclient_post.model.UserData;
import com.ninabornemann.reqres_in_restclient_post.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    public List<UserData> getAllUsers() {
        return service.getAllUsers();
    }
}
