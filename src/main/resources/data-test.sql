-- Datos de prueba en  users

-- Datos de prueba en
INSERT INTO company ("user_id", name, website, description, country, email, phone) VALUES
                                                                                    (1, 'TechCorp', 'www.techcorp.com', 'Compañía líder en desarrollo de software', 'USA', 'info@techcorp.com', '+1-800-555-1234'),
                                                                                    (2, 'Innovatech', 'www.innovatech.com', 'Empresa enfocada en innovación tecnológica', 'Germany', 'contact@innovatech.com', '+49-800-555-5678'),
                                                                                    (3, 'EduLearn', 'www.edulearn.org', 'Plataforma global para educación en línea', 'Canada', 'support@edulearn.org', '+1-416-555-9876'),
                                                                                    (4, 'HealthSolutions', 'www.healthsolutions.com', 'Soluciones tecnológicas para la salud', 'Australia', 'contact@healthsolutions.com', '+61-555-3456'),
                                                                                    (5, 'GreenEnergy', 'www.greenenergy.com', 'Empresa de energías renovables', 'UK', 'info@greenenergy.com', '+44-800-555-7890')
    ON CONFLICT DO NOTHING;
