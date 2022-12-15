package com.example.bankingsystem.Login_SignUp_Classes;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


/**
 * @author Solomon Einstein MIT Eshun
 * */

public class LoginController {

    @FXML
    private Label hello_text;

    @FXML
    void sayHello(ActionEvent event) {
        hello_text.setVisible(true);
    }

}
