--Tabla departamento
DROP TABLE IF EXISTS Departamento;
CREATE TABLE Departamento
(
    id_departamento INTEGER PRIMARY KEY,
    id_jefe         INTEGER,
    nombre          TEXT NOT NULL,
    presupuesto     REAL
);
INSERT INTO Departamento (id_departamento, id_jefe, nombre, presupuesto)
VALUES (1, 1, 'Análisis', 200000);
INSERT INTO Departamento (id_jefe, nombre, presupuesto)
VALUES (3, 'Programación', 400000);

--Tabla programador
DROP TABLE IF EXISTS Programador;
CREATE TABLE Programador
(
    id_programador  INTEGER PRIMARY KEY,
    id_departamento INTEGER,
    nombre          TEXT NOT NULL,
    lenguaje        TEXT NOT NULL,
    salario         REAL
);
INSERT INTO Programador (id_programador, id_departamento, nombre, lenguaje, salario)
VALUES (1, 1, 'Alfredo', 'Java', 3000);
INSERT INTO Programador (id_departamento, nombre, lenguaje, salario)
VALUES (1, 'Sebastián', 'Java', 3000);
INSERT INTO Programador (id_departamento, nombre, lenguaje, salario)
VALUES (2, 'María', 'Python', 3000);
INSERT INTO Programador (id_departamento, nombre, lenguaje, salario)
VALUES (2, 'Sandra', 'Python', 3000);
