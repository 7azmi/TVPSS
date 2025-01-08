package com.tvpss.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String loginPage() {
        return "login"; // Renders login.html
    }

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard"; // Renders dashboard.html
    }
}
