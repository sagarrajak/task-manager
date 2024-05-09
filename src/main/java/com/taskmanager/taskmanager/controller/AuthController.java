package com.taskmanager.taskmanager.controller;


import com.taskmanager.taskmanager.dto.request.LoginRequestDto;
import com.taskmanager.taskmanager.dto.request.SignupRequestDto;
import com.taskmanager.taskmanager.dto.response.SignupResponse;
import com.taskmanager.taskmanager.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/auth/v1")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> singUp(@RequestBody SignupRequestDto request) {
        return new ResponseEntity<>(authenticationService.signup(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRequestDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(authenticationService.login(loginRequestDto));
    }
}
