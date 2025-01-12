package com.tvpss.controllers;

import com.tvpss.models.User;
import com.tvpss.models.Equipment;
import com.tvpss.repositories.UserRepository;
import com.tvpss.repositories.EquipmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserRepository userRepository;
    private final EquipmentRepository equipmentRepository;

    public ProfileController(UserRepository userRepository, EquipmentRepository equipmentRepository) {
        this.userRepository = userRepository;
        this.equipmentRepository = equipmentRepository;
    }

    // UC012: Update Profile Details
    @GetMapping("/update")
    public String updateProfileForm(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("user_id");
        if (userId == null) {
            model.addAttribute("error", "You must be logged in to update your profile.");
            return "error";
        }

        User user = userRepository.findByUsername((String) session.getAttribute("username"));
        model.addAttribute("user", user);
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("role", session.getAttribute("role"));
        return "update_profile";
    }

    @PostMapping("/update")
    public String updateProfile(@RequestParam String name,
                                @RequestParam String email,
                                @RequestParam String phoneNumber,
                                HttpSession session,
                                Model model) {
        Integer userId = (Integer) session.getAttribute("user_id");
        if (userId == null) {
            model.addAttribute("error", "You must be logged in to update your profile.");
            return "error";
        }

        userRepository.updateUserDetails(userId, name, email, phoneNumber);
        model.addAttribute("message", "Profile updated successfully.");
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("role", session.getAttribute("role"));
        return "redirect:/profile";
    }

    // UC013: Update Equipment List
    @GetMapping("/equipment/update")
    public String updateEquipmentForm(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("user_id");
        if (userId == null) {
            model.addAttribute("error", "You must be logged in to update equipment.");
            return "error";
        }

        Equipment equipment = equipmentRepository.findEquipmentByUserId(userId);
        // System.out.println(equipment.isGreenScreenTechnology());
        model.addAttribute("equipment", equipment);
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("role", session.getAttribute("role"));
        return "update_equipment";
    }

    @PostMapping("/equipment/update")
    public String updateEquipment(@RequestParam(required = false) Boolean logo,
                                  @RequestParam(required = false) Boolean miniStudio,
                                  @RequestParam(required = false) Boolean inSchoolRecording,
                                  @RequestParam(required = false) Boolean uploadOnYouTube,
                                  @RequestParam(required = false) Boolean recordingInsideOutside,
                                  @RequestParam(required = false) Boolean externalAgencyCollaboration,
                                  @RequestParam(required = false) Boolean greenScreenTechnology,
                                  HttpSession session,
                                  Model model) {
        Integer userId = (Integer) session.getAttribute("user_id");
        if (userId == null) {
            model.addAttribute("error", "You must be logged in to update equipment.");
            return "error";
        }

        // Set default values for null parameters
        boolean updatedLogo = logo != null && logo;
        boolean updatedMiniStudio = miniStudio != null && miniStudio;
        boolean updatedInSchoolRecording = inSchoolRecording != null && inSchoolRecording;
        boolean updatedUploadOnYouTube = uploadOnYouTube != null && uploadOnYouTube;
        boolean updatedRecordingInsideOutside = recordingInsideOutside != null && recordingInsideOutside;
        boolean updatedExternalAgencyCollaboration = externalAgencyCollaboration != null && externalAgencyCollaboration;
        boolean updatedGreenScreenTechnology = greenScreenTechnology != null && greenScreenTechnology;

        equipmentRepository.updateEquipment(userId, updatedLogo, updatedMiniStudio, updatedInSchoolRecording,
                updatedUploadOnYouTube, updatedRecordingInsideOutside, updatedExternalAgencyCollaboration,
                updatedGreenScreenTechnology);

        model.addAttribute("message", "Equipment updated successfully.");
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("role", session.getAttribute("role"));
        return "redirect:/profile";
    }


    // UC014: View School List
    @GetMapping("/schools")
    public String viewSchoolList(HttpSession session,
                                 Model model) {
        // Fetch school representatives and their equipment details from the repository
        List<Map<String, Object>> schoolReps = equipmentRepository.getSchoolRepresentativesWithEquipment();

        // Calculate version for each school representative
        for (Map<String, Object> schoolRep : schoolReps) {
            int version = 1;

            if (Boolean.TRUE.equals(schoolRep.get("logo")) &&
                    Boolean.TRUE.equals(schoolRep.get("mini_studio"))) {
                version = 2;

                if (Boolean.TRUE.equals(schoolRep.get("in_school_recording")) &&
                        Boolean.TRUE.equals(schoolRep.get("upload_on_youtube"))) {
                    version = 3;

                    if (Boolean.TRUE.equals(schoolRep.get("recording_inside_outside")) &&
                            Boolean.TRUE.equals(schoolRep.get("external_agency_collaboration")) &&
                            Boolean.TRUE.equals(schoolRep.get("green_screen_technology"))) {
                        version = 4;
                    }
                }
            }

            schoolRep.put("version", version);
        }


        // Add school representatives with their calculated versions to the model
        model.addAttribute("schoolReps", schoolReps);
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("role", session.getAttribute("role"));
        return "school_list";
    }

    // Helper method to calculate version based on strict criteria
    private int calculateVersion(Equipment schoolRep) {
        // Check for Version 4
        if (schoolRep.isLogo() &&
                schoolRep.isMiniStudio() &&
                schoolRep.isInSchoolRecording() &&
                schoolRep.isUploadOnYoutube() &&
                schoolRep.isRecordingInsideOutside() &&
                schoolRep.isExternalAgencyCollaboration() &&
                schoolRep.isGreenScreenTechnology()) {
            return 4;
        }

        // Check for Version 3
        if (schoolRep.isLogo() &&
                schoolRep.isMiniStudio() &&
                schoolRep.isInSchoolRecording() &&
                schoolRep.isUploadOnYoutube()) {
            return 3;
        }

        // Check for Version 2
        if (schoolRep.isLogo() &&
                schoolRep.isMiniStudio()) {
            return 2;
        }

        // Default to Version 1 (if even Version 2 criteria aren't met)
        return 1;
    }

}
