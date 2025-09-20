package com.ninabornemann.reqres_in_restclient_post.service;


import com.ninabornemann.reqres_in_restclient_post.model.ReqResUserResponse;
import com.ninabornemann.reqres_in_restclient_post.model.RequestNewUser;
import com.ninabornemann.reqres_in_restclient_post.model.ResponseNewUser;
import com.ninabornemann.reqres_in_restclient_post.model.UserData;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class UserService {

    private final RestClient restClient;

    public UserService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("https://reqres.in/api")
                .build();
    }

    public List<UserData> getAllUsers() {
        return restClient.get()
                .uri("/users")
                .header("x-api-key", "reqres-free-v1")
                .retrieve()
                .body(ReqResUserResponse.class)
                .data();
    }

    public ResponseNewUser createUser(RequestNewUser newUser) {
        return restClient.post()
                .uri("/users")
                .header("x-api-key", "reqres-free-v1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(newUser)
                .retrieve()
                .body(ResponseNewUser.class);
    }
}
