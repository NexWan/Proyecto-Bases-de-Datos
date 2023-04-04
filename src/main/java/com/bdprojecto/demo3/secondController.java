package com.bdprojecto.demo3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;

public class secondController {
    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    public void onClick(){
        Person currPerson = new Person(nombre.getText(),apellido.getText());
        JOptionPane.showMessageDialog(null,currPerson.toString());
    }
}
