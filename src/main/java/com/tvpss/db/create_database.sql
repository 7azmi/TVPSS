-- Create the database
CREATE DATABASE tvpss;

-- Use the new database
USE tvpss;

-- Users table
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL,
                       role VARCHAR(20) NOT NULL
);

-- Content table
CREATE TABLE content (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         description TEXT NOT NULL,
                         youtube_link VARCHAR(255),
                         status VARCHAR(20) DEFAULT 'PENDING',
                         user_id INT NOT NULL,
                         CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Equipment table
CREATE TABLE equipment (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           user_id INT NOT NULL, -- Foreign key to users table
                           name VARCHAR(100) NOT NULL,
                           logo BOOLEAN DEFAULT FALSE,
                           mini_studio BOOLEAN DEFAULT FALSE,
                           in_school_recording BOOLEAN DEFAULT FALSE,
                           upload_on_youtube BOOLEAN DEFAULT FALSE,
                           recording_inside_outside BOOLEAN DEFAULT FALSE,
                           external_agency_collaboration BOOLEAN DEFAULT FALSE,
                           green_screen_technology BOOLEAN DEFAULT FALSE,
                           CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
