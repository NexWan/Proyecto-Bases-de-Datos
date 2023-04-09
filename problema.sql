CREATE TABLE Profesores (
                            MatriculaP NUMERIC(5) NOT NULL,
                            nombre VARCHAR(20),
                            apellidoM VARCHAR(20),
                            apellidoP VARCHAR(20),
                            correo VARCHAR(30),
                            PRIMARY KEY(MatriculaP)
);

CREATE TABLE Alumnos (
                         MatriculaAl NUMERIC(5) NOT NULL,
                         nombre VARCHAR(20),
                         apellidoP VARCHAR(20),
                         apellidoM VARCHAR(20),
                         correo VARCHAR(30),
                         PRIMARY KEY(MatriculaAl)
);

CREATE TABLE Aulas (
                       numAula NUMERIC(2) NOT NULL,
                       capacidad NUMERIC(2),
                       PRIMARY KEY(numAula)
);

CREATE TABLE Materias (
                          idMateria INTEGER NOT NULL,
                          MatriculaAl NUMERIC(5) NOT NULL,
                          MatriculaP NUMERIC(5) NOT NULL,
                          nombre VARCHAR(20),
                          descripcion VARCHAR(30),
                          PRIMARY KEY(idMateria, MatriculaAl, MatriculaP),
                          FOREIGN KEY(MatriculaAl) REFERENCES Alumnos(MatriculaAl),
                          FOREIGN KEY(MatriculaP) REFERENCES Profesores(MatriculaP),
                          FOREIGN KEY(numAula) REFERENCES Aulas(numAula)
);

CREATE TABLE Calificaciones (
                                idCalificacion INTEGER NOT NULL,
                                idMateria INTEGER NOT NULL,
                                MatriculaAl NUMERIC(5) NOT NULL,
                                MatriculaP NUMERIC(5) NOT NULL,
                                calificacion INTEGER,
                                PRIMARY KEY(idCalificacion),
                                FOREIGN KEY(idMateria, MatriculaAl, MatriculaP) REFERENCES Materias(idMateria, MatriculaAl, MatriculaP)
);