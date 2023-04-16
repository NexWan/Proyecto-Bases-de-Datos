# External Scenes
Son clases cuyo proposito es servir como "funciones internas" de las clases principales que son alumnos,profesores,admin y login.
Dentro de estas escenas/clases se encontraran las siguientes:
## Alta Controller
Controlador de la clase/ventana alta, se encarga de manejar todos los eventos y la logica detras de estos.
> - **public void Initialize()**<br>
>   Metodo que se implementa de la clase 'Initializable' su funcion es dar valores iniciales a objetos/parametros dentro de la clase.
> - **public void darAlta(ActionEvent actionEvent)**<br>
>   Metodo que se encarga de un ActionEvent del tipo button, su funcion es dar de alta a usuarios dentro de la base de datos de 'usuarios', se pide al usuario en este caso admin que introduzca los datos necesarios y llamara a la funcion insertTable
> - **public void insertTable(Statement st,String user, String clave,String id,String rol)**<br>
>   Este metodo es complemento de la funcion darAlta, dentro de esta funcion se usan los datos previamente registrados en la funcion darAlta y mediante querys se inserta dicho usuario en la tabla users de la base usuarios.
> - **public void dbConnection()**<br>
>   Hace conexion a la base de datos y en este caso tambien agrega los objetos de tipo profesor/alumno que encuentre dentro de la base de datos users.
> - **public void updateDb()**<br>
>   Actualiza la tabla que contiene a los usuarios.
## AltaUI
Esta clase contiene lo necesario para poder inicializar una ventana/stage, carga tambien su FXML.
## ConsultasController
La clase ConsultasController es un controlador para la escena de consultas que maneja la lógica detrás de la tabla que muestra los usuarios en la interfaz de usuario.
<ul>
  <li>
    <strong>initialize</strong>: Método que se ejecuta al iniciar la clase y que se implementa de la interfaz Initializable. Se encarga de inicializar las columnas de la tabla y cargar los datos de la base de datos.
  </li>
  <li>
    <strong>setStage</strong>: Método que recibe un objeto de tipo Stage y lo guarda en una variable local para ser utilizado posteriormente.
  </li>
  <li>
    <strong>connDb</strong>: Método que recibe un usuario y una contraseña para hacer conexión con la base de datos utilizando el driver de Oracle.
  </li>
</ul>

## InputFX
La clase InputFX funciona como un JOptionPane con varios JTextFields, la diferencia es que está hecho con JavaFX y puede pasar directamente desde el constructor la cantidad de filas y sus etiquetas.
<ul>
  <li><b>Constructor:</b> Crea un nuevo objeto InputFX con un número específico de campos de entrada y etiquetas.</li>
  <li><b>showInputDialog():</b> Muestra una ventana de diálogo con los campos de entrada y etiquetas especificados en el constructor. El usuario puede ingresar valores en los campos de entrada y hacer clic en "OK" para cerrar la ventana. Los valores ingresados se almacenan en una lista.</li>
  <li><b>getInputValues():</b> Devuelve una lista de los valores ingresados por el usuario en los campos de entrada.</li>
  <li><b>start(Stage primaryStage):</b> El método start() se sobrescribe como parte de la implementación de la clase Application de JavaFX, pero no se utiliza en InputFX.</li>
</ul>
