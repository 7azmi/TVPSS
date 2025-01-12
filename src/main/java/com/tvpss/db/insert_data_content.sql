DROP TABLE IF EXISTS content;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- Insert dummy data into the 'content' table
INSERT INTO content (title, description, youtube_link, status, user_id, category) VALUES

-- Content for Category: Technology
('How AI is Shaping the Future', 'A detailed video about the impact of artificial intelligence on various industries.', 'https://youtu.be/RzkD_rTEBYs?si=HLnWLRgfZoxfkUHR', 'APPROVED', 1, 'Technology'),
('Top 10 Programming Languages in 2025', 'A countdown of the most popular programming languages and their applications.', 'https://youtu.be/9XtMEdrPu8Q?si=-tBEqTeWtB4MXTvV', 'PENDING', 2, 'Technology'),
('The Future of Quantum Computing', 'Exploring how quantum computing could revolutionize technology.', 'https://youtu.be/lum6rdKr4To?si=lTWZjt7akpj3_-l5', 'PENDING', 1, 'Technology'),
('Understanding Blockchain Technology', 'A beginner-friendly explanation of blockchain and its uses.', 'https://youtu.be/SSo_EIwHSd4?si=Pa59jHNyJBBLQuUV', 'APPROVED', 2, 'Technology'),
('Top Gadgets of CES 2025', 'A showcase of the most innovative gadgets hitting the market.', 'https://youtu.be/mBFf-aYoV6Q?si=PI9D-Afo-UoZJu1s', 'APPROVED', 1, 'Technology'),

-- Content for Category: Education
('Study Tips for University Students', 'Practical advice and tips for achieving academic success.', 'https://youtu.be/TjPFZaMe2yw?si=oVWudaGdX44cH_06', 'APPROVED', 1, 'Education'),
('How to Ace Your Exams', 'A motivational video about effective studying techniques.', 'https://youtu.be/MWk9jXuCcMI?si=D-QcijeZj67xQukE', 'APPROVED', 2, 'Education'),
('Benefits of Online Learning', 'A video discussing the advantages of e-learning platforms.', 'https://youtu.be/R8AuEiQcHWs?si=BYt4ESELtDuXEB5D', 'PENDING', 1, 'Education'),
('Best Books for Personal Growth', 'A curated list of books to enhance your personal development.', 'https://youtu.be/0bMF8hQk30Q?si=vs_wjRokkyAtdUZC', 'APPROVED', 2, 'Education'),
('Effective Time Management Strategies', 'Learn how to manage your time efficiently for academic success.', 'https://youtu.be/iDbdXTMnOmE?si=oR0E6vDdpSF6aukt', 'APPROVED', 1, 'Education');
