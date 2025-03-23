
-- Insertar en miembros - eventos - asistencia - acompañantes - asistencia_acompañantes

USE itm;

INSERT INTO Miembros (nombre, tipo, descripcion, rol) VALUES
('Juan Pérez', 'President', 'Líder del club', 'ADMIN'),
('María López', 'Member', 'Miembro activo', 'USER'),
('Carlos García', 'Prospect', 'Nuevo integrante', 'USER'),
('Ana Torres', 'Dama', 'Miembro honorario', 'GUEST'),
('Luis Ramírez', 'Member', 'Participante activo', 'USER');

INSERT INTO Eventos (fecha, descripcion, organizador_id, tipo) VALUES
('2024-06-15', 'Rodada nocturna', 1, 'Rodada'),
('2024-07-20', 'Cena de aniversario', 2, 'Cena'),
('2024-08-10', 'Asamblea general', 1, 'Asamblea'),
('2024-09-05', 'Rodada de montaña', 3, 'Rodada'),
('2024-10-30', 'Evento de sanción', 4, 'Sancionado');

INSERT INTO Acompanantes (nombre, descripcion) VALUES
('Pedro Gómez', 'Amigo de Juan'),
('Laura Méndez', 'Esposa de Carlos'),
('Miguel Torres', 'Hermano de Ana'),
('Sofía Herrera', 'Novia de Luis'),
('Fernando Díaz', 'Compañero de María');

INSERT INTO Asistencia (miembro_id, evento_id, asistencia_moto, kilometraje) VALUES
(1, 4, TRUE, 150),
(2, 5, FALSE, 0),
(3, 8, TRUE, 200),
(4, 9, TRUE, 120),
(5, 10, FALSE, 0),
(1, 11, TRUE, 180),
(3, 12, TRUE, 220),
(5, 13, TRUE, 90);

INSERT INTO Asistencia_Acompanantes (acompanante_id, evento_id, asistencia_moto, kilometraje) VALUES
(1, 4, TRUE, 150),
(2, 5, FALSE, 0),
(3, 8, TRUE, 200),
(4, 9, TRUE, 120),
(5, 10, FALSE, 0),
(1, 11, TRUE, 180),
(3, 12, TRUE, 220),
(5, 13, TRUE, 90);
