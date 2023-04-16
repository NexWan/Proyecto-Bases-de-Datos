<h1>Login Stuff</h1>

<h2>Login Class</h2>
    <p>Esta clase es una subclase de javafx.application.Application y se encarga de mostrar la ventana de inicio de sesión para el sistema.</p>
    <h3>Métodos</h2>
    <ul>
      <li><strong>start(Stage stage)</strong>: Este método es llamado cuando se ejecuta la aplicación. Se carga el archivo FXML de la ventana de inicio de sesión y se muestra en pantalla.</li>
      <li><strong>main(String[] args)</strong>: Método principal de la aplicación. Llama al método launch() de la clase Application para iniciar la aplicación.</li>
    </ul>

# LoginController
Controlador de la ventana de login, se encarga de validar los datos del usuario en la base de datos y redirigirlo a la ventana correspondiente.
Dentro de esta clase se encuentran los siguientes métodos:
- **public void login()**<br>
  Metodo encargado de validar los datos del usuario ingresados en la interfaz de login, hace una consulta en la tabla de usuarios y redirige al usuario a la ventana correspondiente dependiendo de su rol (alumno, profesor o admin).
- **public TextField user**<br>
  Atributo que representa el campo de texto donde el usuario ingresa su nombre de usuario.
- **public TextField pass**<br>
  Atributo que representa el campo de texto donde el usuario ingresa su contraseña.
- **public Button log**<br>
  Atributo que representa el botón de login.
