package com.example.bankingsystem.Login_SignUp_Classes;

import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.IOException;
import java.util.Objects;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.util.Duration;

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
