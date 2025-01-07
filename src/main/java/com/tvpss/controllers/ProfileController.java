//package com.tvpss.controllers;
//
//import com.tvpss.DummyDatabase;
//import org.springframework.web.bind.annotation.*;
//import java.util.*;
//
//@RestController
//@RequestMapping("/equipment")
//public class ProfileController {
//
//    @GetMapping("/list")
//    public List<Map<String, Object>> getEquipmentList() {
//        return DummyDatabase.equipment;
//    }
//
//    @PostMapping("/request")
//    public String requestEquipment(@RequestParam String equipmentName) {
//        DummyDatabase.equipment.add(Map.of("id", DummyDatabase.equipment.size() + 1,
//                "name", equipmentName,
//                "version", "Requested"));
//        return "Equipment request submitted successfully!";
//    }
//}
