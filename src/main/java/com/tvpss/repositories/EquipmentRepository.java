package com.tvpss.repositories;

import com.tvpss.models.Equipment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EquipmentRepository {

    private final JdbcTemplate jdbcTemplate;

    public EquipmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Save equipment to the database
    public int save(Equipment equipment) {
        String sql = "INSERT INTO equipment (user_id, name, logo, mini_studio, in_school_recording, " +
                "upload_on_youtube, recording_inside_outside, external_agency_collaboration, green_screen_technology) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                equipment.getUserId(),
                equipment.getName(),
                equipment.isLogo(),
                equipment.isMiniStudio(),
                equipment.isInSchoolRecording(),
                equipment.isUploadOnYoutube(),
                equipment.isRecordingInsideOutside(),
                equipment.isExternalAgencyCollaboration(),
                equipment.isGreenScreenTechnology()
        );
    }

    // Find equipment by user ID
    public List<Equipment> findEquipmentByUserId(int userId) {
        String sql = "SELECT * FROM equipment WHERE user_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Equipment.class), userId);
    }
}
