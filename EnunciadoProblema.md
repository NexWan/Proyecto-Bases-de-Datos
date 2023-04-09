# Escuela
### A partir del siguiente enunciado se desea realizar el modelo entidad-relación para la gestión de una escuela.
 La escuela tiene varios profesores que imparten distintas materias a los alumnos.
 Los profesores tienen un nombre, apellido, correo electrónico y una asignatura que imparten.
 Los alumnos tienen un nombre, apellido, correo electrónico y una lista de asignaturas en las que están matriculados.
 Cada asignatura tiene un nombre y una descripción. Los profesores pueden dar clases a muchos alumnos y los alumnos pueden tener varios profesores.
 Además, cada asignatura es impartida por un solo profesor, pero un profesor puede impartir varias asignaturas.
 Por último, se desea saber en qué aulas se imparten las clases. Las aulas tienen un nombre y una capacidad máxima de estudiantes.
 
Tambien por otro lado se desea llevar un control sobre los usuarios que pueden acceder al sistema, por lo que se necesitara una base de datos
que maneje a los usuarios, cada usuario tendra que tener un nombre de usuario, contrase;a, fecha de registro, rol y una matricula/numero de registro.
(Cada numero de registro debe de coincidir con la matricula del usuario que se esta registrando/loggeando)
Cada usuario que se cree debe ser verificado si su numero de registro/matricula coincide con una matricula en la base de datos de la escuela, y por ende que esta
no este en uso.