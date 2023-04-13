/*
* The class InputFX works as a JOptionPane with several JTextFields, the difference is that it's made with JavaFX and can
* pass directly from the constructor the amount of rows and its labels*/

package com.bdprojecto.demo3.externalScenes;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class InputFX extends Application {

    private final int numFields;
    private final List<String> labels;
    private final LinkedList<String> inputValues;

    public InputFX(int numFields, List<String> labels) {
        this.numFields = numFields;
        this.labels = labels;
        this.inputValues = new LinkedList<>();
    }

    public void showInputDialog() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Input Dialog");

        // create grid pane and set properties
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // create text fields and labels, and add them to the grid
        for (int i = 0; i < numFields; i++) {
            String labelText = labels.get(i);
            Label label = new Label(labelText);
            TextField textField = new TextField();
            grid.add(label, 0, i);
            grid.add(textField, 1, i);

            // add a listener to the text field to store its value in the list
            int finalI = i;
            textField.textProperty().addListener((observable, oldValue, newValue) ->
                    inputValues.set(finalI, newValue));
            inputValues.add(null);
        }

        // create OK button and add it to an HBox
        Button okButton = new Button("OK");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(okButton);
        grid.add(hbBtn, 1, numFields);

        // set action for OK button
        okButton.setOnAction(event -> window.close());

        // create scene and show the window
        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();
    }

    public LinkedList<String> getInputValues() {
        return inputValues;
    }

    @Override
    public void start(Stage primaryStage) {
        // do nothing, start method is not used for InputFX
    }
}

