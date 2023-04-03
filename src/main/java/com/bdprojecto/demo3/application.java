package com.bdprojecto.demo3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class application extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(application.class.getResource("hello-view.fxml"));
        String css = this.getClass().getResource("style.css").toExternalForm(); //Reference to the directory
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMinHeight(700);
        stage.setMinWidth(800);
        stage.setResizable(false);
        scene.getStylesheets().add(css); //Css reference to the javafx scene
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}