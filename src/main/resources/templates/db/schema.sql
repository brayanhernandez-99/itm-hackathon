
CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'springuser';

CREATE DATABASE IF NOT EXISTS itm;
GRANT ALL PRIVILEGES ON itm.* TO 'springuser'@'localhost';
FLUSH PRIVILEGES;

-- Enum para Rol
CREATE TABLE Rol (
    nombre VARCHAR(20) PRIMARY KEY
);

INSERT INTO Rol (nombre) VALUES ('ADMIN'), ('USER'), ('GUEST');

-- Enum para TipoEvento
CREATE TABLE TipoEvento (
    nombre VARCHAR(50) PRIMARY KEY
);

INSERT INTO TipoEvento (nombre) VALUES ('Asamblea'), ('Rodada'), ('Sancionado'), ('Cena'), ('Otro');

-- Enum para TipoMiembro
CREATE TABLE TipoMiembro (
    nombre VARCHAR(50) PRIMARY KEY
);

INSERT INTO TipoMiembro (nombre) VALUES ('President'), ('Member'), ('Prospect'), ('Dama');

-- Tabla Miembros
CREATE TABLE Miembros (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255),
    rol VARCHAR(20) NOT NULL,
    FOREIGN KEY (tipo) REFERENCES TipoMiembro(nombre),
    FOREIGN KEY (rol) REFERENCES Rol(nombre)
);

-- Tabla Eventos
CREATE TABLE Eventos (
    id SERIAL PRIMARY KEY,
    fecha DATE NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    organizador_id INT NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    FOREIGN KEY (organizador_id) REFERENCES Miembros(id),
    FOREIGN KEY (tipo) REFERENCES TipoEvento(nombre)
);

-- Tabla Acompañantes
CREATE TABLE Acompanantes (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255)
);

-- Tabla Asistencia
CREATE TABLE Asistencia (
    id SERIAL PRIMARY KEY,
    miembro_id INT NOT NULL,
    evento_id INT NOT NULL,
    asistencia_moto BOOLEAN NOT NULL,
    kilometraje INT NOT NULL,
    FOREIGN KEY (miembro_id) REFERENCES Miembros(id),
    FOREIGN KEY (evento_id) REFERENCES Eventos(id)
);

-- Tabla Asistencia_Acompanantes
CREATE TABLE Asistencia_Acompanantes (
    id SERIAL PRIMARY KEY,
    acompanante_id INT NOT NULL,
    evento_id INT NOT NULL,
    asistencia_moto BOOLEAN NOT NULL,
    kilometraje INT NOT NULL,
    FOREIGN KEY (acompanante_id) REFERENCES Acompanantes(id),
    FOREIGN KEY (evento_id) REFERENCES Eventos(id)
);