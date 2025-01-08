//package com.tvpss.controllers;
//
//import com.tvpss.repositories.EquipmentRepository;
//import com.tvpss.models.Equipment;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/equipment")
//public class ProfileController {
//
//    private final EquipmentRepository equipmentRepository;
//
//    public ProfileController(EquipmentRepository equipmentRepository) {
//        this.equipmentRepository = equipmentRepository;
//    }
//
//    @GetMapping("/list/{userId}")
//    public List<Equipment> getEquipmentList(@PathVariable int userId) {
//        return equipmentRepository.findEquipmentByUserId(userId);
//    }
//
//    @PostMapping("/add")
//    public String addEquipment(@RequestBody Equipment equipment) {
//        equipmentRepository.save(equipment);
//        return "Equipment added successfully!";
//    }
//}
