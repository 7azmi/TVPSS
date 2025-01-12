package com.tvpss.controllers;

import com.tvpss.models.Content;
import com.tvpss.repositories.ContentRepository;
//import jakarta.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/content")
public class ContentController {

    private final ContentRepository contentRepository;

    public ContentController(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @GetMapping("/library")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String viewContentLibrary(HttpSession session, Model model) {
        try {
            // Fetch approved content from the repository
            List<Content> contentList = contentRepository.findApprovedContent();

            // Process the content list to extract YouTube IDs
            for (Content content : contentList) {
                String youtubeId = extractYouTubeId(content.getYoutubeLink());
                content.setYoutubeId(youtubeId);
                System.out.println("Extracted YouTube ID: " + youtubeId); // Debugging
            }

            // Add processed content and other attributes to the model
            model.addAttribute("contentList", contentList);
            model.addAttribute("username", session.getAttribute("username"));
            model.addAttribute("role", session.getAttribute("role"));

            return "content_library"; // Returns the Thymeleaf template for content library
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Unable to load content library.");
            return "error"; // Error page in case of failure
        }
    }


    @GetMapping("/upload")
    public String uploadContentForm(HttpSession session, Model model) {
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("role", session.getAttribute("role"));

        return "upload_content";
    }

    @PostMapping("/upload")
    public String uploadContent(@RequestParam String title,
                                @RequestParam String description,
                                @RequestParam String youtubeLink,
                                @RequestParam String category, // Added category
                                HttpSession session,
                                Model model) {
        try {
            // Get the userId from the session
            Integer userId = (Integer) session.getAttribute("user_id");
            if (userId == null) {
                model.addAttribute("error", "You must be logged in to upload content.");
                return "error";
            }

            // Call repository to add content
            contentRepository.addContent(userId, title, description, youtubeLink, category); // Updated with category

            // Success message
            model.addAttribute("message", "Content uploaded successfully and is pending approval.");
            model.addAttribute("username", session.getAttribute("username"));
            model.addAttribute("role", session.getAttribute("role"));
            return "upload_content";
        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();
            model.addAttribute("error", "Unable to upload content. Please try again.");
            return "error";
        }
    }


    @GetMapping("/manage")
    public String manageContent(HttpSession session, Model model) {
        try {
            model.addAttribute("pendingContent", contentRepository.findPendingContent());
            model.addAttribute("username", session.getAttribute("username"));
            model.addAttribute("role", session.getAttribute("role"));
            return "manage_content";
        } catch (Exception e) {
            model.addAttribute("error", "Unable to load content management.");
            return "error";
        }
    }

    @PostMapping("/manage/{id}/approve")
    public String approveContent(@PathVariable Long id, HttpSession session, Model model) {
        try {
            contentRepository.updateContentStatus(id, "APPROVED");
            model.addAttribute("username", session.getAttribute("username"));
            model.addAttribute("role", session.getAttribute("role"));
            return "redirect:/content/manage";
        } catch (Exception e) {
            model.addAttribute("error", "Unable to approve content. Please try again.");
            return "error";
        }
    }

    @PostMapping("/manage/{id}/reject")
    public String rejectContent(@PathVariable Long id, HttpSession session, Model model) {
        try {
            contentRepository.updateContentStatus(id, "REJECTED");
            model.addAttribute("username", session.getAttribute("username"));
            model.addAttribute("role", session.getAttribute("role"));
            return "redirect:/content/manage";
        } catch (Exception e) {
            model.addAttribute("error", "Unable to reject content. Please try again.");
            return "error";
        }
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
}
