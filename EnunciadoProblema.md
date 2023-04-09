# Escuela
### A partir del siguiente enunciado se desea realizar el modelo entidad-relación para la gestión de una escuela.
 La escuela tiene varios profesores que imparten distintas materias a los alumnos.
 Los profesores tienen un nombre, apellido, correo electrónico y una asignatura que imparten.
 Los alumnos tienen un nombre, apellido, correo electrónico y una lista de asignaturas en las que están matriculados.
 Cada asignatura tiene un nombre, una descripción y calificacion del alumno. Los profesores pueden dar clases a muchos alumnos y los alumnos pueden tener varios profesores.
 Además, cada asignatura es impartida por un solo profesor, pero un profesor puede impartir varias asignaturas.
 Una materia puede contener muchas calificaciones y las calificaciones pueden ser de muchas materias.
 Por último, se desea saber en qué aulas se imparten las clases. Las aulas tienen un nombre y una capacidad máxima de estudiantes.
 
Tambien por otro lado se desea llevar un control sobre los usuarios que pueden acceder al sistema, por lo que se necesitara una base de datos
que maneje a los usuarios, cada usuario tendra que tener un nombre de usuario, contrase;a, fecha de registro, rol y una matricula/numero de registro.
(Cada numero de registro debe de coincidir con la matricula del usuario que se esta registrando/loggeando)
Cada usuario que se cree debe ser verificado si su numero de registro/matricula coincide con una matricula en la base de datos de la escuela, y por ende que esta
no este en uso.

```sql
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
```

![image](school.png)
