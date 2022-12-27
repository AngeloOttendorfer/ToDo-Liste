package com.example.todoliste.application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ToDoController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}