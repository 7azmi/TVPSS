USE tvpss;

-- Insert dummy data into 'users'
INSERT INTO users (username, password, role, name, email, phone_number) VALUES
                                                                            ('student', 'student', 'STUDENT', 'Ahmed Ghaleb', 'john.doe@example.com', '1234567890'),
                                                                            ('admin', 'admin', 'ADMIN', '7azmi', 'jane.admin@example.com', '0987654321'),
                                                                            ('schoolRep', 'schoolRep', 'SCHOOL_REP', 'UTM sekolah', 'abc.school@example.com', '1122334455');

-- Insert dummy data into 'equipment'
INSERT INTO equipment (user_id, logo, mini_studio, in_school_recording, upload_on_youtube, recording_inside_outside, external_agency_collaboration, green_screen_technology) VALUES
                                                                                                                                                                                 (1, 1, 1, 1, 1, 1, 0, 1),
                                                                                                                                                                                 (2, 0, 0, 0, 1, 0, 1, 0),
                                                                                                                                                                                 (3, 1, 1, 1, 1, 1, 1, 1);

-- Insert dummy data into 'content'
INSERT INTO content (title, description, youtube_link, status, user_id) VALUES
                                                                            ('Video 1', 'Description for video 1', 'https://youtube.com/video1', 'APPROVED', 1),
                                                                            ('Video 2', 'Description for video 2', 'https://youtube.com/video2', 'PENDING', 2),
                                                                            ('School Promo', 'Promotional video for school', 'https://youtube.com/promo', 'APPROVED', 3);
