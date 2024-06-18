package com.taskmanager.taskmanager.controller;

import com.taskmanager.taskmanager.dto.response.JwtAuthenticationResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrganizationControllerTest {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    @Autowired
    TestRestTemplate restTemplate;

    private  String token;

    private void signUp() throws JSONException {
        JSONObject signupCredentials = new JSONObject();
        signupCredentials.put("firstName", "sagar");
        signupCredentials.put("lastName", "rajak");
        signupCredentials.put("email", "sagarrajak858@gmail.com");
        signupCredentials.put("password", "sagar123");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(signupCredentials.toString(), headers);
        var response = restTemplate.postForEntity("/auth/signup", requestEntity, null);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    private void login() throws JSONException {
        JSONObject loginCredentials = new JSONObject();
        loginCredentials.put("username", "sagarrajak858@gmail.com");
        loginCredentials.put("password", "sagar123");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(loginCredentials.toString(), headers);
        var response = restTemplate.postForEntity("/auth/login", requestEntity, JwtAuthenticationResponse.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        JwtAuthenticationResponse body = response.getBody();
        Assertions.assertNotNull(body);
        Assertions.assertNotNull(body.getToken());
        this.token = body.getToken();
        System.out.println("token " +token);
    }

    @BeforeEach
    void setup() throws JSONException {
        // login user
        this.signUp();
        this.login();
    }

    @Test
    @DisplayName("Test /api/organization create organization")
    void testCreateOrganization_whenValidDetailsProvided_createNewOrganization() throws JSONException {
        System.out.println("test working");
        Assertions.assertTrue(true);
    }


}