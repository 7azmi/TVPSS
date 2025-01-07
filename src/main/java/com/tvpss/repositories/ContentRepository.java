package com.tvpss.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ContentRepository {

    private final JdbcTemplate jdbcTemplate;

    public ContentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> findAllContent() {
        String sql = "SELECT * FROM content";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> findPendingContent() {
        String sql = "SELECT * FROM content WHERE status = 'PENDING'";
        return jdbcTemplate.queryForList(sql);
    }

    public int updateContentStatus(long id, String status) {
        String sql = "UPDATE content SET status = ? WHERE id = ?";
        return jdbcTemplate.update(sql, status, id);
    }

    public int addContent(String title, String description, String youtubeLink, String uploadedBy) {
        String sql = "INSERT INTO content (title, description, youtube_link, status, uploaded_by) VALUES (?, ?, ?, 'PENDING', ?)";
        return jdbcTemplate.update(sql, title, description, youtubeLink, uploadedBy);
    }
}
