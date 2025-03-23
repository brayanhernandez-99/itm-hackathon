
-- Insertar en miembros - eventos - asistencia - acompañantes - asistencia_acompañantes

TipoEvento:
('Asamblea'), ('Rodada'), ('Sancionado'), ('Cena'), ('Fiesta'), ('Competencia'), ('Viaje');

TipoMiembro:
('President'), ('VIP'), ('Member'), ('Prospect'), ('Dama'), ('Tesorero');

USE itm;

INSERT INTO Miembros (nombre, tipo, descripcion) VALUES
('Juan Pérez', 'President', 'Líder del club'),
('María López', 'Member', 'Miembro activo desde 2020'),
('Carlos García', 'Prospect', 'Nuevo integrante en evaluación'),
('Ana Torres', 'Dama', 'Miembro honorario del club'),
('Luis Ramírez', 'Member', 'Participante activo en rodadas'),
('Sofía Herrera', 'VIP', 'Colaboradora y patrocinadora'),
('Pedro Gómez', 'Tesorero', 'Encargado de finanzas'),
('Laura Méndez', 'Tesorero', 'Asistente de finanzas'),
('Fernando Díaz', 'VIP', 'Miembro distinguido'),
('Miguel Torres', 'Prospect', 'Nuevo integrante en pruebas');

INSERT INTO Eventos (fecha, descripcion, organizador_id, tipo) VALUES
('2024-06-15', 'Rodada nocturna en la ciudad', 1, 'Rodada'),
('2024-07-20', 'Cena de aniversario del club', 2, 'Cena'),
('2024-08-10', 'Asamblea general de miembros', 3, 'Asamblea'),
('2024-09-05', 'Rodada de montaña extrema', 4, 'Rodada'),
('2024-10-30', 'Evento de sanción por normas incumplidas', 5, 'Sancionado'),
('2024-11-12', 'Fiesta de fin de año del club', 6, 'Fiesta'),
('2025-01-15', 'Competencia de velocidad en circuito cerrado', 7, 'Competencia'),
('2025-02-25', 'Planificación de actividades futuras', 8, 'Asamblea'),
('2025-03-20', 'Viaje en caravana por varias ciudades', 9, 'Viaje'),
('2025-04-10', 'Rodada en carretera abierta', 10, 'Rodada');

INSERT INTO Acompanantes (nombre, descripcion) VALUES
('Daniela Suárez', 'Esposa de Juan Pérez'),
('Eduardo Martínez', 'Amigo de María López'),
('Victoria Ramírez', 'Hermana de Carlos García'),
('Raúl Ortega', 'Compañero de Ana Torres'),
('Natalia Gómez', 'Pareja de Luis Ramírez'),
('Roberto Álvarez', 'Invitado especial del club'),
('Elena Paredes', 'Prima de Pedro Gómez'),
('Francisco León', 'Colega de Fernando Díaz'),
('Clara Espinosa', 'Tía de Miguel Torres'),
('Gabriel Ríos', 'Amigo de la infancia de Laura Méndez');

INSERT INTO Asistencia (miembro_id, evento_id, asistencia_moto, kilometraje) VALUES
(1, 1, TRUE, 150),
(2, 2, FALSE, 0),
(3, 3, TRUE, 200),
(4, 4, TRUE, 120),
(5, 5, FALSE, 0),
(6, 6, TRUE, 180),
(7, 7, TRUE, 220),
(8, 8, TRUE, 90),
(9, 9, FALSE, 0),
(10, 10, TRUE, 250);

INSERT INTO Asistencia_Acompanantes (acompanante_id, evento_id, asistencia_moto, kilometraje) VALUES
(1, 1, TRUE, 150),
(2, 2, FALSE, 0),
(3, 3, TRUE, 200),
(4, 4, TRUE, 120),
(5, 5, FALSE, 0),
(6, 6, TRUE, 180),
(7, 7, TRUE, 220),
(8, 8, TRUE, 90),
(9, 9, FALSE, 0),
(10, 10, TRUE, 250);
