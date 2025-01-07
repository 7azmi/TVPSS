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

    private final RowMapper<Content> contentRowMapper = (rs, rowNum) -> {
        Content content = new Content();
        content.setId(rs.getLong("id"));
        content.setTitle(rs.getString("title"));
        content.setDescription(rs.getString("description"));
        content.setYoutubeLink(rs.getString("youtube_link"));
        content.setStatus(rs.getString("status"));
        return content;
    };

    public int updateContentStatus(long id, String status) {
        String sql = "UPDATE content SET status = ? WHERE id = ?";
        return jdbcTemplate.update(sql, status, id);
    }

    public List<Content> findPendingContent() {
        String sql = "SELECT * FROM content WHERE status = 'PENDING'";
        return jdbcTemplate.query(sql, contentRowMapper);
    }

    public List<Content> findApprovedContent() {
        String sql = "SELECT * FROM content WHERE status = 'APPROVED'";
        return jdbcTemplate.query(sql, contentRowMapper);
    }

    public int addContent(String title, String description, String youtubeLink, String uploadedBy) {
        String sql = "INSERT INTO content (title, description, youtube_link, status, uploaded_by) VALUES (?, ?, ?, 'PENDING', ?)";
        return jdbcTemplate.update(sql, title, description, youtubeLink, uploadedBy);
    }
}
