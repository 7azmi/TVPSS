package com.tvpss;

import java.util.*;

public class DummyDatabase {

    public static List<Map<String, Object>> users = new ArrayList<>();
    public static List<Map<String, Object>> content = new ArrayList<>();
    public static List<Map<String, Object>> equipment = new ArrayList<>();

    static {
        // Initializing Users
        users.add(Map.of("id", 1, "username", "admin", "password", "admin123", "role", "ADMIN"));
        users.add(Map.of("id", 2, "username", "schoolRep", "password", "rep123", "role", "SCHOOL_REP"));
        users.add(Map.of("id", 3, "username", "student", "password", "student123", "role", "STUDENT"));

        // Initializing Equipment
        equipment.add(Map.of("id", 1, "name", "Mini Studio", "version", "Version 2"));
        equipment.add(Map.of("id", 2, "name", "Green Screen Technology", "version", "Version 4"));
        equipment.add(Map.of("id", 3, "name", "In-School Recording", "version", "Version 3"));
        equipment.add(Map.of("id", 4, "name", "YouTube Upload Capability", "version", "Version 1"));
    }
}
