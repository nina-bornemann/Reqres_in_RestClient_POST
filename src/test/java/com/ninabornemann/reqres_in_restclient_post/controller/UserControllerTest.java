package com.ninabornemann.reqres_in_restclient_post.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MockRestServiceServer mockServer;

    @Test
    void getAllUsers_shouldReturnAllUsers() throws Exception {
        mockServer.expect(requestTo("https://reqres.in/api/users"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                        {
                          "page": 1,
                          "per_page": 6,
                          "total": 12,
                          "total_pages": 2,
                          "data": [
                            {
                              "id": 1,
                              "email": "george.bluth@reqres.in",
                              "first_name": "George",
                              "last_name": "Bluth",
                              "avatar": "https://reqres.in/img/faces/1-image.jpg"
                            },
                            {
                              "id": 2,
                              "email": "janet.weaver@reqres.in",
                              "first_name": "Janet",
                              "last_name": "Weaver",
                              "avatar": "https://reqres.in/img/faces/2-image.jpg"
                            },
                            {
                              "id": 3,
                              "email": "emma.wong@reqres.in",
                              "first_name": "Emma",
                              "last_name": "Wong",
                              "avatar": "https://reqres.in/img/faces/3-image.jpg"
                            }
                            ]
                        }
                        """, MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        [
                           {
                             "id": "1",
                             "email": "george.bluth@reqres.in",
                             "first_name": "George",
                             "last_name": "Bluth",
                             "avatar": "https://reqres.in/img/faces/1-image.jpg"
                           },
                           {
                             "id": "2",
                             "email": "janet.weaver@reqres.in",
                             "first_name": "Janet",
                             "last_name": "Weaver",
                             "avatar": "https://reqres.in/img/faces/2-image.jpg"
                           },
                           {
                             "id": "3",
                             "email": "emma.wong@reqres.in",
                             "first_name": "Emma",
                             "last_name": "Wong",
                             "avatar": "https://reqres.in/img/faces/3-image.jpg"
                           }
                        ]
                        
                        """));
    }

    @Test
    void createUser_shouldAddAnewUser() throws Exception {
        mockServer.expect(requestTo("https://reqres.in/api/users"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess("""
                        {
                            "id": "123",
                            "name": "Rebecca",
                            "job": "Photographer",
                            "createdAt": "2025-09-19T10:15:30.000Z"
                        }
                        """, MediaType.APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "Rebecca",
                            "job": "Photographer"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                           "id": "123",
                           "name": "Rebecca",
                           "job": "Photographer",
                           "createdAt": "2025-09-19T10:15:30.000Z"
                        }
                        """
                ))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").isNotEmpty());
    }

    @Test
    void deleteUserByID_shouldDeleteUser_WhenGivenId() throws Exception {
        mockServer.expect(requestTo("https://reqres.in/api/users/123"))
                .andExpect(method(HttpMethod.DELETE))
                .andRespond(withSuccess());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/123"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}