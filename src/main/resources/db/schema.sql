-- Crear usuario
CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'springuser';

-- Crear base de datos
CREATE DATABASE IF NOT EXISTS itm;
GRANT ALL PRIVILEGES ON itm.* TO 'springuser'@'localhost';
FLUSH PRIVILEGES;
USE itm;

CREATE TABLE Miembros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    tipo ENUM('President', 'Member', 'Prospect', 'Dama') NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE Eventos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    organizador_id INT NOT NULL,
    tipo ENUM('Asamblea', 'Rodada', 'Sancionado', 'Cena', 'Fiesta', 'Competencia', 'Viaje') NOT NULL,
    FOREIGN KEY (organizador_id) REFERENCES Miembros(id) ON DELETE CASCADE
);

CREATE TABLE Acompanantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE Asistencia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    miembro_id INT NOT NULL,
    evento_id INT NOT NULL,
    asistencia_moto BOOLEAN NOT NULL,
    kilometraje INT NOT NULL,
    FOREIGN KEY (miembro_id) REFERENCES Miembros(id) ON DELETE CASCADE,
    FOREIGN KEY (evento_id) REFERENCES Eventos(id) ON DELETE CASCADE
);

CREATE TABLE Asistencia_Acompanantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    acompanante_id INT NOT NULL,
    evento_id INT NOT NULL,
    asistencia_moto BOOLEAN NOT NULL,
    kilometraje INT NOT NULL,
    FOREIGN KEY (acompanante_id) REFERENCES Acompanantes(id) ON DELETE CASCADE,
    FOREIGN KEY (evento_id) REFERENCES Eventos(id) ON DELETE CASCADE
);
