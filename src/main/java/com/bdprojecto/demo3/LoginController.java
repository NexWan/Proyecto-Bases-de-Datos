package com.bdprojecto.demo3;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class LoginController extends manageTeacher{

    public TextField user;
    public TextField pass;
    public Button log;

    public void login() throws ClassNotFoundException, SQLException, IOException {
        String userS = user.getText();
        String passS = pass.getText();
        System.out.println(userS + " " + passS);
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","usuarios","usuarios");
        Statement st = con.createStatement();
        String query = String.format("SELECT usuario,password,rol,numTrabajador FROM login WHERE upper(usuario) LIKE upper('%s') AND password='%s'",userS,passS);
        System.out.println(query);
        ResultSet rs = st.executeQuery(query);
        if(rs.next()){
            String rol = rs.getString("ROL");
            int id = rs.getInt("NUMTRABAJADOR");
            System.out.println(id+ " " + rol);
            JOptionPane.showMessageDialog(null,String.format("Hola %s ! %nsu numero de trabajador es: %d y ha sido logeado como: %s",userS,id,rol));
            Stage s = new Stage();
            new mainGuiApp().start(s);
            static_label.setText(String.format("Bienvenido! %s",userS));
        }else{
            JOptionPane.showMessageDialog(null,"Usuario o contrase;a incorrecta");
        }
    }
}
