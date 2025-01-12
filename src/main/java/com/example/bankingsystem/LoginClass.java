package com.example.bankingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * @author Solomon Einstein MIT Eshun
 * */
public class LoginClass extends Application {

    public static Stage my_stage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginClass.class.getResource("Login.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(LoginClass.class.getResource("user_credentials.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 830, 645);
//        Scene scene = new Scene(fxmlLoader.load(), 1216, 645);

        stage.initStyle(StageStyle.UNDECORATED);
        my_stage = stage;

        stage.setTitle("Banking System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
