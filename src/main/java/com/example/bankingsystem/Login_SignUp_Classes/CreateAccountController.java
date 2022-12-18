package com.example.bankingsystem.Login_SignUp_Classes;

import com.example.bankingsystem.DatabaseConnectionUtils.DatabaseConnection;
import com.example.bankingsystem.MainDashboardClasses.DashboardController;
import io.github.palexdev.materialfx.controls.MFXButton;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


/**
 * @author Solomon Eshun
* */
public class CreateAccountController implements Initializable {

    @FXML
    private TextField account_id_textField_signIn;

    @FXML
    private TextField email_textField_signIn;

    @FXML
    private MFXButton generate_accId_btn;

    @FXML
    private TextField name_textField_signIn;

    @FXML
    private PasswordField password_textField_signIn;

    @FXML
    private Label show_signIn;

    @FXML
    private MFXButton signUp_btn_signIn;

    @FXML
    public static Pane translateSignInPane;

    private Parent root;
    private Stage stage;
    private Scene scene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
        signUp_btn_signIn.setOnAction(event ->{

            if (account_id_textField_signIn.getText().equals("") || email_textField_signIn.getText().equals("")
                    || name_textField_signIn.getText().equals("")
                    || password_textField_signIn.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill in all the forms");
                alert.show();
            }else{

                try {
                    FXMLLoader loader = new FXMLLoader(CreateAccountController.class.getResource("dashboard.fxml"));
                    root = loader.load();
                    DashboardController getUserCredentials = loader.getController();
                    getUserCredentials.setUserCredentials(name_textField_signIn.getText(),
                            email_textField_signIn.getText(), password_textField_signIn.getText(),
                            account_id_textField_signIn.getText());
                } catch (IOException ex) {
                    System.out.println(" error dashboard fxml");
                    throw new RuntimeException(ex);
                }

                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                DatabaseConnection.signUpUser(name_textField_signIn.getText(),
                        email_textField_signIn.getText(), password_textField_signIn.getText(),
                        account_id_textField_signIn.getText());
                System.out.println("Login successful");
            }



        });
    }

}
