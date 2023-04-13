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

public class AdminController implements Initializable {
    public Label welcome;
    public static Label static_welcome;
    public Stage s = new Stage();
    public Button darAlta;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        static_welcome = welcome;
    }

    public void darAlta(ActionEvent actionEvent) throws Exception {
        new AltaUI().start(s);
        ((Stage) darAlta.getScene().getWindow()).close();
    }


    public void altaAdmin(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        new adminAlta();
    }

    public void consulta(ActionEvent actionEvent) throws IOException {
        new ConsultasUI().start(s);
    }

    public void baja(ActionEvent actionEvent) {
        try{
            new darBaja();
        }catch (Exception ignore){

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


