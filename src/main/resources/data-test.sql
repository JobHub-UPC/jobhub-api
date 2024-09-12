INSERT INTO "users" (role, email, password, created, active) VALUES
                                                               ('ADMIN', 'admin@techcorp.com', 'admin123', '2024-09-01 10:00:00', TRUE),
                                                               ('USER', 'john.doe@gmail.com', 'password123', '2024-09-02 11:15:00', TRUE),
                                                               ('USER', 'jane.smith@yahoo.com', 'jane456', '2024-09-03 12:30:00', TRUE),
                                                               ('COMPANY', 'info@innovatech.com', 'innovatech789', '2024-09-04 13:45:00', TRUE),
                                                               ('USER', 'mike.jones@hotmail.com', 'mike321', '2024-09-05 14:00:00', FALSE)
    ON CONFLICT DO NOTHING;
