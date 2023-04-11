package com.bdprojecto.demo3.adminStuff;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import com.bdprojecto.demo3.externalScenes.*;
import javafx.stage.Stage;

public class AdminController implements Initializable {
    public Label welcome;
    public static Label static_welcome;
    public Stage s = new Stage();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        static_welcome = welcome;
    }

    public void altaMaestro(ActionEvent actionEvent) throws Exception {
        new AltaUI().start(s);
    }

    public void altaAlumno(ActionEvent actionEvent) {
    }

    public void altaAdmin(ActionEvent actionEvent) {
    }

    public void consulta(ActionEvent actionEvent) {
    }

    public void baja(ActionEvent actionEvent) {
    }
}
