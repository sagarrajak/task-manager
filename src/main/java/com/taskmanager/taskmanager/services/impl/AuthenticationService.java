package com.taskmanager.taskmanager.services.impl;

import com.taskmanager.taskmanager.config.JwtService;
import com.taskmanager.taskmanager.dto.request.LoginRequestDto;
import com.taskmanager.taskmanager.dto.request.SignupRequestDto;
import com.taskmanager.taskmanager.dto.response.JwtAuthenticationResponse;
import com.taskmanager.taskmanager.dto.response.SignupResponse;
import com.taskmanager.taskmanager.entity.UserEntity;
import com.taskmanager.taskmanager.exception.authentication.LoginException;
import com.taskmanager.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public SignupResponse signup(SignupRequestDto request) {
        UserEntity user = UserEntity.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .disabled(false)
                .build();
        UserEntity savedUser = userRepository.save(user);
        return SignupResponse.builder().user(savedUser).message("Sign up successful").build();
    }

    public JwtAuthenticationResponse login(LoginRequestDto dto) {
        var user = this.userRepository.findOneByEmailIgnoreCase(dto.getUsername()).orElseThrow(() -> new LoginException("Invalid email or password!"));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user, dto.getPassword()));
        var jwt = jwtService.generateToken(user.getUsername());
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    public UserEntity getCurrentLoginUser() {
        return  (UserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
