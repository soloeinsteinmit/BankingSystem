package com.example.bankingsystem.Login_SignUp_Classes;

import com.example.bankingsystem.DatabaseConnectionUtils.DatabaseConnection;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

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
    private MFXButton sign_btn;

    @FXML
    public static Pane translateSignUpPane;

    private Parent root;
    private Stage stage;
    private Scene scene;
    public static String userName;
    public static boolean isEqualController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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


        signIn_User();

    }

    private void signIn_User(){
        sign_btn.setOnAction(event -> {
            if ((email_textField_signUp.getText().isEmpty() || acc_id_textField_signUp.getText().isEmpty()
                    || password_textField_signUp.getText().isEmpty()) ){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill in all the forms");
                alert.show();
            }else{
//                if (isEqualController){
                    /*try {

                        FXMLLoader loader = new FXMLLoader(CreateAccountController.class.getResource("dashboard.fxml"));
                        root = loader.load();


                        DashboardController getUserCredentials = loader.getController();
                        getUserCredentials.setUserCredentials(userName,
                                email_textField_signUp.getText(), acc_id_textField_signUp.getText(),
                                password_textField_signUp.getText());


                    } catch (IOException ex) {
                        System.out.println(" error dashboard fxml");
                        throw new RuntimeException(ex);
                    }

                    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();*/
                    DatabaseConnection.signInUser(event, "dashboard.fxml", email_textField_signUp.getText(),
                            acc_id_textField_signUp.getText(), password_textField_signUp.getText());

                    /*System.out.println("userName string = "+userName);
                    System.out.println("userName string = "+isEqualController);
                    System.out.println("Login successful");*/
    /*} else {
        System.out.println("is equal else"+isEqualController);
        System.out.println(isEqualController);
        System.out.println(isEqualController);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Please provide correct credentials");
        alert.show();
    }*/

            }
        });
    }
}
