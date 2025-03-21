CREATE DATABASE IF NOT EXISTS itm;
CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'springuser';
GRANT ALL PRIVILEGES ON itm.* TO 'springuser'@'localhost';
FLUSH PRIVILEGES;

USE itm;

CREATE TABLE Miembros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    otros_datos VARCHAR(255)
);

CREATE TABLE Eventos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    organizador_id INT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    FOREIGN KEY (organizador_id) REFERENCES Miembros(id) ON DELETE CASCADE
);

CREATE TABLE Asistencia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    miembro_id INT NOT NULL,
    evento_id INT NOT NULL,
    asistencia_moto BOOLEAN NOT NULL,
    kilometraje INT NOT NULL DEFAULT 0,
    FOREIGN KEY (miembro_id) REFERENCES Miembros(id) ON DELETE CASCADE,
    FOREIGN KEY (evento_id) REFERENCES Eventos(id) ON DELETE CASCADE
);

CREATE TABLE Acompanantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    otros_datos VARCHAR(255)
);

CREATE TABLE Asistencia_Acompanantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    acompanante_id INT NOT NULL,
    evento_id INT NOT NULL,
    asistencia_moto BOOLEAN NOT NULL,
    kilometraje INT NOT NULL DEFAULT 0,
    FOREIGN KEY (acompanante_id) REFERENCES Acompanantes(id) ON DELETE CASCADE,
    FOREIGN KEY (evento_id) REFERENCES Eventos(id) ON DELETE CASCADE
);



// Cambié "Acompañantes" por "Acompanantes" para evitar problemas con la "ñ".
// Organizador ahora es un OrganizadorID referenciado a Miembros(ID).
// AsistenciaMoto ahora es BOOLEAN para mayor compatibilidad.
// ON DELETE CASCADE en claves foráneas para borrar datos relacionados automáticamente.
// Kilometraje INT DEFAULT 0 en AsistenciaAcompanantes para consistencia.