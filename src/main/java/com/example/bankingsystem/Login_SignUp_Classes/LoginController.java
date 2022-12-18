package com.example.bankingsystem.Login_SignUp_Classes;


import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 * @author Solomon Einstein MIT Eshun
 * another author
 * @author Ebenezer Ogoe
 * */

public class LoginController implements Initializable {
    @FXML
    private VBox vBox;

    @FXML
    private VBox createAccount_vBox;
    @FXML
    private VBox signIn_vBox;

    public static VBox container;
    public static VBox container_iBank;
    public static VBox panel;

    private static Parent fxml;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logInAccount();
        container = createAccount_vBox;
        container_iBank = signIn_vBox;
        panel = vBox;

        /*TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(8), moving_ball);
        translateTransition1.setToX(translateSignUpPane.getWidth());
        translateTransition1.setToY(translateSignUpPane.getHeight());
        translateTransition1.setCycleCount(2);
        translateTransition1.setAutoReverse(true);
        translateTransition1.play();
        translateTransition1.setOnFinished(event -> {
            TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(8), moving_ball);
            translateTransition2.setToX(translateSignInPane.getWidth());
            translateTransition2.setToY(-translateSignInPane.getHeight());
            translateTransition2.setCycleCount(TranslateTransition.INDEFINITE);
            translateTransition2.setAutoReverse(true);
            translateTransition2.play();
        });*/

        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("createAccount.fxml")));
            createAccount_vBox.setVisible(false);
            createAccount_vBox.getChildren().removeAll();
            createAccount_vBox.getChildren().add(fxml);

        } catch (IOException exception){
            System.out.println("Error found in removing or setting fxml");
            System.out.println(exception.getCause().toString());
        }

    }


    public static void open_sign_in(){


        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(LoginController.class.getResource("createAccount.fxml")));
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), panel);
            translateTransition.setToX(-panel.getLayoutX());
            translateTransition.play();
            translateSignInPane();
            translateTransition.setOnFinished(e -> {

                FadeTransition fadeTransition1 = new FadeTransition();
                fadeTransition1.setNode(container_iBank);
                fadeTransition1.setDuration(Duration.seconds(1));
                fadeTransition1.setInterpolator(Interpolator.EASE_IN);
                fadeTransition1.setFromValue(0);
                fadeTransition1.setToValue(1);
                fadeTransition1.play();
            });
            container.getChildren().removeAll();
            container.getChildren().setAll(fxml);

            fxml = FXMLLoader.load(Objects.requireNonNull(
                    LoginController.class.getResource("sign_in.fxml")));
            panel.getChildren().removeAll();
            panel.getChildren().setAll(fxml);




        } catch (IOException exception){
            System.out.println("Error found in removing or setting fxml sign in");
            exception.printStackTrace();
        }

        //});
    }

    public static void open_sign_up(){

            try {
                fxml = FXMLLoader.load(Objects.requireNonNull(LoginController.class.getResource("signin_iBank.fxml")));
                TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), panel);
                translateTransition.setToX(0);
                translateTransition.play();

                translateSignUpPane();
                translateTransition.setOnFinished(e -> {

                    FadeTransition fadeTransition1 = new FadeTransition();
                    fadeTransition1.setNode(container);
                    fadeTransition1.setDuration(Duration.seconds(1));
                    fadeTransition1.setInterpolator(Interpolator.EASE_IN);
                    fadeTransition1.setFromValue(0);
                    fadeTransition1.setToValue(1);
                    fadeTransition1.play();
                });

                container.getChildren().removeAll();
                container.getChildren().setAll(fxml);
                fxml = FXMLLoader.load(Objects.requireNonNull(
                        LoginController.class.getResource("sign_up.fxml")));
                panel.getChildren().removeAll();
                panel.getChildren().setAll(fxml);

            } catch (IOException exception){
                System.out.println("Error found in removing or setting fxml sign in");
                exception.printStackTrace();
            }

    }

    public static void translateSignUpPane(){
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), container);
        transition.setByX(100);
        transition.play();
        transition.setOnFinished(event -> {
            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setNode(container);
            fadeTransition.setDuration(Duration.seconds(1));
            fadeTransition.setInterpolator(Interpolator.LINEAR);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.play();
            container.setVisible(false);
            container_iBank.setVisible(true);

            TranslateTransition transition1 = new TranslateTransition(Duration.seconds(0.5), container);
            transition1.setByX(-100);
            transition1.play();


        });
    }

    public static void translateSignInPane(){
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), container_iBank);
        transition.setByX(100);
        transition.play();
        transition.setOnFinished(event -> {
            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setNode(container_iBank);
            fadeTransition.setDuration(Duration.seconds(1));
            fadeTransition.setInterpolator(Interpolator.LINEAR);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.play();

            container_iBank.setVisible(false);
            container.setVisible(true);

            TranslateTransition transition1 = new TranslateTransition(Duration.seconds(0.5), container_iBank);
            transition1.setByX(-100);
            transition1.play();
        });

    }

    /**
     * Loads the create account fxml and sign in fxml to their respective
     * vBoxes.
     * */
    private void createAccount(){

        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("createAccount.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        createAccount_vBox.getChildren().setAll(fxml);

        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sign_in.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        vBox.setLayoutX(-2);
        vBox.getChildren().add(fxml);

    }

    private void logInAccount(){
        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signIn_iBank.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        signIn_vBox.getChildren().setAll(fxml);

        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sign_up.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        vBox.getChildren().add(fxml);
    }


}
