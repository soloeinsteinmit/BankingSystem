package com.example.bankingsystem.Login_SignUp_Classes;


import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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

    @FXML
    private ImageView logo_signUp;
    @FXML
    private Text ibank_signUp;

    public static VBox container;
    public static VBox container_iBank;
    public static VBox panel;
    public static Text iBankText;
    public static ImageView logo_img;

    private static Parent fxml;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logInAccount();

        iBankText = ibank_signUp;
        logo_img = logo_signUp;
        container = createAccount_vBox;
        container_iBank = signIn_vBox;
        panel = vBox;

    }


    public static void open_sign_in(){

        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(LoginController.class.getResource("createAccount.fxml")));


            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), panel);
            translateTransition.setToX(-panel.getLayoutX());
            translateTransition.play();
            fadeOutLogoAndText();
            translateSignInPane();
            translateTransition.setOnFinished(e -> {

                FadeTransition fadeTransition1 = new FadeTransition();
                fadeTransition1.setNode(container_iBank);
                fadeTransition1.setDuration(Duration.seconds(1));
                fadeTransition1.setInterpolator(Interpolator.EASE_IN);
                fadeTransition1.setFromValue(1);
                fadeTransition1.setToValue(0);
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
    }

    public static void open_sign_up(){

            try {
                fxml = FXMLLoader.load(Objects.requireNonNull(LoginController.class.getResource("signin_iBank.fxml")));


                TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), panel);
                translateTransition.setToX(0);
                translateTransition.play();
                fadeInLogoAndText();
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
        transition.setByX(-100);
        transition.play();
        transition.setOnFinished(event -> {
            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setNode(container_iBank);
            fadeTransition.setDuration(Duration.seconds(1));
            fadeTransition.setInterpolator(Interpolator.EASE_IN);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

            container.setVisible(false);
            container_iBank.setVisible(true);

            TranslateTransition transition1 = new TranslateTransition(Duration.seconds(0.5), container);
            transition1.setByX(100);
            transition1.play();


        });
    }

    public static void translateSignInPane(){
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), container_iBank);
        transition.setByX(100);
        transition.play();
        transition.setOnFinished(event -> {
            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setNode(container);
            fadeTransition.setDuration(Duration.seconds(1));
            fadeTransition.setInterpolator(Interpolator.LINEAR);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

            container.setVisible(true);
            container_iBank.setVisible(false);

            TranslateTransition transition1 = new TranslateTransition(Duration.seconds(0.5), container_iBank);
            transition1.setByX(-100);
            transition1.play();
        });

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

    private static void fadeOutLogoAndText(){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(iBankText);
        fadeTransition.setDuration(Duration.seconds(1));
        fadeTransition.setInterpolator(Interpolator.EASE_OUT);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        FadeTransition fadeTransition1 = new FadeTransition();
        fadeTransition1.setNode(logo_img);
        fadeTransition1.setDuration(Duration.seconds(1));
        fadeTransition1.setInterpolator(Interpolator.EASE_OUT);
        fadeTransition1.setFromValue(1);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();
    }

    private static void fadeInLogoAndText(){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(logo_img);
        fadeTransition.setDuration(Duration.seconds(1));
        fadeTransition.setInterpolator(Interpolator.EASE_IN);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        FadeTransition fadeTransition1 = new FadeTransition();
        fadeTransition1.setNode(iBankText);
        fadeTransition1.setDuration(Duration.seconds(1));
        fadeTransition1.setInterpolator(Interpolator.EASE_IN);
        fadeTransition1.setFromValue(0);
        fadeTransition1.setToValue(1);
        fadeTransition1.play();
    }


}
