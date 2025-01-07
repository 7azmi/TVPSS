package com.tvpss.controllers;

import com.tvpss.models.Content;
import com.tvpss.DummyDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/content")
public class ContentController {

    // View the content library (approved content only)
    @GetMapping("/library")
    public String viewContentLibrary(Model model) {
        model.addAttribute("contentList", DummyDatabase.content.stream()
                .filter(c -> c.get("status").equals("APPROVED"))
                .toList());
        return "content_library";
    }

    // Display the upload form
    @GetMapping("/upload")
    public String uploadContentForm(Model model) {
        return "upload_content";
    }

    // Handle content upload
    @PostMapping("/upload")
    public String uploadContent(@RequestParam String title,
                                @RequestParam String description,
                                @RequestParam String youtubeLink,
                                @RequestParam String uploadedBy,
                                Model model) {
        Map<String, Object> newContent = Map.of(
                "id", DummyDatabase.content.size() + 1L,
                "title", title,
                "description", description,
                "youtubeLink", youtubeLink,
                "status", "PENDING",
                "uploadedBy", uploadedBy
        );
        DummyDatabase.content.add(newContent);
        model.addAttribute("message", "Content uploaded successfully and is pending approval.");
        return "upload_content";
    }

    // Display pending content for admin
    @GetMapping("/manage")
    public String manageContent(Model model) {
        model.addAttribute("pendingContent", DummyDatabase.content.stream()
                .filter(c -> c.get("status").equals("PENDING"))
                .toList());
        return "manage_content";
    }


    @PostMapping("/manage/{id}/approve")
    public String approveContent(@PathVariable Long id, Model model) {
        try {
            // Find the content by ID
            boolean updated = DummyDatabase.content.stream()
                    .filter(c -> c.get("id").equals(id))
                    .findFirst()
                    .map(content -> {
                        content.put("status", "APPROVED"); // Update status to APPROVED
                        return true;
                    })
                    .orElse(false); // Return false if content with the given ID is not found

            if (!updated) {
                model.addAttribute("error", "Content not found with ID: " + id);
            }

            return "redirect:/content/manage"; // Redirect to manage content page
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while approving the content: " + e.getMessage());
            return "manage_content";
        }
    }

    @PostMapping("/manage/{id}/reject")
    public String rejectContent(@PathVariable Long id, Model model) {
        try {
            // Find the content by ID
            boolean updated = DummyDatabase.content.stream()
                    .filter(c -> c.get("id").equals(id))
                    .findFirst()
                    .map(content -> {
                        content.put("status", "REJECTED"); // Update status to REJECTED
                        return true;
                    })
                    .orElse(false); // Return false if content with the given ID is not found

            if (!updated) {
                model.addAttribute("error", "Content not found with ID: " + id);
            }

            return "redirect:/content/manage"; // Redirect to manage content page
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred while rejecting the content: " + e.getMessage());
            return "manage_content";
        }
    }
}