package com.tvpss.repositories;

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

    public List<Map<String, Object>> findAllEquipment() {
        String sql = "SELECT * FROM equipment";
        return jdbcTemplate.queryForList(sql);
    }

    public int addEquipment(String name, String version) {
        String sql = "INSERT INTO equipment (name, version) VALUES (?, ?)";
        return jdbcTemplate.update(sql, name, version);
    }
}
