package com.example.bankingsystem;

import com.example.bankingsystem.DatabaseConnectionUtils.DatabaseConnection;
import com.example.bankingsystem.DatabaseConnectionUtils.UserCredentialsDbConnection;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


/**
 * @author Solomon Eshun
* */
public class CreateAccountController implements Initializable {

    @FXML
    private MFXTextField account_id_textField_signIn;

    @FXML
    private MFXTextField email_textField_signIn;

    @FXML
    private MFXButton generate_accId_btn;

    @FXML
    private MFXTextField name_textField_signIn;

    @FXML
    private MFXPasswordField password_textField_signIn;

    @FXML
    private MFXButton signUp_btn_signIn;

    @FXML
    public static Pane translateSignInPane;

    private Parent root;
    private Stage stage;
    private Scene scene;
    public static boolean isFound;
    public static MFXButton signUpBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        signUpBtn = signUp_btn_signIn;
        generateAccId();
        signUp_user();


    }

    /**
    * generate user account id
    * */
    private void generateAccId(){
        generate_accId_btn.setOnAction(event1 -> {
            Random acc_id = new Random();
            int min = 11111;
            int max = 99999;
            account_id_textField_signIn.setText(String.valueOf(acc_id.nextInt(max - min + 1)+min));
        });
    }

    /**
    * signs up user
    * */
    private void signUp_user(){
        signUpBtn.setOnAction(event ->{

            if (account_id_textField_signIn.getText().equals("") || email_textField_signIn.getText().equals("")
                    || name_textField_signIn.getText().equals("")
                    || password_textField_signIn.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill in all the forms");
                alert.show();
            }
            else{

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("user_credentials.fxml"));
                    root = loader.load();

                    DatabaseConnection.signUpUser(name_textField_signIn.getText(),
                            email_textField_signIn.getText(), password_textField_signIn.getText(),
                            account_id_textField_signIn.getText());

                    System.out.println("IS FOUND UP "+ isFound);

                    if (SignInIBankAccountTextController.validateEmail(email_textField_signIn.getText()) && !isFound){

                        UserCredentialsController getText = loader.getController();
                        getText.setTextField(email_textField_signIn, name_textField_signIn, account_id_textField_signIn);

                        UserCredentialsDbConnection.account_id = account_id_textField_signIn.getText();
                        System.out.println("acc id createAcc = " + UserCredentialsDbConnection.account_id);

                        /*HomeController.str_userName = name_textField_signIn.getText();
                        HomeController.str_email = email_textField_signIn.getText();
                        HomeController.str_password = password_textField_signIn.getText();
                        HomeController.str_accId = account_id_textField_signIn.getText();

                        String[] splitName = name_textField_signIn.getText().split(" ");
                        String[] firstName = splitName[0].split("");
                        // gotten abbr name
                        HomeController.str_abbrName = firstName[0] + " " + splitName[splitName.length-1];
                        System.out.println(HomeController.str_abbrName);

                        HomeController.setUserCredentials();*/

                        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                        String title = "SIGNING UP";
                        String message = name_textField_signIn.getText().toUpperCase() + " SUCCESSFUL SIGNED UP ";
                        TrayNotification tray = new TrayNotification();
                        AnimationType type = AnimationType.POPUP;

                        tray.setAnimationType(type);
                        tray.setTitle(title);
                        tray.setMessage(message);
                        tray.setNotificationType(NotificationType.SUCCESS);
                        tray.showAndDismiss(Duration.millis(5000));
                        System.out.println("Login successful");
                    }

                } catch (IOException ex) {
                    System.out.println(" error dashboard fxml");
                    throw new RuntimeException(ex);
                }

            }

        });
    }

}
