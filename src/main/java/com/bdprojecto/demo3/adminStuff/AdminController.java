package com.bdprojecto.demo3.adminStuff;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

import com.bdprojecto.demo3.externalScenes.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;

/*
* The class AdminController is the class in charge of handling all events in the app.
*/

public class AdminController implements Initializable {
    /*Declaration of objects */
    public Label welcome;
    public static Label static_welcome;
    public Stage s = new Stage();
    public Button darAlta;
/*Metodo inicializable, funciona para poder dar un valor a un objeto a la hora de iniciar el programa/clase*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        static_welcome = welcome;
    }
    /*La funcion darAlta funciona para poder dar de alta a los usuarios*/
    public void darAlta(ActionEvent actionEvent) throws Exception {
        new AltaUI().start(s);
        ((Stage) darAlta.getScene().getWindow()).close();
    }

    /*Es lo mismo que la clase darAlta, solamente que este es un simple Javafx en vez de toda una clase
    * Ya que solamente servira para registrar usuarios con rol de administrador y estos como tal no tienen muchos atributos*/
    public void altaAdmin(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        new adminAlta();
    }
    /*Metodo que llama a la clase consultas para poder ver todos los datos que estan registrados*/
    public void consulta(ActionEvent actionEvent) throws IOException {
        new ConsultasUI().start(s);
    }
//    Metodo que llama a la clase statica darBaja para poder dar de baja a algun usuario
    public void baja(ActionEvent actionEvent) {
        try{
            new darBaja();
        }catch (Exception ignore){

        }
    }
    /*Metodo de registrar, en este metodo se encuentran conexiones y querys que ayudan a insertar usuarios en la base de datos
    * Este sirve para dar de alta en el sistema de escuela los profesores/alumnos*/
    public void registrar(ActionEvent actionEvent) {
        try{
//            Conexion a la base de datos de escuela/escuela
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","escuela","escuela");
            Statement st = conn.createStatement();
//            Uso de mi clase personalizada InputFX para poder registrar datos
            InputFX inputFX = new InputFX(6,List.of("Matricula","Nombre","Apellido Paterno","Apellido Materno","Correo","Rol del usuario (profesor/alumno)"));
            inputFX.showInputDialog();
            List<String> list = inputFX.getInputValues();
            ResultSet rs = null;
            if(list.get(5).equalsIgnoreCase("Profesor")){
                String query = String.format("INSERT INTO profesores VALUES(%s,'%s','%s','%s','%s')",list.get(0),list.get(1),list.get(2),list.get(3),list.get(4));
                rs = st.executeQuery(query);
            }else if(list.get(5).equalsIgnoreCase("alumno")){
                String query = String.format("INSERT INTO Alumnos VALUES(%s,'%s','%s','%s','%s')",list.get(0),list.get(1),list.get(2),list.get(3),list.get(4));
                rs = st.executeQuery(query);
            }else{
                Platform.runLater(()->{
                    JOptionPane.showMessageDialog(null,"ERROR! verifica que el rol sea alumno o profesor.","ERROR!",JOptionPane.ERROR_MESSAGE);
                });
                return;
            }
            if (rs.next()) {
                Platform.runLater(()->{
                    JOptionPane.showMessageDialog(null,"El usuario se ha insertado con exito!","Confirmacion",JOptionPane.INFORMATION_MESSAGE);
                });
            }else{
                JOptionPane.showMessageDialog(null,"Ha ocurrido un error!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static class adminAlta{
        adminAlta() throws SQLException, ClassNotFoundException {
            Connection conn = connectDB();
            Statement st = getStatement(conn);
            InputFX inputFX = new InputFX(3, List.of("ID aministrador","Nombre de usuario","Clave de acceso"));
            inputFX.showInputDialog();
            var list = inputFX.getInputValues();
            if(!list.get(0).isBlank() && !list.get(1).isBlank() && !list.get(2).isBlank()){
                try{
                    ResultSet rs = st.executeQuery(String.format("INSERT INTO users VALUES(%d,'%s','%s','ADMIN')",Integer.parseInt(list.get(0)),list.get(1),list.get(2)));
                    if(rs.next()) {
                        Platform.runLater(() ->{
                            JOptionPane.showMessageDialog(null, "Usuario insertado con exito!");
                        });
                    }
                }catch (Exception e){
                    Platform.runLater(()->{
                        JOptionPane.showMessageDialog(null,"Ha ocurrido un error!, se mostrara el error a continuacion","ERROR!",JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(null,e.getMessage(),"MENSAJE DE ERROR",JOptionPane.ERROR_MESSAGE);
                    });
                }
            }
        }

        Connection connectDB() throws SQLException, ClassNotFoundException {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","usuarios","usuarios");
        }

        Statement getStatement(Connection con) throws SQLException {
            return con.createStatement();
        }
    }

    static class darBaja{
        darBaja(){
            try{
                Connection connect = connectDb();
                Statement st = connect.createStatement();
                Platform.runLater(() ->{
                    JOptionPane.showMessageDialog(null,"A continuacion se abrira una pestaña con todos los usuarios, una vez que cierre esta nueva ventana podra seleccionar el usuario a eliminar","ATENCION",JOptionPane.INFORMATION_MESSAGE);
                    try {
                        openConsultas();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    String matricula = JOptionPane.showInputDialog(null,"Teclea la matricula/id del usuario que desea eliminar");
                    try {
                        ResultSet rt = st.executeQuery(String.format("DELETE FROM users WHERE idUsers=%s",matricula));
                        if(rt.next()){
                            JOptionPane.showMessageDialog(null,String.format("El usuario con id %s ha sido eliminado!",matricula));
                        }else{
                            JOptionPane.showMessageDialog(null,"No se elimino nada! verifique el id","ERROR!",JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                });
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        Connection connectDb() throws ClassNotFoundException, SQLException {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","usuarios","usuarios");
        }

        void openConsultas() throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/bdprojecto/demo3/Consultas.FXML"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Consultas");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
    }
}


