package com.tvpss.controllers;

import com.tvpss.models.Equipment;
import com.tvpss.repositories.EquipmentRepository;
import com.tvpss.repositories.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/equipment")
public class SchoolRepEquipmentController {

    private final EquipmentRepository equipmentRepository;
    private final UserRepository userRepository;

    public SchoolRepEquipmentController(EquipmentRepository equipmentRepository, UserRepository userRepository) {
        this.equipmentRepository = equipmentRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/schools")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SCHOOL_REP')")
    public String viewSchoolList(Model model) {
        List<Equipment> equipmentList = equipmentRepository.getEquipmentBySchools();
        model.addAttribute("equipmentList", equipmentList);
        return "school_list";
    }

    @GetMapping("/update")
    @PreAuthorize("hasAuthority('SCHOOL_REP')")
    public String showEquipmentUpdateForm(HttpSession session, Model model) {
        int userId = (int) session.getAttribute("user_id");
        List<Equipment> equipmentList = equipmentRepository.findEquipmentByUserId(userId);
        model.addAttribute("equipmentList", equipmentList);
        return "update_equipment";
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('SCHOOL_REP')")
    public String updateEquipment(@RequestParam("id") List<Integer> ids,
                                  @RequestParam("logo") List<Boolean> logos,
                                  @RequestParam("miniStudio") List<Boolean> miniStudios,
                                  @RequestParam("inSchoolRecording") List<Boolean> inSchoolRecordings,
                                  @RequestParam("uploadOnYouTube") List<Boolean> uploadOnYouTubes,
                                  @RequestParam("recordInsideOutside") List<Boolean> recordInsideOutsides,
                                  @RequestParam("externalAgency") List<Boolean> externalAgencies,
                                  @RequestParam("greenScreen") List<Boolean> greenScreens,
                                  HttpSession session, Model model) {
        int userId = (int) session.getAttribute("user_id");
        for (int i = 0; i < ids.size(); i++) {
            equipmentRepository.updateEquipment(
                    ids.get(i), userId, logos.get(i), miniStudios.get(i), inSchoolRecordings.get(i),
                    uploadOnYouTubes.get(i), recordInsideOutsides.get(i), externalAgencies.get(i), greenScreens.get(i)
            );
        }
        model.addAttribute("message", "Equipment updated successfully.");
        return "redirect:/equipment/update";
    }
}
