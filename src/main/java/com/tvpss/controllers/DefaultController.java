package com.tvpss.controllers;

import com.tvpss.models.Content;
import com.tvpss.repositories.ContentRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
public class DefaultController {
    private final ContentRepository contentRepository;

    public DefaultController(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @GetMapping("/")
    public String loginPage() {
        return "login"; // Renders login.html
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("user_id") == null) {
            model.addAttribute("error", "Session expired. Please log in again.");
            return "login";
        }


        List<Content> contentList = contentRepository.findApprovedContent();

        // Process the content list to extract YouTube IDs
        for (Content content : contentList) {
            String youtubeId = extractYouTubeId(content.getYoutubeLink());
            content.setYoutubeId(youtubeId);
            System.out.println("Extracted YouTube ID: " + youtubeId); // Debugging
        }

        // Get only the last three items
        List<Content> lastThreeItems = contentList.stream()
                .skip(Math.max(0, contentList.size() - 3)) // Skip items to keep only the last three
                .collect(Collectors.toList());

        // Add processed content and other attributes to the model
        model.addAttribute("contentList", lastThreeItems);
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("role", session.getAttribute("role"));

        // Include optional messages
        if (session.getAttribute("message") != null) {
            model.addAttribute("message", session.getAttribute("message"));
            session.removeAttribute("message");
        }
        if (session.getAttribute("error") != null) {
            model.addAttribute("error", session.getAttribute("error"));
            session.removeAttribute("error");
        }

        return "dashboard";
    }

    private String extractYouTubeId(String youtubeLink) {
        if (youtubeLink == null || youtubeLink.isEmpty()) {
            return null; // Return null if the link is invalid
        }

        try {
            // Match standard YouTube URLs with "v" parameter or short URLs
            String regex = "v=([^&]+)|youtu\\.be/([^?&]+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(youtubeLink);
            if (matcher.find()) {
                return matcher.group(1) != null ? matcher.group(1) : matcher.group(2); // Return video ID
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; // Return null if no match is found
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        if (session.getAttribute("user_id") == null) {
            model.addAttribute("error", "Session expired. Please log in again.");
            return "login";
        }

        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("role", session.getAttribute("role"));
        return "profile"; // Renders profile.html
    }

}
