mysql -u root -p

CREATE DATABASE tvpss;

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
                         uploaded_by VARCHAR(50)
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




INSERT INTO users (username, password, role) VALUES
                                                 ('admin', 'admin123', 'ADMIN'),
                                                 ('schoolRep', 'rep123', 'SCHOOL_REP'),
                                                 ('student', 'student123', 'STUDENT');

INSERT INTO content (title, description, youtube_link, status, uploaded_by) VALUES
                                                                                ('Mathematics Lesson 1', 'Introduction to Algebra', 'https://youtube.com/example1', 'APPROVED', 'SchoolRep1'),
                                                                                ('Science Experiment', "Understanding Newton\'s Laws", 'https://youtube.com/example2', 'PENDING', 'SchoolRep2');

INSERT INTO equipment (user_id, name, logo, mini_studio, in_school_recording, upload_on_youtube, recording_inside_outside, external_agency_collaboration, green_screen_technology)
VALUES
    (1, 'Mini Studio', TRUE, TRUE, FALSE, TRUE, FALSE, FALSE, FALSE), -- Associated with 'admin'
    (2, 'Green Screen Technology', TRUE, FALSE, TRUE, FALSE, TRUE, FALSE, TRUE), -- Associated with 'schoolRep'
    (3, 'In-School Recording', FALSE, FALSE, TRUE, FALSE, TRUE, TRUE, FALSE), -- Associated with 'student'
    (1, 'YouTube Upload Capability', TRUE, FALSE, FALSE, TRUE, FALSE, FALSE, FALSE); -- Associated with 'admin'

