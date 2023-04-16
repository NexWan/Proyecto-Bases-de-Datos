# Alumno stuff
En esta carpeta se encuentran todos los archivos correspondientes a la interfaz de lo que son los alumnos.
<br>Esta interfaz por asi decirlo se encarga de manejar que actividades pueden realizar los usuarios cuyo rol corresponda a estudiante.
<br>
## Alumno
Esta clase simplemente es un objeto de tipo Alumno, su funcion es meramente la de organizar y guardar datos de forma temporal para poder ser usados a la hora de
mostrar tablas con informacion usando metodos de JavaFX.<br>
## AlumnoController
Como su nombre lo indica esta clase tiene la funcion de manejar todos los eventos de la GUI del usuario.
>
>- **Public void InscribirMat(ActionEvent actionEvent)**
><br>Un metodo que tiene como parametro un 'ActionEvent', que corresponde a un evento de tipo button, este metodo llama a una clase estatica y pasa como argumento la matricula del usuario loggeado.
>- **public void VerCal(ActionEvent actionEvent)**<br>
>Metodo que tiene como parametro un ActionEvent, correspondiente a un boton, este metodo lo que hace es llamar a la clase estatica consultarCal tambien pasando como parametro la matricula del usuario.
>- **Public void Initialize**<br>
>Metodo implementable de la clase Initializable, tiene como funcion determinar funciones/objetos con un valor inicial a la hora de iniciar el programa.
>- **static class VerMaterias**<br>
> Esta clase estatica sirve para poder ver las materias a las que el alumno se puede inscribir, mas detalles de los metodos acontinuacion:
>  - **public verMaterias(String matriculaAl)**
>  <br> Constructor de la clase, basicamente dentro de todo este constructor esta toda la logica para mostrar las calificaciones, funciona haciendo la conexion a la base de datos, ejecutando querys y mostrandolos en una JTable, luego pide al usuario por un Input para poder determinar a que clase quiere inscribirse.
>  - public Connection connDB()<br>
>  Este metodo basicamente hace la conexion a la base de datos, regresa como parametro un objeto de tipo Connection.
> - **static class ConsultarCal**
> Esta clase tiene como proposito poder mostrar en una TableView las calificaciones del alumno.
>   - public ConsultarCal(String matriculaAl)<br>
>   Metodo constructor que recibe como parametro la matricula del alumno, aqui se ejecuta nuevamente toda la logica de lo que es la conexion a base de datos,querys y poder mostrar en una tableview las calificaciones del alumno.
>   - public Connecion connDb()<br>
>   Este metodo simplemente conecta a la base de datos y regresa un objeto de tipo Connection.
## AlumnoUI
En esta clase solamente se encuentran los argumentos principales para poder mostrar la ventana de la ui/stage. 
Tambien se carga lo que es el FXML del archivo