package com.example.bankingsystem.Login_SignUp_Classes;


import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 * @author Solomon Einstein MIT Eshun
 * */

public class LoginController implements Initializable {

    @FXML
    private Pane signUp_signIn_pane;

    public Parent fxml;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), signUp_signIn_pane);
        translateTransition.setToX(0);
        translateTransition.play();
        translateTransition.setOnFinished(e -> {
            try {
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sign_up.fxml")));
                signUp_signIn_pane.getChildren().removeAll();
                signUp_signIn_pane.getChildren().setAll(fxml);
            } catch (IOException exception){
                System.out.println("Error found in removing or setting fxml");
            }
        });

    }

    @FXML
    public void open_sign_in(MouseEvent event){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), signUp_signIn_pane);
        translateTransition.setToX(0);
        translateTransition.play();
        translateTransition.setOnFinished(e -> {
            try {
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sign_up.fxml")));
                signUp_signIn_pane.getChildren().removeAll();
                signUp_signIn_pane.getChildren().setAll(fxml);
            } catch (IOException exception){
                System.out.println("Error found in removing or setting fxml");
            }
        });
    }

    @FXML
    public void open_sign_up(MouseEvent event){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), signUp_signIn_pane);
        translateTransition.setToX(-signUp_signIn_pane.getLayoutX());
        translateTransition.play();
        translateTransition.setOnFinished(e -> {
            try {
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sign_in.fxml")));
                signUp_signIn_pane.getChildren().removeAll();
                signUp_signIn_pane.getChildren().setAll(fxml);
            } catch (IOException exception){
                System.out.println("Error found in removing or setting fxml");
            }
        });
    }

}
