package com.example.bankingsystem.Login_SignUp_Classes;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void open_sign_up(MouseEvent event){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), LoginController.getSignUp_signIn_pane());
        translateTransition.setToX(-LoginController.getSignUp_signIn_pane().getLayoutX());
        translateTransition.play();

        LoginController.translateSignUpPane();
        translateTransition.setOnFinished(e -> {

            FadeTransition fadeTransition1 = new FadeTransition();
            fadeTransition1.setNode(LoginController.getTranslateSignUpPane());
            fadeTransition1.setDuration(Duration.seconds(1));
            fadeTransition1.setInterpolator(Interpolator.EASE_IN);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(1);
            fadeTransition1.play();

            try {
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sign_in.fxml")));
                LoginController.getSignUp_signIn_pane().getChildren().removeAll();
                LoginController.getSignUp_signIn_pane().getChildren().setAll(fxml);
            } catch (IOException exception){
                System.out.println("Error found in removing or setting fxml sign in");
                System.out.println(exception.getCause().toString());
            }
        });
    }
}
