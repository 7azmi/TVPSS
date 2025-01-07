package com.tvpss.repositories;

import com.tvpss.models.Equipment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EquipmentRepository {
    private final JdbcTemplate jdbcTemplate;

    public EquipmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Find equipment by user ID
    public List<Equipment> findEquipmentByUserId(int userId) {
        String sql = "SELECT * FROM equipment WHERE user_id = ?";
        return jdbcTemplate.query(sql, equipmentRowMapper, userId);
    }

    // Save equipment
    public int save(Equipment equipment) {
        String sql = "INSERT INTO equipment (user_id, name, logo, mini_studio, in_school_recording, upload_on_youtube, recording_inside_outside, external_agency_collaboration, green_screen_technology) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, equipment.getUserId(), equipment.getName(), equipment.isLogo(),
                equipment.isMiniStudio(), equipment.isInSchoolRecording(), equipment.isUploadOnYouTube(),
                equipment.isRecordingInsideOutside(), equipment.isExternalAgencyCollaboration(), equipment.isGreenScreenTechnology());
    }

    // RowMapper for Equipment
    private final RowMapper<Equipment> equipmentRowMapper = (rs, rowNum) -> {
        Equipment equipment = new Equipment();
        equipment.setId(rs.getLong("id"));
        equipment.setUserId(rs.getInt("user_id"));
        equipment.setName(rs.getString("name"));
        equipment.setLogo(rs.getBoolean("logo"));
        equipment.setMiniStudio(rs.getBoolean("mini_studio"));
        equipment.setInSchoolRecording(rs.getBoolean("in_school_recording"));
        equipment.setUploadOnYouTube(rs.getBoolean("upload_on_youtube"));
        equipment.setRecordingInsideOutside(rs.getBoolean("recording_inside_outside"));
        equipment.setExternalAgencyCollaboration(rs.getBoolean("external_agency_collaboration"));
        equipment.setGreenScreenTechnology(rs.getBoolean("green_screen_technology"));
        return equipment;
    };
}
