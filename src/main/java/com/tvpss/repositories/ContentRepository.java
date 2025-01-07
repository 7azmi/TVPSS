package com.tvpss.repositories;

import com.tvpss.models.Content;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContentRepository {
    private final JdbcTemplate jdbcTemplate;

    public ContentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Add content
    public int addContent(int userId, String title, String description, String youtubeLink) {
        String sql = "INSERT INTO content (user_id, title, description, youtube_link, status) VALUES (?, ?, ?, ?, 'PENDING')";
        return jdbcTemplate.update(sql, userId, title, description, youtubeLink);
    }

    // Find pending content
    public List<Content> findPendingContent() {
        String sql = "SELECT id, title, description, youtube_link, status, user_id FROM content WHERE status = 'PENDING'";
        return jdbcTemplate.query(sql, contentRowMapper);
    }

    // Find approved content
    public List<Content> findApprovedContent() {
        String sql = "SELECT id, title, description, youtube_link, status, user_id FROM content WHERE status = 'APPROVED'";
        return jdbcTemplate.query(sql, contentRowMapper);
    }

    // Approve content
    public int approveContent(Long id) {
        String sql = "UPDATE content SET status = 'APPROVED' WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // Reject content
    public int rejectContent(Long id) {
        String sql = "UPDATE content SET status = 'REJECTED' WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int updateContentStatus(long id, String status) {
        String sql = "UPDATE content SET status = ? WHERE id = ?";
        return jdbcTemplate.update(sql, status, id);
    }

    // RowMapper for Content
    private final RowMapper<Content> contentRowMapper = (rs, rowNum) -> {
        Content content = new Content();
        content.setId(rs.getLong("id"));
        content.setTitle(rs.getString("title"));
        content.setDescription(rs.getString("description"));
        content.setYoutubeLink(rs.getString("youtube_link"));
        content.setStatus(rs.getString("status"));
        content.setUserId(rs.getInt("user_id"));
        return content;
    };
}
