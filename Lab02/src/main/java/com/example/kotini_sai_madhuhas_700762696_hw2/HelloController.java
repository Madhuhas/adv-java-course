package com.example.kotini_sai_madhuhas_700762696_hw2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to CS5220 Summer 2024!");
    }
}