# Admin stuff
En esta carpeta se encuentran todos los archivos necesarios para la aplicacion de administrador, esta solamente puede ser accesada
desde el rol admin que se concede a los usuarios de la base de datos.

## AdminController
En esta clase se encuentra todo lo relacionado a los eventos y manipluacion de la base de datos, los metodos son los siguientes:

- <b>Public Void Initialize()</b><br>
    Este metodo se hereda/implementa de la clase Initializable, es un metodo void que tiene como proposito dar varlores inicializados
a ciertos atributos/objetos de la clase
- **public void darAlta()**<br>
Este metodo tiene como proposito el dar de alta a los usuarios, dentro de este como tal no hay nada mas que un llamado a la clase [AltaUI](../externalScenes)
, para ver mas informacion de como funciona este metodo ir a esa carpeta
- **public void altaAdmin()**
<br> Esta clase tiene como funcion el dar de alta a los usuarios con privilegio de "admin", se realiza esto mediante la llamada a la clase estatica adminAlta.
- **public void consulta**<br>
Este metodo llama a la clase [ConsultasUI](../externalScenes) para hacer consulta a todos los datos, si desea saber mas vaya al link de la carpeta.
- **Public void baja**<br>
Este hace llamada a la clase estatica darBaja. Esta clase es la que se encargara de dar de baja a los usuarios de la base de datos de usuarios.
- **public void registrar()**<br>
Este metodo como su nombre lo indica se encarga de registrar a los profesores o alumnos en la base de datos de la escuela, no la base de datos de usuarios que son 2 bases diferentes.
### Clases estaticas
Son clases dentro de la clase que solamente sirven para hacer mas modular el codigo.

- **static class adminAlta**<br>
Esta clase tiene como proposito el poder dal de alta a los usuarios de tipo admin.
    - adminAlta()
  <br> Es el constructor de la clase y tambien basicamente el que contiene toda la logica dentro de la clase, aqui se ejecutan los querys y se hacen conexion a la base.
    - connectDb()<br>
        Conecta a la base de datos usuarios.
    - getStatement()<br>
  regresa un objeto statement para poder ejecutar querys.
- **Static class darBaja**
<br>Esta clase se encarga de dar de baja a los usuarios.
  - darBaja()<br>
  Constructor de la clase y tambien la que tiene toda la logica detras de la clase para ejeuctar querys y conectarse a la base de datos.
  - connectDb()<br>
    Conecta ala base de datos
  - openConsultas()<br>
    Abre una vista para lo que son todos los usuarios registrado en la base usuarios para poder eliminar luego un usuario.

## AdminUI
Esta clase solamente es un contenedor para poder abrir el UI de la clase, tiene un FXML que es el de 
[Admin.fxml](../../../../../resources/com/bdprojecto/demo3/Admin.fxml)
- public void Start()<br>
Inicializador de la clase tipo JavaFX

## Person
Esta clase es un objeto para poder guardar informacion y usarla de forma mas facil.
