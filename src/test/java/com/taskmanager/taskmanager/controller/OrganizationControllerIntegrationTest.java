package com.taskmanager.taskmanager.controller;

import com.taskmanager.taskmanager.dto.response.JwtAuthenticationResponse;
import com.taskmanager.taskmanager.dto.response.organization.CreateOrganizationResponseDto;
import com.taskmanager.taskmanager.dto.response.organization.OrganizationDetailsResponseDto;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrganizationControllerIntegrationTest {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";


    @Autowired
    TestRestTemplate restTemplate;

    private  String token;

    private final Logger log = LoggerFactory.getLogger(getClass());

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
    @Order(1)
    void testCreateOrganization_whenValidDetailsProvided_createNewOrganization() throws JSONException {
        JSONObject organizationPayload = new JSONObject();
        organizationPayload.put("fullName", "This is ss");
        organizationPayload.put("uniqueName", "sagar123");
        organizationPayload.put("description", "This is simple organization controller");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(this.token);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(organizationPayload.toString(), headers);

        var response = restTemplate.postForEntity("/api/organization",
                requestEntity,
                CreateOrganizationResponseDto.class
        );
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(response.getBody().getName(), "This is ss");
        Assertions.assertEquals(response.getBody().getUniqueName(), "sagar123");
        Assertions.assertEquals(response.getBody().getDescription(), "This is simple organization controller");
    }


    @Test
    @DisplayName("Test GET /api/organization/{uniqueId} get organization details")
    @Order(2)
    void testGetOrganization_whenValidOrganizationDetailsProvided_willReturnSavedOrganizationDetails() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(this.token);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        var response = restTemplate.exchange("/api/organization/sagar123", HttpMethod.GET, requestEntity, OrganizationDetailsResponseDto.class);
        log.debug(response.getBody().toString());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());


//        Assertions.assertNotNull(response.getBody());
//        Assertions.assertEquals(response.getBody().getName(), "This is ss");
//        Assertions.assertEquals(response.getBody().getUniqueName(), "sagar123");
//        Assertions.assertEquals(response.getBody().getDescription(), "This is simple organization controller");
    }


}