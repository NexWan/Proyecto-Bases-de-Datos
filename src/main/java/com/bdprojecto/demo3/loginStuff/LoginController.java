package com.bdprojecto.demo3.loginStuff;

import com.bdprojecto.demo3.adminStuff.AdminUi;
import com.bdprojecto.demo3.alumnoStuff.AlumnoUi;
import com.bdprojecto.demo3.teacherStuff.TeacherUi;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

import static com.bdprojecto.demo3.adminStuff.AdminController.static_welcome;
import static com.bdprojecto.demo3.teacherStuff.manageTeacher.*;
import static com.bdprojecto.demo3.alumnoStuff.AlumnoController.static_matriculaAl;

public class LoginController{

    public TextField user;
    public TextField pass;
    public Button log;

    public void login() throws ClassNotFoundException, SQLException, IOException {
        Platform.runLater(() ->{
            try{
                String userS = user.getText();
                String passS = pass.getText();
                System.out.println(userS + " " + passS);
                Connection con = getConn();
                Statement st = con.createStatement();
//              En este query se selecciona el usuario, rol e id que coincida con el del usuario y la contraseña que se indico en el programa
                String query = String.format("SELECT usuario,rol,idusers FROM users WHERE upper(usuario) LIKE upper('%s') AND clave='%s'",userS,passS);
                System.out.println(query);
                ResultSet rs = st.executeQuery(query);
                Stage s = new Stage();
//                A continuacion si el Query es correcto se loggeara al usuario y se mostrara su informacion en un mensaje
                if(rs.next()){
                    String rol = rs.getString("ROL");
                    JOptionPane.showMessageDialog(null,String.format("Bienvenido! %s, ha sido loggeado como: %s",userS,rol));
                    switch(rol.toLowerCase()){
                        case "alumno"->{
                            new AlumnoUi().start(s);
                            static_matriculaAl.setText(rs.getString("IDUSERS"));
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
                }else{
//                    Si no se nidicara que la contraseña o usuario son incorrectos
                    JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrecta");
                }
            }catch (Exception ignore){

            }
        });
    }

    public Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","usuarios","usuarios");
    }
}
