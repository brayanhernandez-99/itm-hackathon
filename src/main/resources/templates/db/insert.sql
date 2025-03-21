USE itm;

-- Insertar Miembros
INSERT INTO Miembros (nombre, tipo, otros_datos) VALUES
('Juan Pérez', 'Presidente', 'Fundador del club'),
('María López', 'Tesorero', 'Encargada de finanzas'),
('Carlos Gómez', 'Miembro', 'Participa en eventos'),
('Ana Martínez', 'Miembro', 'Nueva en el club');

-- Insertar Eventos
INSERT INTO Eventos (fecha, descripcion, organizador_id, tipo) VALUES
('2025-04-10', 'Ruta a la montaña', 1, 'Paseo'),
('2025-05-15', 'Reunión mensual', 2, 'Reunión'),
('2025-06-20', 'Viaje a la playa', 3, 'Paseo');

-- Insertar Asistencia de Miembros
INSERT INTO Asistencia (miembro_id, evento_id, asistencia_moto, kilometraje) VALUES
(1, 1, TRUE, 120),
(2, 1, TRUE, 100),
(3, 2, FALSE, 0),
(4, 3, TRUE, 200);

-- Insertar Acompañantes
INSERT INTO Acompanantes (nombre, otros_datos) VALUES
('Luis Ramírez', 'Esposo de María'),
('Sofía Torres', 'Amiga de Ana');

-- Insertar Asistencia de Acompañantes
INSERT INTO Asistencia_Acompanantes (acompanante_id, evento_id, asistencia_moto, kilometraje) VALUES
(1, 1, TRUE, 120),
(2, 3, FALSE, 0);
