package com.example.bankingsystem.Login_SignUp_Classes;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {

    @FXML
    private MFXButton sign_in;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sign_in.setOnAction(event -> {
            LoginController.open_sign_up();
        });
    }
}
