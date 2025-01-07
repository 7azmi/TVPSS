-- Use the database
USE tvpss;

-- Insert users
INSERT INTO users (username, password, role) VALUES
                                                 ('admin', 'admin123', 'ADMIN'),
                                                 ('schoolRep', 'rep123', 'SCHOOL_REP'),
                                                 ('student', 'student123', 'STUDENT');

-- Insert content
INSERT INTO content (title, description, youtube_link, status, user_id) VALUES
                                                                            ('Mathematics Lesson 1', 'Introduction to Algebra', 'https://youtube.com/example1', 'APPROVED', 2), -- Associated with 'schoolRep'
                                                                            ('Science Experiment', 'Understanding Newtons Laws', 'https://youtube.com/example2', 'PENDING', 3); -- Associated with 'student'

-- Insert equipment
INSERT INTO equipment (user_id, name, logo, mini_studio, in_school_recording, upload_on_youtube, recording_inside_outside, external_agency_collaboration, green_screen_technology)
VALUES
    (1, 'Mini Studio', TRUE, TRUE, FALSE, TRUE, FALSE, FALSE, FALSE), -- Associated with 'admin'
    (2, 'Green Screen Technology', TRUE, FALSE, TRUE, FALSE, TRUE, FALSE, TRUE), -- Associated with 'schoolRep'
    (3, 'In-School Recording', FALSE, FALSE, TRUE, FALSE, TRUE, TRUE, FALSE), -- Associated with 'student'
    (1, 'YouTube Upload Capability', TRUE, FALSE, FALSE, TRUE, FALSE, FALSE, FALSE); -- Associated with 'admin'
