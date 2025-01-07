package com.tvpss.controllers;

import com.tvpss.models.User;
import com.tvpss.repositories.UserRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
// import jakarta.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {
        if (session == null) {
            throw new IllegalStateException("HttpSession is not properly injected.");
        }
        System.out.println("Session ID: " + session.getId());
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user_id", user.getId());
            session.setAttribute("role", user.getRole());
            session.setAttribute("username", user.getUsername());
            return "redirect:/dashboard";
        }
        model.addAttribute("error", "Invalid credentials. Please try again.");
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("user_id") == null) {
            model.addAttribute("error", "Session expired. Please log in again.");
            return "login";
        }

        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("role", session.getAttribute("role"));
        return "dashboard";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String role,
                           Model model) {
        if (!role.equals("SCHOOL_REP") && !role.equals("STUDENT")) {
            model.addAttribute("error", "Invalid role. Only School Representatives and Students can register.");
            return "login";
        }

        if (userRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists. Please choose a different username.");
            return "login";
        }

        userRepository.save(new User(username, password, role));
        model.addAttribute("message", "User registered successfully! Please log in.");
        return "login";
    }
}
