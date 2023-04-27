package com.bdprojecto.demo3.externalScenes;

import com.bdprojecto.demo3.teacherStuff.Profesor;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import com.bdprojecto.demo3.alumnoStuff.Alumno;
import com.bdprojecto.demo3.teacherStuff.Profesor;
import javafx.scene.control.Label;
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
        Platform.runLater(()->{
            try{
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
            }catch (Exception e){
                System.out.println(e);
            }
        });
    }

    private void insertTable(Statement st, String user, String clave, String id, String rol) throws SQLException, ClassNotFoundException {
        try{
//            Con esto se inserta en la tabla de usuarios los valores previamente pasados. Este caso es especifico para dar de alta alumnos y profesores
            String query = String.format("INSERT INTO usuarios.users VALUES(%s,'%s','%s','%s')",id,user,clave,rol);
            ResultSet rt = st.executeQuery(query);
            if(rt.next()){
                JOptionPane.showMessageDialog(null,String.format("El usuario: %s con clave: %s y matricula: %s ha sido insertado!",user,id,clave));
                if(rol.equalsIgnoreCase("profesor") || rol.equalsIgnoreCase("profe")){
                    InputFX inputFX = new InputFX(2, List.of("Seleccione que materia va a dar","descripcion de la materia"));
                    inputFX.showInputDialog();
                    List<String> list = inputFX.getInputValues();
                    query = "SELECT idMateria FROM materias";
                    System.out.println(query);
                    rt = st.executeQuery(query);
                    int i = 0;
                    while(rt.next()){
                        i = rt.getInt("IDMATERIA");
                    }
                    System.out.println(i);
//                    Si el rol es de profesor se pedira que inserte que materia va a dar, por consecuente se tiene que dar de alta esa materia con el id del profesor en la tabla de materias con este query
                    query = String.format("INSERT INTO materias VALUES(%d,'%s','%s',%s)",(i+1),list.get(0),list.get(1),id);
                    rt = st.executeQuery(query);
                    if(rt.next()){
                        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                        a.setContentText("Se ha registrado la materia con exito!");
                        a.show();
                    }
                }
                st.execute("commit");
                updateDb();
            }else{
                Platform.runLater(()->{
                    JOptionPane.showMessageDialog(null,"Ha ocurrido un error!","ERROR!",JOptionPane.ERROR_MESSAGE);
                });
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
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
//        En los 2 querys se selecciona todos los usuarios dentro de la escuela que no esten registrados en la base de datos de los usuarios
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
}
