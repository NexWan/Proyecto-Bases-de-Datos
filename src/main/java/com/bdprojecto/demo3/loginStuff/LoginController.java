package com.bdprojecto.demo3.loginStuff;

import com.bdprojecto.demo3.adminStuff.AdminUi;
import com.bdprojecto.demo3.teacherStuff.TeacherUi;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

import static com.bdprojecto.demo3.adminStuff.AdminController.static_welcome;
import static com.bdprojecto.demo3.teacherStuff.manageTeacher.*;

public class LoginController{

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
        String query = String.format("SELECT usuario,rol,idusers FROM users WHERE upper(usuario) LIKE upper('%s') AND clave='%s'",userS,passS);
        System.out.println(query);
        ResultSet rs = st.executeQuery(query);
        Stage s = new Stage();
        if(rs.next()){
            FXMLLoader loader;
            String rol = rs.getString("ROL");
            switch(rol.toLowerCase()){
                case "alumno"->{
                    System.out.println("a");
                }
                case "profesor","profe"->{
                    System.out.println("c");
                    new TeacherUi().start(s);
                    static_label.setText("Welcome! "+userS );
                    static_matricula.setText(rs.getString("IDUSERS"));
                }
                case "admin"->{
                    new AdminUi().start(s);
                    static_welcome.setText(String.format("Welcome! %s",userS));
                }
            }
//            System.out.println(rol);
//            JOptionPane.showMessageDialog(null,String.format("Hola %s ! %nha sido logeado como: %s",userS,rol));
//            new TeacherUi().start(s);
//            static_label.setText(String.format("Bienvenido! %s",userS));
        }else{
            JOptionPane.showMessageDialog(null,"Usuario o contrase;a incorrecta");
        }
    }
}
