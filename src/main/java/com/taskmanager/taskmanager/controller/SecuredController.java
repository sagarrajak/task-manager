package com.taskmanager.taskmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/")
public class SecuredController {
    @GetMapping("user")
    public String helloUser() {
        return "simple hello user";
    }

    @GetMapping("admin")
    public String helloAdmin() {
        return "Simple hello admin!";
    }
}
