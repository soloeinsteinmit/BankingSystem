package com.example.bankingsystem.Login_SignUp_Classes;


import com.example.bankingsystem.DatabaseConnectionUtils.DatabaseConnection;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;


/**
 * @author Solomon Einstein MIT Eshun
 * */

public class LoginController implements Initializable {

    @FXML
    private TextField password_textField_signIn;
    @FXML
    private TextField name_textField_signIn;
    @FXML
    private TextField email_textField_signIn;
    @FXML
    private TextField acc_id_textField_signUp;
    @FXML
    private ImageView close_white_signUp;
    @FXML
    private TextField email_textField_signUp;
    @FXML
    private PasswordField password_textField_signUp;
    @FXML
    private Label show_signIn;
    @FXML
    private Label show_signUp;
    @FXML
    private MFXButton signUp_btn_signIn;
    @FXML
    private Pane signUp_signIn_pane;
    @FXML
    private MFXButton sign_btn_signUp;

    @FXML
    private Pane translateSignInPane;
    @FXML
    private Pane translateSignUpPane;
    @FXML
    private Label signUp_label;
    @FXML
    private Label signIn_label;
    @FXML
    private MFXButton generate_accId_btn;
    @FXML
    private TextField account_id_textField_signIn;
    @FXML
    private Circle moving_ball;

    private Parent fxml;
    private Parent root;
    private Stage stage;
    private Scene scene;


    @Override
    public void initialize(URL location, ResourceBundle resources) {



        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), signUp_signIn_pane);
        translateTransition.setToX(0);
        translateTransition.play();

        translateTransition.setOnFinished(e -> {

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
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sign_up.fxml")));
                signUp_signIn_pane.getChildren().removeAll();
                signUp_signIn_pane.getChildren().setAll(fxml);

//                if (translateSignUpPane.isVisible()){
//                    signIn_label.setDisable(true);
//                    signUp_label.setDisable(false);
//                }
//                if (translateSignInPane.isVisible()){
//                    signUp_label.setDisable(false);
//                    signIn_label.setDisable(true);
//                }

                generate_accId_btn.setOnAction(event1 -> {
                    Random acc_id = new Random();
                    int min = 11111;
                    int max = 99999;
                    account_id_textField_signIn.setText(String.valueOf(acc_id.nextInt(max - min + 1)+min));
                });

                signUp_btn_signIn.setOnAction(event ->{

                    if (account_id_textField_signIn.getText().equals("")){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Please generate your account id");
                        alert.show();
                    }else{

                        try {
                            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dashboard.fxml")));
                            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        DatabaseConnection.signUpUser(name_textField_signIn.getText(),
                                email_textField_signIn.getText(), password_textField_signIn.getText(),
                                account_id_textField_signIn.getText());
                        System.out.println("Login successful");
                    }

                });


            } catch (IOException exception){
                System.out.println("Error found in removing or setting fxml");
                System.out.println(exception.getCause().toString());
            }
        });

    }

    @FXML
    public void open_sign_in(MouseEvent event){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), signUp_signIn_pane);
        translateTransition.setToX(0);
        translateTransition.play();
        translateSignInPane();
        translateTransition.setOnFinished(e -> {

            FadeTransition fadeTransition1 = new FadeTransition();
            fadeTransition1.setNode(translateSignUpPane);
            fadeTransition1.setDuration(Duration.seconds(1));
            fadeTransition1.setInterpolator(Interpolator.EASE_IN);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(1);
            fadeTransition1.play();

            try {
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sign_up.fxml")));
                signUp_signIn_pane.getChildren().removeAll();
                signUp_signIn_pane.getChildren().setAll(fxml);
            } catch (IOException exception){
                System.out.println("Error found in removing or setting fxml sign up");
                System.out.println(exception.getCause().toString());
            }
        });
    }

    @FXML
    public void open_sign_up(MouseEvent event){
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), signUp_signIn_pane);
        translateTransition.setToX(-signUp_signIn_pane.getLayoutX());
        translateTransition.play();

        translateSignUpPane();
        translateTransition.setOnFinished(e -> {

            FadeTransition fadeTransition1 = new FadeTransition();
            fadeTransition1.setNode(translateSignInPane);
            fadeTransition1.setDuration(Duration.seconds(1));
            fadeTransition1.setInterpolator(Interpolator.EASE_IN);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(1);
            fadeTransition1.play();

            try {
                fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sign_in.fxml")));
                signUp_signIn_pane.getChildren().removeAll();
                signUp_signIn_pane.getChildren().setAll(fxml);
            } catch (IOException exception){
                System.out.println("Error found in removing or setting fxml sign in");
                System.out.println(exception.getCause().toString());
            }
        });
    }

    public void translateSignUpPane(){
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), translateSignUpPane);
        transition.setByX(100);
        transition.play();
        transition.setOnFinished(event -> {
            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setNode(translateSignUpPane);
            fadeTransition.setDuration(Duration.seconds(1));
            fadeTransition.setInterpolator(Interpolator.LINEAR);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.play();
            translateSignUpPane.setVisible(false);
            translateSignInPane.setVisible(true);

            TranslateTransition transition1 = new TranslateTransition(Duration.seconds(0.5), translateSignUpPane);
            transition1.setByX(-100);
            transition1.play();


        });
    }

    public void translateSignInPane(){
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), translateSignInPane);
        transition.setByX(-100);
        transition.play();
        transition.setOnFinished(event -> {
            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setNode(translateSignInPane);
            fadeTransition.setDuration(Duration.seconds(1));
            fadeTransition.setInterpolator(Interpolator.LINEAR);
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0);
            fadeTransition.play();
            translateSignInPane.setVisible(false);
            translateSignUpPane.setVisible(true);

            TranslateTransition transition1 = new TranslateTransition(Duration.seconds(0.5), translateSignInPane);
            transition1.setByX(100);
            transition1.play();
        });

    }


}
