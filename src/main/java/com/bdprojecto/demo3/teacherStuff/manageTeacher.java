package com.bdprojecto.demo3.teacherStuff;

import com.bdprojecto.demo3.alumnoStuff.Alumno;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class manageTeacher implements Initializable {
    public Label matricula;
    public static Label static_matricula;
    @FXML
    private TableColumn<Alumno,String> nomAlumno;
    public TableColumn<Alumno,Double> calificacion;
    public TableColumn<Alumno,String> Materia;
    @FXML
    private Label label;
    public static Label static_label;

    public int i = 1;

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private TableView<Alumno> tab;

    public void writeData(){

    }

    public void showData() throws SQLException, ClassNotFoundException {
        System.out.println(i);
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","escuela","escuela");
        Statement st = con.createStatement();
        String query = """
            SELECT a.nombre AS nombreAl, m.nombre AS nombreMat, c.calificacion, p.nombre AS nombreProf
            FROM Alumnos a, Materias m, Calificaciones c, Profesores p
            WHERE a.MatriculaAl = c.MatriculaAl
                AND m.idMateria = c.idMateria
                AND m.MatriculaP = p.MatriculaP
                AND p.MatriculaP = """ + matricula.getText() + """
            """;
        ResultSet rs = st.executeQuery(query);
        if(i%2 != 0){
            while(rs.next()){
                System.out.println(rs.getString("MATERIA") + " " + rs.getDouble("CALIFICACION") + " " + rs.getString("NOMBRE"));
                Alumno alumno = new Alumno(rs.getString("NOMBRE"),rs.getDouble("CALIFICACION"),(rs.getString("MATERIA")));
                tab.getItems().add(alumno);
            }
        }else{
            tab.getItems().clear();
        }
        i++;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            static_label = label;
            static_matricula = matricula;
            nomAlumno.setCellValueFactory(new PropertyValueFactory<Alumno,String>("nomAlumno"));
            calificacion.setCellValueFactory(new PropertyValueFactory<Alumno, Double>("calificacion"));
            Materia.setCellValueFactory(new PropertyValueFactory<Alumno, String>("Materia"));
        }catch (Exception e){
            System.out.println("Por alguna razon da un error al iniciar el programa pero no afecta su funcionamiento, solo ignorar!");
            e.printStackTrace();
        }
    }

    public void InscribirAlumno(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","escuela","escuela");
        Statement st = con.createStatement();

    }
}
