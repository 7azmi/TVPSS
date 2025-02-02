CREATE TABLE Analytics (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           date DATE NOT NULL,
                           active_users INT NOT NULL,
                           active_users_change FLOAT NOT NULL,
                           total_uploads INT NOT NULL,
                           total_uploads_change FLOAT NOT NULL,
                           engagement_rate FLOAT NOT NULL,
                           engagement_rate_change FLOAT NOT NULL,
                           total_views INT NOT NULL,
                           total_views_change FLOAT NOT NULL
);

INSERT INTO Analytics (date, active_users, active_users_change, total_uploads, total_uploads_change, engagement_rate, engagement_rate_change, total_views, total_views_change)
VALUES
    ('2025-01-10', 40689, 8.5, 10293, 1.3, 12.5, -4.3, 2040, 1.8),
    ('2025-01-09', 37500, -2.0, 10160, 0.5, 16.8, 2.1, 2005, -0.8),
    ('2025-01-08', 38250, 3.5, 10110, 1.8, 14.7, -1.5, 2020, 0.5),
    ('2025-01-01', 35000, 2.0, 10000, 1.2, 15.5, -1.5, 1900, 2.5),
    ('2025-01-02', 35500, 1.4, 10020, 0.8, 16.0, 0.5, 1950, 2.6),
    ('2025-01-03', 36000, 1.4, 10050, 1.0, 16.3, 0.3, 2000, 2.5),
    ('2025-01-04', 36500, 1.4, 10100, 1.0, 16.5, 0.2, 2025, 1.3),
    ('2025-01-05', 37000, 1.4, 10120, 0.8, 16.8, 0.3, 2030, 0.5),
    ('2025-01-06', 37400, 1.1, 10140, 0.6, 16.5, -0.3, 2028, -0.1),
    ('2025-01-07', 38000, 1.6, 10180, 1.0, 16.0, -0.5, 2035, 0.3),
    ('2025-01-08', 38250, 0.7, 10190, 0.5, 14.7, -1.3, 2020, -0.7),
    ('2025-01-09', 37500, -2.0, 10160, -0.3, 16.8, 2.1, 2005, -0.8),
    ('2025-01-10', 40689, 8.5, 10293, 1.3, 12.5, -4.3, 2040, 1.8),
    ('2025-01-11', 41000, 0.8, 10310, 0.9, 13.0, 0.5, 2055, 0.7),
    ('2025-01-12', 41500, 1.2, 10350, 1.1, 13.5, 0.5, 2065, 0.5),
    ('2025-01-13', 42000, 1.2, 10400, 1.2, 14.0, 0.5, 2075, 0.5),
    ('2025-01-14', 42500, 1.2, 10450, 1.2, 14.5, 0.5, 2080, 0.2),
    ('2025-01-15', 43000, 1.2, 10500, 1.2, 15.0, 0.5, 2100, 1.0),
    ('2025-01-16', 43500, 1.2, 10550, 1.2, 15.5, 0.5, 2120, 1.0),
    ('2025-01-17', 44000, 1.2, 10600, 1.2, 16.0, 0.5, 2130, 0.5),
    ('2025-01-18', 44500, 1.2, 10650, 1.2, 16.5, 0.5, 2140, 0.5),
    ('2025-01-19', 45000, 1.2, 10700, 1.2, 17.0, 0.5, 2150, 0.5),
    ('2025-01-20', 45500, 1.2, 10750, 1.2, 17.5, 0.5, 2160, 0.5),
    ('2025-01-21', 46000, 1.2, 10800, 1.2, 18.0, 0.5, 2170, 0.5);