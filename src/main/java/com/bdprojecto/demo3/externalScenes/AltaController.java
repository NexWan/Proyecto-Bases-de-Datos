package com.bdprojecto.demo3.externalScenes;

import com.bdprojecto.demo3.teacherStuff.Profesor;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.sql.*;
import java.util.LinkedList;
import java.util.ResourceBundle;

import com.bdprojecto.demo3.alumnoStuff.Alumno;
import com.bdprojecto.demo3.teacherStuff.Profesor;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;

public class AltaController implements Initializable {
    public Label rol;
    public TableColumn<Alumno, String> nombreAlumno;
    public TableColumn<Alumno, String> matriculaAlumno;
    public TableColumn<Profesor, String> nombreProf;
    public TableColumn<Profesor, Integer> matriculaProfe;
    public TableView<Profesor> tabProf;
    public TableView<Alumno> tabAlumno;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            nombreProf.setCellValueFactory(new PropertyValueFactory<Profesor,String >("nombre"));
            matriculaProfe.setCellValueFactory(new PropertyValueFactory<Profesor,Integer>("matricula"));
            nombreAlumno.setCellValueFactory(new PropertyValueFactory<Alumno,String >("nomAlumno"));
            matriculaAlumno.setCellValueFactory(new PropertyValueFactory<Alumno,String>("matricula"));
            LinkedList<String> alumnosPendiente = new LinkedList<>(), profesoresPendiente = new LinkedList<>();
            dbConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void darAlta(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","escuela","escuela");
        Statement st = conn.createStatement();
        if(tabAlumno.getSelectionModel().getSelectedItem() != null){
            Alumno selected = tabAlumno.getSelectionModel().getSelectedItem();
            System.out.println(tabAlumno.getSelectionModel().getSelectedItem());
            try{
                int option = JOptionPane.showConfirmDialog(null,String.format("El siguiente usuario se dara de alta: %s con matricula: %s y rol de Alumno",selected.getNomAlumno(),selected.getMatricula()));
                if(option == JOptionPane.OK_OPTION){
                    String user = selected.getNomAlumno().substring(0,3) + selected.getMatricula();
                    String clave = selected.getNomAlumno().substring(0,0) + selected.getMatricula();
                    String id = selected.getMatricula();
                    String rol = "Alumno";
                    insertTable(st, user, clave, id, rol);
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Una excepcion ha ocurrido!","Error",JOptionPane.ERROR_MESSAGE);
            }
        }else if(tabProf.getSelectionModel().getSelectedItem() != null) {
            Profesor selected = tabProf.getSelectionModel().getSelectedItem();
            try{
                int option = JOptionPane.showConfirmDialog(null,String.format("El siguiente usuario se dara de alta: %s con matricula: %s y rol de Alumno",selected.getNombre(),selected.getMatricula()));
                if(option == JOptionPane.OK_OPTION){
                    String user = selected.getNombre().substring(0,3) + selected.getMatricula();
                    String clave = selected.getNombre().substring(0,0) + selected.getMatricula();
                    String id = selected.getMatricula();
                    String rol = "Profesor";
                    insertTable(st, user, clave, id, rol);
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Una excepcion ha ocurrido!","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void insertTable(Statement st, String user, String clave, String id, String rol) throws SQLException, ClassNotFoundException {
        String query = String.format("INSERT INTO usuarios.users VALUES(%s,'%s','%s','%s')",id,user,clave,rol);
        ResultSet rt = st.executeQuery(query);
        if(rt.next()){
            JOptionPane.showMessageDialog(null,String.format("El usuario: %s con clave: %s y matricula: %s ha sido insertado!",user,id,clave));
            st.execute("commit");
            updateDb();
        }else{
            JOptionPane.showMessageDialog(null,"Ha ocurrido un error!","ERROR!",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateDb() throws SQLException, ClassNotFoundException {
        tabAlumno.getItems().clear();
        tabProf.getItems().clear();
        dbConnection();
    }

    private void dbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","escuela","escuela");
        Statement st = conn.createStatement();
        String queryP = """
                    SELECT nombre, matriculaP
                    FROM escuela.profesores
                    WHERE profesores.matriculaP NOT IN
                    (SELECT u.idUsers
                    FROM usuarios.users u JOIN Escuela.profesores p
                    ON u.idUsers = p.matriculaP
                    )""";
        ResultSet rs = st.executeQuery(queryP);
        while(rs.next()){
            Profesor p = new Profesor(rs.getString("MATRICULAP"),rs.getString("NOMBRE"));
            tabProf.getItems().add(p);
        }
        String queryA = """
                    SELECT nombre, matriculaAl
                    FROM escuela.alumnos\s
                    WHERE alumnos.matriculaAl NOT IN
                    (SELECT u.idUsers
                    FROM usuarios.users u JOIN Escuela.alumnos a
                    ON u.idUsers = a.matriculaAl
                    )
                    """;
        rs = st.executeQuery(queryA);
        while(rs.next()){
            Alumno a = new Alumno(rs.getString("NOMBRE"),rs.getString("MATRICULAAL"));
            tabAlumno.getItems().add(a);
        }
    }

    private void insertIntoTable(){

    }
}
