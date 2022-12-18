package com.example.bankingsystem.Login_SignUp_Classes;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInIBankAccountTextController implements Initializable {

    @FXML
    private TextField acc_id_textField_signUp;

    @FXML
    private TextField email_textField_signUp;

    @FXML
    private Label forgot_password;

    @FXML
    private Circle moving_ball;

    @FXML
    private PasswordField password_textField_signUp;

    @FXML
    private Label show_signUp;

    @FXML
    private MFXButton sign_btn_signUp;

    @FXML
    public static Pane translateSignUpPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
