package com.example.bankingsystem.MainDashboardClasses;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Label user_acc_id;

    @FXML
    private Label user_email;

    @FXML
    private Label user_name;

    @FXML
    private Label user_password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setUserCredentials(String name, String email, String acc_id, String password){
        user_name.setText(name);
        user_email.setText(email);
        user_acc_id.setText(acc_id);
        user_password.setText(password);
    }
}
