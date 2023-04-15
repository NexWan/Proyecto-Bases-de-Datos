package com.bdprojecto.demo3.teacherStuff;

import com.bdprojecto.demo3.alumnoStuff.Alumno;
import com.bdprojecto.demo3.externalScenes.InputFX;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class manageTeacher implements Initializable {
    public Label matricula;
    public static Label static_matricula;
    public TableColumn<Alumno,String> matriculaAl;
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
        Platform.runLater(()->{
            try{
                InputFX inputFX = new InputFX(2, List.of("Matricula de alumno","Calificacion"));
                inputFX.showInputDialog();
                List<String> list = inputFX.getInputValues();
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","escuela","escuela");
                Statement st = con.createStatement();
                String query = String.format("UPDATE calificaciones SET calificacion= %s " +
                        "WHERE idMateria = (SELECT idMateria FROM materias WHERE matriculaP=%s) AND matriculaAl = %s",list.get(1),matricula.getText(),list.get(0));
                ResultSet rs = st.executeQuery(query);
                if(rs.next()){
                    JOptionPane.showMessageDialog(null,"Calificacion actualizada!");
                }else{
                    JOptionPane.showMessageDialog(null,"No se pudo actualizar la calificacion! verifique los datos","ERROR!",JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void showData() throws SQLException, ClassNotFoundException {
        System.out.println(i);
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","escuela","escuela");
        Statement st = con.createStatement();
        String query = """
            SELECT a.nombre AS nombreAl, m.nombre AS nombreMat, c.calificacion, p.nombre AS nombreProf, a.matriculaAl
            FROM Alumnos a, Materias m, Calificaciones c, Profesores p
            WHERE a.MatriculaAl = c.MatriculaAl
                AND m.idMateria = c.idMateria
                AND m.MatriculaP = p.MatriculaP
                AND p.MatriculaP = """ + matricula.getText() + """
            """;
        ResultSet rs = st.executeQuery(query);
        if(i%2 != 0){
            while(rs.next()){
                System.out.println(rs.getString("nombreMat") + " " + rs.getString("calificacion") + " " + rs.getString("nombreAl"));
                Alumno alumno = new Alumno(rs.getString("nombreAl"), Double.parseDouble(rs.getString("CALIFICACION")),(rs.getString("nombreMat")),rs.getString("MatriculaAL"));
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
            matriculaAl.setCellValueFactory(new PropertyValueFactory<Alumno,String>("matricula"));
        }catch (Exception e){
            System.out.println("Por alguna razon da un error al iniciar el programa pero no afecta su funcionamiento, solo ignorar!");
            e.printStackTrace();
        }
    }
}
