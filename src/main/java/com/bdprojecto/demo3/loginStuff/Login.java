package com.bdprojecto.demo3.loginStuff;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.*;

import java.io.IOException;

public class Login extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("/com/bdprojecto/demo3/LoginFxml.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hola");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }

}
