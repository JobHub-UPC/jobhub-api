-- Datos de prueba en  users
INSERT INTO "users" (role, email, password, created, active) VALUES
                                                                 ('ADMIN', 'admin@techcorp.com', 'admin123', '2024-09-01 10:00:00', TRUE),
                                                                 ('USER', 'john.doe@gmail.com', 'password123', '2024-09-02 11:15:00', TRUE),
                                                                 ('USER', 'jane.smith@yahoo.com', 'jane456', '2024-09-03 12:30:00', TRUE),
                                                                 ('COMPANY', 'info@innovatech.com', 'innovatech789', '2024-09-04 13:45:00', TRUE),
                                                                 ('USER', 'mike.jones@hotmail.com', 'mike321', '2024-09-05 14:00:00', FALSE)
    ON CONFLICT DO NOTHING;


-- Datos de prueba en
INSERT INTO company (id, name, website, description, country, email, phone) VALUES
                                                                                    (1, 'TechCorp', 'www.techcorp.com', 'Compañía líder en desarrollo de software', 'USA', 'info@techcorp.com', '+1-800-555-1234'),
                                                                                    (2, 'Innovatech', 'www.innovatech.com', 'Empresa enfocada en innovación tecnológica', 'Germany', 'contact@innovatech.com', '+49-800-555-5678'),
                                                                                    (3, 'EduLearn', 'www.edulearn.org', 'Plataforma global para educación en línea', 'Canada', 'support@edulearn.org', '+1-416-555-9876'),
                                                                                    (4, 'HealthSolutions', 'www.healthsolutions.com', 'Soluciones tecnológicas para la salud', 'Australia', 'contact@healthsolutions.com', '+61-555-3456'),
                                                                                    (5, 'GreenEnergy', 'www.greenenergy.com', 'Empresa de energías renovables', 'UK', 'info@greenenergy.com', '+44-800-555-7890')
    ON CONFLICT DO NOTHING;

INSERT INTO job (id, title, description, location, job_type, posted_date, deadline_date, salary, company_id) VALUES
                                                                                                                 (1, 'Desarrollador Backend', 'Responsable de diseñar, crear, y mantener la lógica del lado del servidor en una aplicación web.', 'Remoto', 'Tiempo completo', '2024-09-01 09:00:00', '2024-09-30 17:00:00', 70000.00, 1),
                                                                                                                 (2, 'Ingeniero de DevOps', 'Gestionar y mejorar los sistemas de integración y despliegue continuo, asegurando una infraestructura escalable.', 'Nueva York, USA', 'Tiempo completo', '2024-09-05 10:00:00', '2024-09-29 18:00:00', 85000.00, 2),
                                                                                                                 (3, 'Diseñador UX/UI', 'Crear experiencias de usuario intuitivas y visualmente atractivas para productos web y móviles.', 'Berlín, Alemania', 'Medio tiempo', '2024-08-25 08:30:00', '2024-09-20 16:00:00', 45000.00, 3),
                                                                                                                 (4, 'Científico de Datos', 'Analizar grandes volúmenes de datos para encontrar patrones y desarrollar soluciones basadas en datos.', 'Toronto, Canadá', 'Freelance', '2024-09-10 12:00:00', '2024-10-05 23:59:00', 95000.00, 4),
                                                                                                                 (5, 'Ingeniero de Energías Renovables', 'Diseñar e implementar proyectos que optimicen el uso de energías renovables.', 'Londres, Reino Unido', 'Tiempo completo', '2024-09-15 09:00:00', '2024-10-10 18:00:00', 80000.00, 5)
    ON CONFLICT DO NOTHING;
