
USE itm;

INSERT INTO Miembros (nombre, tipo, descripcion) VALUES
('Juan Pérez', 'President', 'Líder del grupo'),
('María López', 'Member', 'Miembro activo'),
('Carlos Gómez', 'Prospect', 'Nuevo prospecto'),
('Ana Ramírez', 'Dama', 'Miembro femenino'),
('Luis Torres', 'Member', 'Motociclista experimentado'),
('Sofía Medina', 'Prospect', 'En proceso de admisión'),
('Miguel Rojas', 'Member', 'Participa en eventos'),
('Daniela Castro', 'Dama', 'Encargada de eventos sociales'),
('Javier Sánchez', 'President', 'Líder del grupo en otra sede'),
('Fernanda Ortega', 'Member', 'Asiste regularmente a rodadas');

INSERT INTO Eventos (fecha, descripcion, organizador_id, tipo) VALUES
('2024-04-01', 'Rodada por la montaña', 1, 'Rodada'),
('2024-04-05', 'Cena de bienvenida', 2, 'Cena'),
('2024-04-10', 'Competencia de velocidad', 3, 'Competencia'),
('2024-04-15', 'Fiesta de aniversario', 4, 'Fiesta'),
('2024-04-20', 'Rodada larga distancia', 5, 'Rodada'),
('2024-04-25', 'Asamblea general', 6, 'Asamblea'),
('2024-05-01', 'Viaje a la costa', 7, 'Viaje'),
('2024-05-05', 'Rodada nocturna', 8, 'Rodada'),
('2024-05-10', 'Cena de recaudación de fondos', 9, 'Cena'),
('2024-05-15', 'Rodada de principiantes', 10, 'Rodada');

INSERT INTO Acompanantes (nombre, descripcion) VALUES
('Carlos Herrera', 'Acompañante frecuente de María'),
('Laura Villalobos', 'Esposa de Juan Pérez'),
('Pedro Castillo', 'Hermano de Luis Torres'),
('Andrea Gutiérrez', 'Pareja de Sofía Medina'),
('Ricardo Morales', 'Amigo de Miguel Rojas'),
('Camila Estrada', 'Prima de Daniela Castro'),
('Fernando Mendoza', 'Amigo de Javier Sánchez'),
('Lucía Restrepo', 'Esposa de Fernanda Ortega'),
('Esteban Ramírez', 'Compañero de viaje de Ana Ramírez'),
('Alejandra Salazar', 'Hermana de Carlos Gómez');

INSERT INTO Asistencia (miembro_id, evento_id, asistencia_moto, kilometraje) VALUES
(1, 1, TRUE, 120), (1, 5, TRUE, 250), (1, 7, TRUE, 500),
(2, 2, FALSE, 0), (2, 6, FALSE, 0), (2, 9, FALSE, 0),
(3, 3, TRUE, 200), (3, 8, TRUE, 100), (3, 10, TRUE, 150),
(4, 4, FALSE, 0), (4, 7, FALSE, 0), (4, 9, FALSE, 0),
(5, 5, TRUE, 300), (5, 1, TRUE, 220), (5, 10, TRUE, 180),
(6, 6, FALSE, 0), (6, 3, TRUE, 90), (6, 8, TRUE, 210),
(7, 7, TRUE, 400), (7, 2, FALSE, 0), (7, 5, TRUE, 320),
(8, 8, TRUE, 150), (8, 4, FALSE, 0), (8, 6, FALSE, 0),
(9, 9, FALSE, 0), (9, 10, TRUE, 120), (9, 1, TRUE, 260),
(10, 10, TRUE, 100), (10, 2, FALSE, 0), (10, 7, TRUE, 340);

INSERT INTO Asistencia_Acompanantes (acompanante_id, evento_id, asistencia_moto, kilometraje) VALUES
(1, 1, FALSE, 0), (1, 5, TRUE, 180), (1, 7, TRUE, 350),
(2, 2, FALSE, 0), (2, 6, FALSE, 0), (2, 9, FALSE, 0),
(3, 3, TRUE, 250), (3, 8, TRUE, 120), (3, 10, TRUE, 180),
(4, 4, FALSE, 0), (4, 7, FALSE, 0), (4, 9, FALSE, 0),
(5, 5, TRUE, 270), (5, 1, TRUE, 200), (5, 10, TRUE, 160),
(6, 6, FALSE, 0), (6, 3, TRUE, 80), (6, 8, TRUE, 230),
(7, 7, TRUE, 380), (7, 2, FALSE, 0), (7, 5, TRUE, 310),
(8, 8, TRUE, 130), (8, 4, FALSE, 0), (8, 6, FALSE, 0),
(9, 9, FALSE, 0), (9, 10, TRUE, 90), (9, 1, TRUE, 240),
(10, 10, TRUE, 110), (10, 2, FALSE, 0), (10, 7, TRUE, 330);
