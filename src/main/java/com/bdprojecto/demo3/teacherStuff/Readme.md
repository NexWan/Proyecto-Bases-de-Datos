# Teacher Stuff
En esta carpeta se encuentra todo lo relacionado a la ventana del usuario tipo profesor.
ManageTeacher

## Manage teacher
La clase 'manageTeacher' es un controlador de la ventana de gestion de profesores, la cual permite visualizar la lista de alumnos que imparte un profesor, y modificar sus calificaciones. La clase se encarga de manejar eventos y la lógica detrás de ellos.

Dentro de esta clase se encuentran las siguientes funciones:

-    public void writeData(): <br>Método que muestra un cuadro de diálogo en el que el usuario debe ingresar la matricula del alumno y su nueva calificación. Luego, actualiza la tabla de calificaciones en la base de datos correspondiente a la materia impartida por el profesor.
-    public void showData(): <br>Método que consulta la base de datos y muestra la lista de alumnos con sus calificaciones en una tabla. Los datos se obtienen mediante una consulta SQL.
-    public void initialize():<br> Método que implementa la interfaz 'Initializable' y se encarga de dar valores iniciales a objetos/parámetros dentro de la clase.
Además, se declaran variables que hacen referencia a los elementos de la interfaz gráfica de usuario.

<h1>TeacherUi</h1>
    <p>Clase que representa la interfaz de usuario para la funcionalidad de profesores.</p>
    <h2>start</h2>
    <p>Método que inicia la interfaz de usuario.</p>
    <ul>
      <li><code>stage</code>: instancia de la ventana principal de JavaFX</li>
      <li><code>fxmlLoader</code>: cargador de archivos FXML para la interfaz de usuario</li>
      <li><code>scene</code>: escena de JavaFX que contiene los elementos de la interfaz de usuario</li>
    </ul>
    <h3>main</h3>
    <p>Método principal de la clase, encargado de lanzar la aplicación.</p>
    <ul>
      <li><code>args</code>: argumentos de la línea de comandos</li>
    </ul>

## Profesor
Clase de tipo profesor, un objeto cuya finalidad es la de guardar informacion de forma temporal y poder acceder a ella de forma mas facil.