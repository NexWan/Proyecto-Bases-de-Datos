package com.bdprojecto.demo3.alumnoStuff;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class AlumnoController implements Initializable {
    public TitledPane panelCal;
    public TableView<Alumno> tableCal;
    public TableColumn<Alumno,String> calificacion;
    public TableColumn<Alumno,String> materia;
    public TableColumn<Alumno,String> profesor;
    public Label matricula;
    public static Label static_matriculaAl;


    public void InscribirMat(ActionEvent actionEvent) {
        new verMaterias(matricula.getText());
    }

    public void VerCal(ActionEvent actionEvent) {
        panelCal.setVisible(true);
        new consultarCal(matricula.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        static_matriculaAl = matricula;
        calificacion.setCellValueFactory(new PropertyValueFactory<Alumno,String>("calificacion"));
        materia.setCellValueFactory(new PropertyValueFactory<Alumno,String>("materia"));
        profesor.setCellValueFactory(new PropertyValueFactory<Alumno,String>("profesor"));
    }

    static class verMaterias{
        private JLabel label = new JLabel();
        private String matriculaAl;

        public verMaterias(String matriculaAl){
            try{
                Connection conn = connDB();
                Statement st = conn.createStatement();
                DefaultTableModel model = new DefaultTableModel();
                ResultSet rs = st.executeQuery("SELECT m.nombre AS materia, m.descripcion, p.nombre AS profesor FROM materias M, profesores P WHERE m.matriculaP = p.matriculaP");
                model.addColumn("materia");
                model.addColumn("Descripcion");
                model.addColumn("Profesor");
                LinkedList<String> list = new LinkedList<>();
                while(rs.next()){
                    Object[] row = new Object[3];
                    row[0] = rs.getString("MATERIA");
                    row[1] = rs.getString("DESCRIPCION");
                    row[2] = rs.getString("PROFESOR");
                    model.addRow(row);
                    list.add(rs.getString("MATERIA"));
                }
                JTable table = new JTable(model);
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(0,1));
                panel.add(table);
                JScrollPane jScrollPane = new JScrollPane(panel);
                jScrollPane.setPreferredSize(new Dimension(400,300));
                JOptionPane.showMessageDialog(null,"Se mostrara a continuacion algunas materias, digita el nombre de la que te quieres inscribir");
                String materia = JOptionPane.showInputDialog(null,jScrollPane);
                if(list.contains(materia)){
                    rs = st.executeQuery(String.format("SELECT idMateria FROM Materias WHERE nombre='%s'",materia));
                    rs.next();
                    String idMater = rs.getString("IDMATERIA");
                    rs = st.executeQuery(String.format("INSERT INTO Calificaciones VALUES(%s,%s,0)",matriculaAl,idMater));
                    if(rs.next()){
                        Platform.runLater(() ->{
                            JOptionPane.showMessageDialog(null,"Se ha inscrito correctamente a la materia!");
                        });
                    }else{
                        Platform.runLater(()->{
                            JOptionPane.showMessageDialog(null,"Ha ocurrido un error! talvez ya este inscrito en la materia!","ERROR",JOptionPane.ERROR_MESSAGE);
                        });
                    }

                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        public Connection connDB() throws SQLException, ClassNotFoundException {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","escuela","escuela");
        }
    }

     class consultarCal{
        private String matricula;
        public consultarCal(String matricula){
            try{
                Connection conn = connDB();
                Statement st = conn.createStatement();
                String query = String.format("SELECT C.calificacion AS calificacion, M.nombre AS materia, P.nombre AS profesor FROM Calificaciones C, Materias M, Profesores P WHERE" +
                        " C.matriculaAl=%s AND C.idMateria = M.idMateria AND M.matriculaP = P.matriculaP",matricula);
                ResultSet rt = st.executeQuery(query);
                while(rt.next()){
                    Alumno al = new Alumno(rt.getString("PROFESOR"),rt.getString("MATERIA"),Double.parseDouble(rt.getString("CALIFICACION")));
                    tableCal.getItems().add(al);
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
         public Connection connDB() throws SQLException, ClassNotFoundException {
             Class.forName("oracle.jdbc.driver.OracleDriver");
             return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","escuela","escuela");
         }
    }
}
