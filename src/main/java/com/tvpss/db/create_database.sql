-- Create the database
CREATE DATABASE IF NOT EXISTS tvpss;
USE tvpss;

-- Create 'users' table
CREATE TABLE users (
                       id INT NOT NULL AUTO_INCREMENT,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       role VARCHAR(20) NOT NULL,
                       name VARCHAR(100) NOT NULL, -- Full name of student, admin, or school
                       email VARCHAR(100),
                       phone_number VARCHAR(15),
                       PRIMARY KEY (id),
                       UNIQUE KEY (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create 'equipment' table
CREATE TABLE equipment (
                           user_id INT NOT NULL,
                           logo TINYINT(1) DEFAULT 0,
                           mini_studio TINYINT(1) DEFAULT 0,
                           in_school_recording TINYINT(1) DEFAULT 0,
                           upload_on_youtube TINYINT(1) DEFAULT 0,
                           recording_inside_outside TINYINT(1) DEFAULT 0,
                           external_agency_collaboration TINYINT(1) DEFAULT 0,
                           green_screen_technology TINYINT(1) DEFAULT 0,
                           PRIMARY KEY (user_id),
                           CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create 'content' table
CREATE TABLE content (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         title VARCHAR(255) NOT NULL,
                         description TEXT NOT NULL,
                         youtube_link VARCHAR(255),
                         status VARCHAR(20) DEFAULT 'PENDING',
                         user_id INT NOT NULL,
                         category VARCHAR(100) NOT NULL,
                         PRIMARY KEY (id),
                         CONSTRAINT fk_user_content FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
