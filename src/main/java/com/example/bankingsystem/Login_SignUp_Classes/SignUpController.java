package com.example.bankingsystem.Login_SignUp_Classes;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    private Parent fxml;

    @FXML
    private MFXButton signUpBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        signUpBtn.setOnAction(event -> {
            LoginController.open_sign_in();
        });
    }

}
