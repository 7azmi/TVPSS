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

    public List<Equipment> getEquipmentBySchools() {
        String sql = "SELECT u.username AS school_name, e.* FROM equipment e JOIN users u ON e.user_id = u.id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Equipment.class));
    }

    public List<Equipment> findEquipmentByUserId(int userId) {
        String sql = "SELECT * FROM equipment WHERE user_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Equipment.class), userId);
    }

    public void updateEquipment(int id, int userId, boolean logo, boolean miniStudio, boolean inSchoolRecording,
                                boolean uploadOnYouTube, boolean recordInsideOutside, boolean externalAgency, boolean greenScreen) {
        String sql = "UPDATE equipment SET logo = ?, mini_studio = ?, in_school_recording = ?, " +
                "upload_on_youtube = ?, recording_inside_outside = ?, external_agency_collaboration = ?, " +
                "green_screen_technology = ? WHERE id = ? AND user_id = ?";
        jdbcTemplate.update(sql, logo, miniStudio, inSchoolRecording, uploadOnYouTube,
                recordInsideOutside, externalAgency, greenScreen, id, userId);
    }
}
