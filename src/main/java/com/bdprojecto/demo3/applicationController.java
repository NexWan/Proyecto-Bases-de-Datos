package com.bdprojecto.demo3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;

public class applicationController {
    @FXML
    private Button button1;
    @FXML
    private TitledPane pane1;

    @FXML
    protected void onClick() {
        if(!button1.getUserData().equals("active")){
            button1.setUserData("active");
            pane1.setVisible(true);
        }else{
            button1.setUserData("inactive");
            pane1.setVisible(false);
        }
        System.out.println(button1.getUserData());
    }
}