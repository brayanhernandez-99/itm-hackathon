
CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'springuser';

CREATE DATABASE IF NOT EXISTS itm;
GRANT ALL PRIVILEGES ON itm.* TO 'springuser'@'localhost';
FLUSH PRIVILEGES;

CREATE TABLE Miembros (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    otros_datos VARCHAR(255),
    rol ENUM('ADMIN', 'USER', 'GUEST') NOT NULL
);

CREATE TABLE Eventos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATE NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    organizador_id INT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    FOREIGN KEY (organizador_id) REFERENCES Miembros(id) ON DELETE CASCADE
);

CREATE TABLE Asistencia (
    id INT PRIMARY KEY AUTO_INCREMENT,
    miembro_id INT NOT NULL,
    evento_id INT NOT NULL,
    asistencia_moto BOOLEAN NOT NULL,
    kilometraje INT NOT NULL,
    FOREIGN KEY (miembro_id) REFERENCES Miembros(id) ON DELETE CASCADE,
    FOREIGN KEY (evento_id) REFERENCES Eventos(id) ON DELETE CASCADE
);

CREATE TABLE Acompanantes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    otros_datos VARCHAR(255)
);

CREATE TABLE Asistencia_Acompanantes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    acompanante_id INT NOT NULL,
    evento_id INT NOT NULL,
    asistencia_moto BOOLEAN NOT NULL,
    kilometraje INT NOT NULL,
    FOREIGN KEY (acompanante_id) REFERENCES Acompanantes(id) ON DELETE CASCADE,
    FOREIGN KEY (evento_id) REFERENCES Eventos(id) ON DELETE CASCADE
);
