-- Datos de prueba en  users
INSERT INTO user (role, email, password, date_create, active_account) VALUES
                                                                           ('admin', 'admin@example.com', 'hashed_password_1', CURRENT_TIMESTAMP, true),
                                                                           ('user', 'user1@example.com', 'hashed_password_2', CURRENT_TIMESTAMP, true),
                                                                           ('user', 'user2@example.com', 'hashed_password_3', CURRENT_TIMESTAMP, false),
                                                                           ('moderator', 'moderator@example.com', 'hashed_password_4', CURRENT_TIMESTAMP, true),
                                                                           ('user', 'user3@example.com', 'hashed_password_5', CURRENT_TIMESTAMP, true)
    ON CONFLICT DO NOTHING;

-- Datos de prueba en
INSERT INTO company (company_id, name, website, description, country, email, phone) VALUES
                                                                                        (1, 'TechCorp', 'www.techcorp.com', 'Compañía líder en desarrollo de software', 'USA', 'info@techcorp.com', '+1-800-555-1234'),
                                                                                        (2, 'Innovatech', 'www.innovatech.com', 'Empresa enfocada en innovación tecnológica', 'Germany', 'contact@innovatech.com', '+49-800-555-5678'),
                                                                                        (3, 'EduLearn', 'www.edulearn.org', 'Plataforma global para educación en línea', 'Canada', 'support@edulearn.org', '+1-416-555-9876'),
                                                                                        (4, 'HealthSolutions', 'www.healthsolutions.com', 'Soluciones tecnológicas para la salud', 'Australia', 'contact@healthsolutions.com', '+61-555-3456'),
                                                                                        (5, 'GreenEnergy', 'www.greenenergy.com', 'Empresa de energías renovables', 'UK', 'info@greenenergy.com', '+44-800-555-7890')
    ON CONFLICT DO NOTHING;

-- Datos tabla job
INSERT INTO job (job_id, company_id, title, description, location, job_type, posted_date, deadline, salary) VALUES
                                                                                                                (1, 1, 'Software Developer', 'Desarrollo de software en diversos lenguajes y plataformas', 'New York, USA', 'Full-time', CURRENT_DATE, CURRENT_DATE + INTERVAL '30 days', 70000),
                                                                                                                (2, 2, 'Data Scientist', 'Análisis y procesamiento de grandes volúmenes de datos', 'Berlin, Germany', 'Part-time', CURRENT_DATE, CURRENT_DATE + INTERVAL '45 days', 85000),
                                                                                                                (3, 3, 'Project Manager', 'Gestión de proyectos educativos en línea', 'Toronto, Canada', 'Full-time', CURRENT_DATE, CURRENT_DATE + INTERVAL '60 days', 90000),
                                                                                                                (4, 4, 'Healthcare IT Specialist', 'Soporte técnico y desarrollo de soluciones IT en salud', 'Sydney, Australia', 'Full-time', CURRENT_DATE, CURRENT_DATE + INTERVAL '30 days', 75000),
                                                                                                                (5, 5, 'Renewable Energy Engineer', 'Diseño e implementación de soluciones de energía renovable', 'London, UK', 'Contract', CURRENT_DATE, CURRENT_DATE + INTERVAL '90 days', 95000)
    ON CONFLICT DO NOTHING;

-- Datos tabla applicant
INSERT INTO applicant (applicant_id, first_name, last_name, degree, description, country, college, email, phone) VALUES
                                                                                                                     (1, 'Alice', 'Johnson', 'Bachelor in Computer Science', 'Especialista en desarrollo web y mobile', 'USA', 'MIT', 'alice.johnson@example.com', '+1-555-2345'),
                                                                                                                     (2, 'Bob', 'Smith', 'Master in Data Science', 'Experto en análisis de datos y machine learning', 'Germany', 'Technical University of Munich', 'bob.smith@example.com', '+49-555-3456'),
                                                                                                                     (3, 'Carlos', 'Gomez', 'PhD in Education', 'Investigador en tecnología educativa', 'Canada', 'University of Toronto', 'carlos.gomez@example.com', '+1-416-555-9876'),
                                                                                                                     (4, 'David', 'Miller', 'Bachelor in Information Technology', 'Especialista en infraestructura IT en el sector salud', 'Australia', 'University of Sydney', 'david.miller@example.com', '+61-555-3456'),
                                                                                                                     (5, 'Eva', 'Martinez', 'Master in Renewable Energy', 'Ingeniera en proyectos de energía limpia', 'UK', 'University of Cambridge', 'eva.martinez@example.com', '+44-555-7890')
    ON CONFLICT DO NOTHING;

-- Datos en tabla group
INSERT INTO group (name, description, created_date, members_count, is_private) VALUES
                                                                                    ('Developers', 'Grupo para desarrolladores de software', CURRENT_TIMESTAMP, 120, false),
                                                                                    ('Data Science Enthusiasts', 'Comunidad de apasionados por la ciencia de datos', CURRENT_TIMESTAMP, 90, false),
                                                                                    ('Project Managers', 'Grupo de discusión para gestores de proyectos', CURRENT_TIMESTAMP, 60, true),
                                                                                    ('Health IT Professionals', 'Profesionales en tecnología para la salud', CURRENT_TIMESTAMP, 45, false),
                                                                                    ('Renewable Energy Engineers', 'Ingenieros en energías renovables', CURRENT_TIMESTAMP, 30, true)
    ON CONFLICT DO NOTHING;
