package com.tvpss.controllers;

import com.tvpss.models.Content;
import com.tvpss.repositories.ContentRepository;
//import jakarta.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/content")
public class ContentController {

    private final ContentRepository contentRepository;

    public ContentController(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @GetMapping("/library")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String viewContentLibrary(Model model) {
        try {
            model.addAttribute("contentList", contentRepository.findApprovedContent());
            return "content_library";
        } catch (Exception e) {
            model.addAttribute("error", "Unable to load content library.");
            return "error";
        }
    }

    @GetMapping("/upload")
    public String uploadContentForm() {
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
            return "upload_content";
        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();
            model.addAttribute("error", "Unable to upload content. Please try again.");
            return "error";
        }
    }



    @GetMapping("/manage")
    public String manageContent(Model model) {
        try {
            model.addAttribute("pendingContent", contentRepository.findPendingContent());
            return "manage_content";
        } catch (Exception e) {
            model.addAttribute("error", "Unable to load content management.");
            return "error";
        }
    }

    @PostMapping("/manage/{id}/approve")
    public String approveContent(@PathVariable Long id, Model model) {
        try {
            contentRepository.updateContentStatus(id, "APPROVED");
            return "redirect:/content/manage";
        } catch (Exception e) {
            model.addAttribute("error", "Unable to approve content. Please try again.");
            return "error";
        }
    }

    @PostMapping("/manage/{id}/reject")
    public String rejectContent(@PathVariable Long id, Model model) {
        try {
            contentRepository.updateContentStatus(id, "REJECTED");
            return "redirect:/content/manage";
        } catch (Exception e) {
            model.addAttribute("error", "Unable to reject content. Please try again.");
            return "error";
        }
    }
}
