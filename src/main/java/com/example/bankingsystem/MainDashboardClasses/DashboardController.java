package com.example.bankingsystem.MainDashboardClasses;

import com.example.bankingsystem.Login_SignUp_Classes.LoginController;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private MFXButton logoutBtn;
    @FXML
    private Label user_acc_id;

    @FXML
    private Label user_email;

    @FXML
    private Label user_name;

    @FXML
    private Label user_password;
    private static Parent root;
    public static MFXButton logOutImg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logOutImg = logoutBtn;
        logout();
    }

    public static void logout(){
        logOutImg.setOnMouseClicked(event -> {
            try {
                root = FXMLLoader.load(Objects.requireNonNull(LoginController.class.getResource("Login.fxml")));

                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void setUserCredentials(String name, String email, String acc_id, String password){
        user_name.setText(name);
        user_email.setText(email);
        user_acc_id.setText(acc_id);
        user_password.setText(password);
    }
}
