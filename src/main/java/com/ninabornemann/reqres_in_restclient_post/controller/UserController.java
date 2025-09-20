package com.ninabornemann.reqres_in_restclient_post.controller;

import com.ninabornemann.reqres_in_restclient_post.model.RequestNewUser;
import com.ninabornemann.reqres_in_restclient_post.model.ResponseNewUser;
import com.ninabornemann.reqres_in_restclient_post.model.UserData;
import com.ninabornemann.reqres_in_restclient_post.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserData> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping
    public ResponseNewUser createUser(@RequestBody RequestNewUser newUser) {
        return service.createUser(newUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable String id) {
        service.deleteUsersById(id);
    }
}
