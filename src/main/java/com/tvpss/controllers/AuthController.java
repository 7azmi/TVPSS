package com.tvpss.controllers;

import com.tvpss.DummyDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession; // Import HttpSession
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) { // Inject HttpSession as a parameter
        System.out.println("Login request received: " + username);

        for (Map<String, Object> user : DummyDatabase.users) {
            if (user.get("username").equals(username) && user.get("password").equals(password)) {
                System.out.println("Login successful for user: " + username);
                model.addAttribute("message", "Login successful! Welcome, " + username + ".");

                // Set user role in the session
                session.setAttribute("role", user.get("role"));
                session.setAttribute("username", username); // Optionally store the username

                return "dashboard"; // Redirect to the dashboard page
            }
        }

        System.out.println("Invalid credentials for user: " + username);
        model.addAttribute("error", "Invalid credentials. Please try again.");
        return "login"; // Redirect back to login page with error message
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String role,
                           Model model) {
        System.out.println("Registration request received: " + username + " with role: " + role);

        if (!role.equals("SCHOOL_REP") && !role.equals("STUDENT")) {
            model.addAttribute("error", "Invalid role. Only School Representatives and Students can register.");
            return "login"; // Redirect back to login page with error message
        }

        // Check if the username already exists
        for (Map<String, Object> user : DummyDatabase.users) {
            if (user.get("username").equals(username)) {
                System.out.println("Registration failed: Username already exists");
                model.addAttribute("error", "Username already exists. Please choose a different username.");
                return "login";
            }
        }

        // Add new user to the dummy database
        int newId = DummyDatabase.users.size() + 1;
        DummyDatabase.users.add(Map.of(
                "id", newId,
                "username", username,
                "password", password,
                "role", role
        ));

        System.out.println("New user added: " + username + " with role: " + role);
        model.addAttribute("message", "User registered successfully! Please log in.");
        return "login"; // Redirect back to login page with success message
    }
}
