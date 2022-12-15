package com.example.bankingsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;



/**
 * @author Solomon Einstein MIT Eshun
 * */
public class BankingSystemController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}