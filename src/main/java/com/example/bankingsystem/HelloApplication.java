package com.example.bankingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ds.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Stack Data Structure");
        stage.setMaxHeight(300);
        stage.setMaxWidth(400);

        stage.setMinHeight(300);
        stage.setMinWidth(400);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}