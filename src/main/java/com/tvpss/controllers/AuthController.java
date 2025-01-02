package com.tvpss.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Simulate login logic (to be replaced with JWT logic)
        return "Logged in successfully!";
    }
}
