package com.example.bankingsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class HelloController implements Initializable {
    @FXML
    private Label alertMessage;

    @FXML
    private Button peekBtn;
    @FXML
    private Button clearBtn;

    @FXML
    private Button popBtn;

    @FXML
    private Button pushBtn;

    @FXML
    private TextArea showStackTextArea;

    private Stack<Integer> stack = new Stack<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (stack.isEmpty()){
            popBtn.setDisable(true);
            peekBtn.setDisable(true);
            clearBtn.setDisable(true);
        }

        popBtn.setOnMouseClicked(event -> {

            if (stack.isEmpty()){
                alertMessage.setVisible(true);
                alertMessage.setText("Stack is empty... No item to be popped");
                popBtn.setDisable(true);
                peekBtn.setDisable(true);
                clearBtn.setDisable(true);
            }else {
                stack.pop();
                showStackTextArea.setText(String.valueOf(stack));
            }
        });
        peekBtn.setOnMouseClicked(event -> {
            if (stack.isEmpty()){
                alertMessage.setVisible(true);
                alertMessage.setText("Stack is empty... No item to peek at");
                popBtn.setDisable(true);
                peekBtn.setDisable(true);
                clearBtn.setDisable(true);
            }else {
                showStackTextArea.setText(stack + "\nItem as the top is: " + stack.peek());
            }
        });
        clearBtn.setOnMouseClicked(event -> {
            if (stack.isEmpty()){
                alertMessage.setVisible(true);
                alertMessage.setText("Stack is empty... Nothing to clear");
                popBtn.setDisable(true);
                peekBtn.setDisable(true);
                clearBtn.setDisable(true);
            }else {
                stack.clear();
                showStackTextArea.setText(String.valueOf(stack));
            }
        });
    }

}