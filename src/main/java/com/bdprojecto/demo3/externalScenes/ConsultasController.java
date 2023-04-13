package com.bdprojecto.demo3.externalScenes;

import com.bdprojecto.demo3.adminStuff.Person;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ConsultasController implements Initializable {
    public TableColumn<Person,String> nombre;
    public TableColumn<Person, String> apellido;
    public TableColumn<Person, String> correo;
    public TableColumn<Person, String> rol;
    public TableColumn<Person, String> matricula;
    public TableView<Person> tabla;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nombre.setCellValueFactory(new PropertyValueFactory<Person,String>("nombre"));
        apellido.setCellValueFactory(new PropertyValueFactory<Person,String>("apellido"));
        correo.setCellValueFactory(new PropertyValueFactory<Person,String>("correo"));
        rol.setCellValueFactory(new PropertyValueFactory<Person,String>("rol"));
        matricula.setCellValueFactory(new PropertyValueFactory<Person,String>("matricula"));
        try {
            Connection conn = connDb("usuarios","usuarios");
            Statement st = conn.createStatement();
            ResultSet rt = st.executeQuery("SELECT usuario,rol,idusers FROM users WHERE upper(rol) LIKE upper('admin')");
//            Primero agrego los admin ya que tienen diferentes values a los de los otros
            while(rt.next()){
                Person person = new Person(rt.getString("USUARIO"),"","",rt.getString("ROL"),rt.getString("IDUSERS"));
                tabla.getItems().add(person);
            }
            conn = connDb("escuela","escuela");
            String queryP = """
                    SELECT p.nombre AS nombre, p.apellidoP || ' ' || p.apellidoM AS apellido, p.matriculaP, p.correo, u.rol
                    FROM escuela.profesores p
                    INNER JOIN usuarios.users u
                    ON p.matriculaP = u.idUsers
                    WHERE p.matriculaP = u.idUsers
                    """;
            rt = st.executeQuery(queryP);
            while(rt.next()){
                Person person = new Person(rt.getString("NOMBRE"),rt.getString("APELLIDO"),rt.getString("CORREO"),rt.getString("ROL"),rt.getString("MATRICULAP"));
                tabla.getItems().add(person);
            }
            String queryAl= """
                    SELECT a.nombre AS nombre, a.apellidoP || ' ' || a.apellidoM AS apellido, a.matriculaAl, a.correo, u.rol
                    FROM escuela.alumnos a
                    INNER JOIN usuarios.users u
                    ON a.matriculaAl = u.idUsers
                    WHERE a.matriculaAl = u.idUsers
                    """;
            rt = st.executeQuery(queryAl);
            while(rt.next()){
                Person person = new Person(rt.getString("NOMBRE"),rt.getString("APELLIDO"),rt.getString("CORREO"),rt.getString("ROL"),rt.getString("MATRICULAAL"));
                tabla.getItems().add(person);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public Connection connDb(String user, String pass) throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",user,pass);
    }
}
