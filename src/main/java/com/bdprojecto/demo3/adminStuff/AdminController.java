package com.bdprojecto.demo3.adminStuff;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public Label welcome;
    public static Label static_welcome;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        static_welcome = welcome;
    }
}
