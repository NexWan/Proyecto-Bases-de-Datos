package com.bdprojecto.demo3;

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
import java.util.ResourceBundle;

public class manageTeacher implements Initializable {
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
        String query = "SELECT C.materia, C.calificacion, A.nombre FROM calificaciones C, alumno A WHERE C.matricula = A.matricula";
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
            nomAlumno.setCellValueFactory(new PropertyValueFactory<Alumno,String>("nomAlumno"));
            calificacion.setCellValueFactory(new PropertyValueFactory<Alumno, Double>("calificacion"));
            Materia.setCellValueFactory(new PropertyValueFactory<Alumno, String>("Materia"));
            static_label = label;
        }catch (Exception e){
            System.out.println("Por alguna razon da un error al iniciar el programa pero no afecta su funcionamiento, solo ignorar!");
            e.printStackTrace();
        }
    }
}
