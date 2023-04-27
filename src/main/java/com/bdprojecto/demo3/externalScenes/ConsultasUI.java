package com.bdprojecto.demo3.externalScenes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ConsultasUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(ConsultasUI.class.getResource("/com/bdprojecto/demo3/Consultas.FXML"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Consulta");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
