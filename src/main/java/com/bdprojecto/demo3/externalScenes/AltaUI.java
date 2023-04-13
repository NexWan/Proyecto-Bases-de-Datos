package com.bdprojecto.demo3.externalScenes;

import com.bdprojecto.demo3.adminStuff.AdminController;
import com.bdprojecto.demo3.adminStuff.AdminUi;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class AltaUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/bdprojecto/demo3/Alta.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dar de alta");
        primaryStage.show();

        scene.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                try {
                    Stage s = new Stage();
                    new AdminUi().start(s);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
