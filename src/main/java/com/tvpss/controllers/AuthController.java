package com.tvpss.controllers;

import com.tvpss.models.User;
import com.tvpss.repositories.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collections;

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
                        Model model) {
        User user = userRepository.findByUsername(username);

        // Validate user credentials
        if (user != null && user.getPassword().equals(password)) {
            // Create an Authentication object with roles
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            null,
                            Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));

            // Set the Authentication object in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authToken);

            return "redirect:/dashboard";
        }

        // Invalid credentials
        model.addAttribute("error", "Invalid credentials. Please try again.");
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Get the authenticated user's details
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities()
                .stream()
                .findFirst()
                .map(Object::toString)
                .orElse("UNKNOWN");

        model.addAttribute("username", username);
        model.addAttribute("role", role);
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
