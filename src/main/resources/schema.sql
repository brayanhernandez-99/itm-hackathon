CREATE DATABASE IF NOT EXISTS itm;
CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'springuser';
GRANT ALL PRIVILEGES ON itm.* TO 'springuser'@'localhost';
FLUSH PRIVILEGES;

USE itm;

CREATE TABLE Miembros (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL,
    Tipo VARCHAR(50) NOT NULL,
    OtrosDatos VARCHAR(255)
);

CREATE TABLE Eventos (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Fecha DATE NOT NULL,
    Descripcion VARCHAR(255) NOT NULL,
    OrganizadorID INT NOT NULL,
    Tipo VARCHAR(50) NOT NULL,
    FOREIGN KEY (OrganizadorID) REFERENCES Miembros(ID) ON DELETE CASCADE
);

CREATE TABLE Asistencia (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    MiembroID INT NOT NULL,
    EventoID INT NOT NULL,
    AsistenciaMoto BOOLEAN NOT NULL,
    Kilometraje INT DEFAULT 0,
    FOREIGN KEY (MiembroID) REFERENCES Miembros(ID) ON DELETE CASCADE,
    FOREIGN KEY (EventoID) REFERENCES Eventos(ID) ON DELETE CASCADE
);

CREATE TABLE Acompanantes (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL,
    OtrosDatos VARCHAR(255)
);

CREATE TABLE AsistenciaAcompanantes (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    AcompananteID INT NOT NULL,
    EventoID INT NOT NULL,
    AsistenciaMoto BOOLEAN NOT NULL,
    Kilometraje INT DEFAULT 0,
    FOREIGN KEY (AcompananteID) REFERENCES Acompanantes(ID) ON DELETE CASCADE,
    FOREIGN KEY (EventoID) REFERENCES Eventos(ID) ON DELETE CASCADE
);


// Cambié "Acompañantes" por "Acompanantes" para evitar problemas con la "ñ".
// Organizador ahora es un OrganizadorID referenciado a Miembros(ID).
// AsistenciaMoto ahora es BOOLEAN para mayor compatibilidad.
// ON DELETE CASCADE en claves foráneas para borrar datos relacionados automáticamente.
// Kilometraje INT DEFAULT 0 en AsistenciaAcompanantes para consistencia.