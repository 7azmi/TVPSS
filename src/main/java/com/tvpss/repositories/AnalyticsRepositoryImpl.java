package com.tvpss.repositories;

import com.tvpss.models.Analytics;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnalyticsRepositoryImpl implements AnalyticsRepository {

    private final JdbcTemplate jdbcTemplate;

    public AnalyticsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Analytics> findAllAnalytics() {
        String sql = "SELECT * FROM Analytics";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Analytics(
                rs.getInt("id"),
                rs.getDate("date").toLocalDate(),
                rs.getInt("active_users"),
                rs.getFloat("active_users_change"),
                rs.getInt("total_uploads"),
                rs.getFloat("total_uploads_change"),
                rs.getFloat("engagement_rate"),
                rs.getFloat("engagement_rate_change"),
                rs.getInt("total_views"),
                rs.getFloat("total_views_change")
        ));
    }
}
