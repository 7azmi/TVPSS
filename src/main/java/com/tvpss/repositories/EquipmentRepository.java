
// Updated EquipmentRepository.java
package com.tvpss.repositories;

import com.tvpss.models.Equipment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EquipmentRepository {

    private final JdbcTemplate jdbcTemplate;

    public EquipmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Equipment findEquipmentByUserId(int userId) {
        String sql = "SELECT * FROM equipment WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Equipment.class), userId);
    }

    public void updateEquipment(int userId, boolean logo, boolean miniStudio, boolean inSchoolRecording,
                                boolean uploadOnYouTube, boolean recordInsideOutside, boolean externalAgency, boolean greenScreen) {
        String sql = "UPDATE equipment SET logo = ?, mini_studio = ?, in_school_recording = ?, " +
                "upload_on_youtube = ?, recording_inside_outside = ?, external_agency_collaboration = ?, " +
                "green_screen_technology = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, logo, miniStudio, inSchoolRecording, uploadOnYouTube,
                recordInsideOutside, externalAgency, greenScreen, userId);
    }

    public List<Equipment> getEquipmentBySchools() {
        String sql = "SELECT u.username AS school_name, e.logo, e.mini_studio, e.in_school_recording, e.upload_on_youtube, " +
                "e.recording_inside_outside, e.external_agency_collaboration, e.green_screen_technology " +
                "FROM equipment e JOIN users u ON e.user_id = u.id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Equipment.class));
    }


    public List<Map<String, Object>> getSchoolRepresentativesWithEquipment() {
        String sql = "SELECT u.id, u.name, u.username, u.email, u.phone_number, " +
                "e.logo, e.mini_studio, e.in_school_recording, e.upload_on_youtube, " +
                "e.recording_inside_outside, e.external_agency_collaboration, e.green_screen_technology " +
                "FROM users u " +
                "LEFT JOIN equipment e ON u.id = e.user_id " +
                "WHERE u.role = 'SCHOOL_REP'";
        return jdbcTemplate.queryForList(sql);
    }

    public void createEquipment(int userId) {
        String sql = "INSERT INTO equipment (user_id, logo, mini_studio, in_school_recording, upload_on_youtube, " +
                "recording_inside_outside, external_agency_collaboration, green_screen_technology) " +
                "VALUES (?, true, true, true, false, false, false, false)";
        jdbcTemplate.update(sql, userId);
    }


}