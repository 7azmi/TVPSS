
// Updated UserRepository.java
package com.tvpss.repositories;

import com.tvpss.models.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Find a user by username
    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username);
        return users.isEmpty() ? null : users.get(0);
    }

    // Save a new user
    public int save(User user) {
        String sql = "INSERT INTO users (username, password, role, name, email, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getRole(), user.getName(), user.getEmail(), user.getPhoneNumber());
    }

    // Find a user by ID
    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    // Update a user
    public int updateUser(User user) {
        String sql = "UPDATE users SET username = ?, password = ?, role = ?, name = ?, email = ?, phone_number = ? WHERE id = ?";
        return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getRole(), user.getName(), user.getEmail(), user.getPhoneNumber(), user.getId());
    }
}