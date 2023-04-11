package com.bdprojecto.demo3;

import com.bdprojecto.demo3.teacherStuff.TeacherUi;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.*;

import java.io.IOException;

public class secondApp extends Application {

    public static void main(String[] args) {
        System.out.println("Hola");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("secondFXML.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
//        Handling the close event, so it opens again the main container app
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
//                Closing message
                JOptionPane.showMessageDialog(null,"Closing");
                Stage s = new Stage();
                try {
                    new TeacherUi().start(s);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
