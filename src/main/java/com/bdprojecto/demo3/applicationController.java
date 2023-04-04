package com.bdprojecto.demo3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

import java.io.IOException;

public class applicationController {
    @FXML
    private Button button1;
    @FXML
    private TitledPane pane1;

    public void setButton1(boolean val){
        button1.setDisable(val);
    }

    public Button getButton1(){
        return button1;
    }

    @FXML
    protected void onClick() throws Exception {
//        This function loads a new window or stage, i plan on adding all the input options in this one
        Stage s = new Stage();
        System.out.println("hola");
        secondApp data = new secondApp();
        data.start(s);
        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
    }
}