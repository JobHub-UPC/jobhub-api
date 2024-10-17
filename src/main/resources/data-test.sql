INSERT INTO roles(name) VALUES
                            ('Admin'),
                            ('Applicant'),
                            ('Company')
ON CONFLICT DO NOTHING;
INSERT INTO users (rol_id, email, password, created, active)
VALUES
    (1, 'admin@techcorp.com', 'admin123', '2024-09-01 10:00:00', TRUE),
    (2, 'john.doe@gmail.com', 'password123', '2024-09-02 11:15:00', TRUE),
    (2, 'jane.smith@yahoo.com', 'jane456', '2024-09-03 12:30:00', TRUE),
    (3, 'info@innovatech.com', 'innovatech789', '2024-09-04 13:45:00', TRUE),
    (2, 'mike.jones@hotmail.com', 'mike321', '2024-09-05 14:00:00', FALSE),
    (2, 'luis.gomez@gmail.com', 'luis654', '2024-09-06 09:30:00', TRUE),
    (3, 'sarah.connor@gmail.com', 'sarahpass', '2024-09-07 15:15:00', TRUE),
    (3, 'contact@startupxyz.com', 'startupxyz123', '2024-09-08 16:45:00', TRUE),
    (3, 'peter.parker@outlook.com', 'spiderman789', '2024-09-09 17:00:00', FALSE),
    (2, 'diana.prince@wondercorp.com', 'wonderwoman321', '2024-09-10 08:20:00', TRUE),
    (3,'contacto@gmail.com','jkajdklas23','2024-09-10 08:10:00',TRUE),
    (3,'agustdbestrapper1@gmail.com','498654648','2024-09-10 08:10:00',TRUE)

    ON CONFLICT DO NOTHING;

-- Insertar profesionales
INSERT INTO applicants (first_name, last_name, email, phone, degree, description, country, college, user_id)
VALUES
    ('John', 'Doe', 'johe@example.com', '+123456789', 'Computer Science', 'Software engineer with 5 years of experience in web development.', 'United States', 'Harvard University', 2),
    ('Maria', 'Smith', 'mia.smith@example.com', '+987654321', 'Mechanical Engineering', 'Experienced in automotive design and manufacturing.', 'Canada', 'University of Toronto', 3),
    ('Carlos', 'Gonzalez', 'rlos.gonzalez@example.com', '+1122334455', 'Business Administration', 'Business consultant with a focus on financial strategies for startups.', 'Spain', 'IE Business School', 5),
    ('Luis', 'Martinez', 'ls.martinez@example.com', '+5647382910', 'Data Science', 'Data analyst specializing in machine learning and big data.', 'Peru', 'Pontifical Catholic University of Peru', 6),
    ('Diana', 'Humanos', 'dina.gonzalez@example.com', '+1122384855', 'Business Administration', 'Business consultant with on financial strategies for startups.', 'Peru', 'I School',10)
ON CONFLICT DO NOTHING;

-- Insertar empresas
INSERT INTO companies (name, country, phone, email, website, description, user_id)
VALUES
    ('Innovatech', 'United States', '+1234567890', 'info@innovech.com', 'www.innovatech.com', 'Leading tech company specializing in innovative solutions.', 4),
    ( 'Sarah Technologies', 'Canada', '+0987654321', 'sarah.coor@gmail.com', 'www.sarahtech.com', 'Innovative solutions for technology and software.', 7),
    ( 'Startup XYZ', 'United Kingdom', '+1122334455', 'contact@startupxyz.com', 'www.startupxyz.com', 'Pioneering tech startup with cutting-edge solutions.', 8),
    ( 'Web Dynamics', 'Australia', '+2233445566', 'peter.park@outlook.com', 'www.webdynamics.com', 'Web solutions and consulting for businesses.', 9),
    ( 'Tech Connect', 'Germany', '+3344556677', 'contacto@gil.com', 'www.techconnect.com', 'Connecting technology with business needs.', 11)
ON CONFLICT DO NOTHING;

-- Insertar empleos
INSERT INTO jobs (title, description, location, job_type, posted_date, deadline_date, salary, company_id)
VALUES
    ('Software Engineer', 'Develop and maintain software applications.', 'San Francisco, CA', 'Full-time', '2024-09-01 09:00:00', '2024-10-01 17:00:00', 120000.00, 1),
    ('Product Manager', 'Lead product development and strategy.', 'New York, NY', 'Full-time', '2024-09-05 09:00:00', '2024-11-01 17:00:00', 150000.00, 2),
    ('Data Scientist', 'Analyze and interpret complex data.', 'Toronto, ON', 'Full-time', '2024-09-10 09:00:00', '2024-12-01 17:00:00', 130000.00, 3),
    ('UX Designer', 'Design user-friendly interfaces and experiences.', 'Sydney, NSW', 'Contract', '2024-09-15 09:00:00', '2024-10-15 17:00:00', 90000.00, 4),
    ('Marketing Specialist', 'Develop and implement marketing strategies.', 'Berlin, DE', 'Part-time', '2024-09-20 09:00:00', '2024-10-20 17:00:00', 70000.00, 5)
ON CONFLICT DO NOTHING;

-- Insertar comunidades
INSERT INTO communities (name, description, created_date, members_count, is_private)
VALUES
    ('Tech Innovators', 'A community for professionals and enthusiasts in technology and innovation.', '2024-09-01 09:00:00', 120, FALSE),
    ('Data Science Enthusiasts', 'A group focused on data science, machine learning, and analytics.', '2024-09-05 09:00:00', 85, TRUE),
    ('Software Development Hub', 'Community for software developers to share knowledge and network.', '2024-09-10 09:00:00', 200, FALSE),
    ('UX/UI Designers Network', 'A network for UX and UI designers to discuss trends and collaborate.', '2024-09-15 09:00:00', 60, TRUE),
    ('Marketing Professionals', 'Group for marketing professionals to share strategies and ideas.', '2024-09-20 09:00:00', 150, FALSE)
ON CONFLICT DO NOTHING;

-- Insertar miembros
INSERT INTO members (user_id, community_id, is_admin, join_date)
VALUES
    ( 2, 1, TRUE, '2024-09-01 10:00:00'),
    ( 3, 2, TRUE, '2024-09-02 11:00:00'),
    (  6, 3, TRUE, '2024-09-03 12:00:00'),
    ( 7, 4, TRUE, '2024-09-04 13:00:00'),
    ( 10, 5, TRUE, '2024-09-05 14:00:00')
ON CONFLICT DO NOTHING;

-- Insertar comentarios en grupos
INSERT INTO comments_groups (content, posted_date, likes_count, member_id)
VALUES
    ('Great discussion on the latest tech trends!', '2024-09-01 10:30:00', 15, 1),
    ('I found this project really innovative and inspiring.', '2024-09-02 11:00:00', 25, 2),
    ('Looking forward to collaborating on this initiative.', '2024-09-03 14:15:00', 8, 3),
    ('The design concepts shared are fantastic.', '2024-09-04 09:45:00', 18, 4),
    ('Excited to be a part of this community and contribute.', '2024-09-05 16:30:00', 22, 5)
ON CONFLICT DO NOTHING;

-- Insertar aplicaciones
INSERT INTO applications (date_created, job_id, applicant_id)
VALUES
    ('2024-09-01 11:00:00', 1, 1),
    ('2024-09-02 12:30:00', 2, 2),
    ('2024-09-03 13:45:00', 3, 3),
    ('2024-09-04 14:20:00', 4, 4),
    ('2024-09-05 15:00:00', 5, 5)
ON CONFLICT DO NOTHING;

--
INSERT INTO job_phases ( name, job_id) VALUES
                                                       ( 'Revisión de CV', 1),
                                                       ( 'Entrevista Técnica', 1),
                                                       ( 'Entrevista con Recursos Humanos', 1),
                                                       ( 'Evaluación Final', 1),
                                                       ( 'Revisión de Portafolio', 2),
                                                       ( 'Entrevista Inicial', 2),
                                                       ( 'Prueba Práctica', 2)
ON CONFLICT DO NOTHING;

INSERT INTO follow_up_applications (application_id, application_date,jobphase_id, status, last_update) VALUES
                                                                                                                       ( 1,'2024-08-01' ,1, 'APPROVED', '2024-09-01'),
                                                                                                                       ( 1, '2024-08-01',2, 'REVISION', '2024-09-05'),
                                                                                                                       ( 2,'2024-08-01' ,1, 'APPROVED', '2024-09-03'),
                                                                                                                       ( 2, '2024-08-01',3, 'RECEIVED', '2024-09-08'),
                                                                                                                       ( 3,'2024-08-01' ,5, 'APPROVED', '2024-09-02'),
                                                                                                                       ( 3, '2024-08-01',6, 'REVISION', '2024-09-10'),
                                                                                                                       ( 4, '2024-08-01',7, 'RECEIVED', '2024-09-04')
ON CONFLICT DO NOTHING;

INSERT INTO applicant_qualifications (followupapplication_id, level) VALUES
                                                                                                           (1, 4 ),
                                                                                                           ( 3, 3), -- No se ha asignado calificación aún
                                                                                                           ( 7, 5),
                                                                                                           ( 5, 2) -- No se ha asignado calificación aún
ON CONFLICT DO NOTHING;


