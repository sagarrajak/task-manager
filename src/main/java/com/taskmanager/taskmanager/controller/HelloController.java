package com.taskmanager.taskmanager.controller;

import com.taskmanager.taskmanager.entity.UserEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("user")
    public String helloUser() {
        UserEntity principal = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "simple hello user " + principal.toString();
    }

    @GetMapping("admin")
    public String helloAdmin() {
        return "Simple hello admin!";
    }
}

