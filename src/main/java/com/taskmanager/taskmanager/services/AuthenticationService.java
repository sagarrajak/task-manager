package com.taskmanager.taskmanager.services;

import com.taskmanager.taskmanager.dto.request.LoginRequestDto;
import com.taskmanager.taskmanager.dto.request.SignupRequestDto;
import com.taskmanager.taskmanager.dto.response.SignupResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationService {
    public SignupResponse signup(SignupRequestDto request) {
        return null;
    }

    public HttpStatusCode login(LoginRequestDto loginRequestDto) {
        return null;
    }
}
