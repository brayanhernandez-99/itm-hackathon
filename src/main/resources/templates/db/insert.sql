
-- Insertar en miembros - eventos - asistencia - acompañantes - asistencia_acompañantes

USE itm;

INSERT INTO Miembros (nombre, tipo, otros_datos, rol) VALUES ('Brayan Hernandez', 'President', 'Motociclista experimentado', 'ADMIN'),('Juan Pérez', 'Member', 'Buen conductor', 'USER'),('Carlos López', 'Prospect', 'Nuevo en el club', 'USER'),('Ana Gómez', 'Dama', 'Nueva en el club', 'GUEST');
INSERT INTO Eventos (fecha, descripcion, organizador_id, tipo) VALUES ('2025-04-10', 'Ruta de montaña', 1, 'Ruta'),('2025-04-15', 'Encuentro de Motoclubes', 2, 'Reunión');
INSERT INTO Asistencia (miembro_id, evento_id, asistencia_moto, kilometraje) VALUES (1, 1, TRUE, 120),(2, 2, FALSE, 0);
INSERT INTO Acompanantes (nombre, otros_datos) VALUES ('Luis Martínez', 'Amigo de Juan'),('María Rodríguez', 'Pareja de Ana');
INSERT INTO Asistencia_Acompanantes (acompanante_id, evento_id, asistencia_moto, kilometraje) VALUES (1, 1, TRUE, 120),(2, 2, FALSE, 0);

