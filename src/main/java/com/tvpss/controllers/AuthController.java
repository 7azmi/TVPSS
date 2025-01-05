package com.tvpss.controllers;

import com.tvpss.DummyDatabase;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password) {
        for (Map<String, Object> user : DummyDatabase.users) {
            if (user.get("username").equals(username) && user.get("password").equals(password)) {
                return Map.of(
                        "status", "success",
                        "message", "Login successful",
                        "role", user.get("role")
                );
            }
        }
        return Map.of("status", "error", "message", "Invalid credentials");
    }
}
