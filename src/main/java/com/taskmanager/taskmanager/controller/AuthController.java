package com.taskmanager.taskmanager.controller;


import com.taskmanager.taskmanager.dto.request.LoginRequestDto;
import com.taskmanager.taskmanager.dto.request.SignupRequestDto;
import com.taskmanager.taskmanager.dto.response.JwtAuthenticationResponse;
import com.taskmanager.taskmanager.dto.response.SignupResponse;
import com.taskmanager.taskmanager.services.impl.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth/")
@AllArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> singUp(@RequestBody @Valid SignupRequestDto request) {
        return new ResponseEntity<>(authenticationService.signup(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        ResponseEntity<JwtAuthenticationResponse> jwtAuthenticationResponseResponseEntity = new ResponseEntity<>(authenticationService.login(loginRequestDto), HttpStatus.OK);
        return jwtAuthenticationResponseResponseEntity;
    }
}
