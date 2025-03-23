-- Crear usuario y base de datos
CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'springuser';
CREATE DATABASE itm;
GRANT ALL PRIVILEGES ON itm.* TO 'springuser'@'localhost';
FLUSH PRIVILEGES;

-- Usar la base de datos
USE itm;

-- Enum para Rol
CREATE TABLE Rol (
    nombre VARCHAR(20) PRIMARY KEY
) ENGINE=InnoDB;

INSERT INTO Rol (nombre) VALUES ('ADMIN'), ('USER'), ('GUEST');

-- Enum para TipoEvento
CREATE TABLE TipoEvento (
    nombre VARCHAR(50) PRIMARY KEY
) ENGINE=InnoDB;

INSERT INTO TipoEvento (nombre) VALUES ('Asamblea'), ('Rodada'), ('Sancionado'), ('Cena'), ('Otro');

-- Enum para TipoMiembro6
CREATE TABLE TipoMiembro (
    nombre VARCHAR(50) PRIMARY KEY
) ENGINE=InnoDB;

INSERT INTO TipoMiembro (nombre) VALUES ('President'), ('Member'), ('Prospect'), ('Dama');

-- Tabla Miembros
CREATE TABLE Miembros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255),
    rol VARCHAR(20) NOT NULL,
    FOREIGN KEY (tipo) REFERENCES TipoMiembro(nombre),
    FOREIGN KEY (rol) REFERENCES Rol(nombre)
) ENGINE=InnoDB;

-- Tabla Eventos
CREATE TABLE Eventos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    organizador_id INT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    FOREIGN KEY (organizador_id) REFERENCES Miembros(id),
    FOREIGN KEY (tipo) REFERENCES TipoEvento(nombre)
) ENGINE=InnoDB;

-- Tabla Acompañantes
CREATE TABLE Acompanantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255)
) ENGINE=InnoDB;

-- Tabla Asistencia
CREATE TABLE Asistencia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    miembro_id INT NOT NULL,
    evento_id INT NOT NULL,
    asistencia_moto TINYINT(1) NOT NULL,
    kilometraje INT NOT NULL,
    FOREIGN KEY (miembro_id) REFERENCES Miembros(id),
    FOREIGN KEY (evento_id) REFERENCES Eventos(id)
) ENGINE=InnoDB;

-- Tabla Asistencia_Acompanantes
CREATE TABLE Asistencia_Acompanantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    acompanante_id INT NOT NULL,
    evento_id INT NOT NULL,
    asistencia_moto TINYINT(1) NOT NULL,
    kilometraje INT NOT NULL,
    FOREIGN KEY (acompanante_id) REFERENCES Acompanantes(id),
    FOREIGN KEY (evento_id) REFERENCES Eventos(id)
) ENGINE=InnoDB;
