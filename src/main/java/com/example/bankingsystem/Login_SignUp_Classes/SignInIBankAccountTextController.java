package com.example.bankingsystem.Login_SignUp_Classes;

import com.example.bankingsystem.DatabaseConnectionUtils.DatabaseConnection;
import com.example.bankingsystem.MainDashboardClasses.DashboardController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInIBankAccountTextController implements Initializable {

    @FXML
    private MFXTextField acc_id_textField_signUp;

    @FXML
    private MFXTextField email_textField_signUp;

    @FXML
    private Label forgot_password;

    @FXML
    private Circle moving_ball;

    @FXML
    private MFXPasswordField password_textField_signUp;

    @FXML
    private MFXButton sign_btn;

    @FXML
    public static Pane translateSignUpPane;

    private Parent root;
    private Stage stage;
    private Scene scene;
    public static String userName;
    public static boolean isEqualController;
    public static MFXTextField getEmailText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getEmailText = email_textField_signUp;
        /*TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(8), moving_ball);
        translateTransition1.setToX(translateSignUpPane.getWidth());
        translateTransition1.setToY(translateSignUpPane.getHeight());
        translateTransition1.setCycleCount(2);
        translateTransition1.setAutoReverse(true);
        translateTransition1.play();
        translateTransition1.setOnFinished(event -> {
            TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(8), moving_ball);
            translateTransition2.setToX(translateSignUpPane.getWidth());
            translateTransition2.setToY(-translateSignUpPane.getHeight());
            translateTransition2.setCycleCount(TranslateTransition.INDEFINITE);
            translateTransition2.setAutoReverse(true);
            translateTransition2.play();
        });*/

        forgot_password.setOnMouseClicked(event -> {
            LoginController.forgotPassword();
        });
        signIn_User();

    }

    private void signIn_User(){
        sign_btn.setOnAction(event -> {
            if ((email_textField_signUp.getText().isEmpty() || acc_id_textField_signUp.getText().isEmpty()
                    || password_textField_signUp.getText().isEmpty()) ){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill in all the forms");
                alert.show();
            } else{
                try {
                    FXMLLoader loader = new FXMLLoader(CreateAccountController.class.getResource("dashboard.fxml"));
                    root = loader.load();

                    DashboardController getUserCredentials = loader.getController();
                    DatabaseConnection.signInUser(email_textField_signUp.getText(), acc_id_textField_signUp.getText(),
                            password_textField_signUp.getText());

                    if (isEqualController && validateEmail(getEmailText.getText())){

                        getUserCredentials.setUserCredentials(userName,
                                email_textField_signUp.getText(), acc_id_textField_signUp.getText(),
                                password_textField_signUp.getText());

                        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                        String title = "SIGNING IN";
                        String message = userName.toUpperCase() + " HAS SUCCESSFULLY SIGNED IN";
                        TrayNotification tray = new TrayNotification();
                        AnimationType type = AnimationType.POPUP;

                        tray.setAnimationType(type);
                        tray.setTitle(title);
                        tray.setMessage(message);
                        tray.setNotificationType(NotificationType.SUCCESS);
                        tray.showAndDismiss(Duration.millis(5000));
                    }
                    System.out.println("userName = " + userName);
                } catch (IOException ex) {
                    System.out.println(" error dashboard fxml");
                    throw new RuntimeException(ex);
                }


                //DatabaseConnection.signInUser(event, "dashboard.fxml", email_textField_signUp.getText(),
                  //      acc_id_textField_signUp.getText(), password_textField_signUp.getText());
             }
        });
    }

     public static boolean validateEmail(String emailText){
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher matcher = pattern.matcher(emailText);
        if (matcher.find() && matcher.group().equals(emailText)){
            return true;
        } else {

            String title = "Email Validation Error";
            String message = "Please enter a valid email";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Email");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid email");
            alert.showAndWait();
            return false;
        }
    }
}
