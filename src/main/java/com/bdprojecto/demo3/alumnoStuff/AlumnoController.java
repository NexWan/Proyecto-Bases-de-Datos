package com.bdprojecto.demo3.alumnoStuff;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.LinkedList;

public class AlumnoController {
    public TitledPane panelCal;
    public TableView tableCal;
    public TableColumn calificacion;
    public TableColumn materia;
    public TableColumn profesor;

    public void InscribirMat(ActionEvent actionEvent) {
        new verMaterias("1");
    }

    public void VerCal(ActionEvent actionEvent) {
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
                    rs = st.executeQuery("INSERT");
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
