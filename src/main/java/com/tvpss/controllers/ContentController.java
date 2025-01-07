package com.tvpss.controllers;

import com.tvpss.models.Content;
import com.tvpss.repositories.ContentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/content")
public class ContentController {

    private final ContentRepository contentRepository;

    public ContentController(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @GetMapping("/library")
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
                                HttpSession session,
                                Model model) {
        try {
            int userId = (int) session.getAttribute("user_id");
            contentRepository.addContent(title, description, youtubeLink, "TODO");
            model.addAttribute("message", "Content uploaded successfully and is pending approval.");
            return "upload_content";
        } catch (Exception e) {
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
