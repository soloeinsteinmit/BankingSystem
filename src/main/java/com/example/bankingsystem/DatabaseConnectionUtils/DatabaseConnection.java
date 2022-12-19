package com.example.bankingsystem.DatabaseConnectionUtils;

import com.example.bankingsystem.Login_SignUp_Classes.SignInIBankAccountTextController;
import com.example.bankingsystem.MainDashboardClasses.DashboardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class DatabaseConnection {


    public static void signUpUser(String userFullName, String email, String user_password,
                                  String user_account_id){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;


        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                    "programming");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM user_signup_table \n" +
                                                            "WHERE user_email = ? AND account_id = ?");
            psCheckUserExist.setString(1, email);
            psCheckUserExist.setString(2, user_account_id);
            resultSet = psCheckUserExist.executeQuery();

            if (resultSet.isBeforeFirst()){
                System.out.println("User already exist");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this email, user already exist");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO user_signup_table(user_full_name, user_email, " +
                        "user_password, date_registered, account_id)\n" +
                        "VALUES(?, ?, ?, ?, ?)");
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("Y-MM-dd");

                psInsert.setString(1, userFullName);
                psInsert.setString(2, email);
                psInsert.setString(3, user_password);
                psInsert.setString(4, dateFormat.format(date));
                psInsert.setString(5, user_account_id);
                psInsert.executeUpdate();

            }
        }
        catch (SQLException e){
            e.printStackTrace();
            e.getCause();
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                    e.getCause();
                }
            }
            if (psCheckUserExist != null){
                try {
                    psCheckUserExist.close();
                } catch (SQLException e){
                    e.printStackTrace();
                    e.getCause();
                }
            }
            if (psInsert != null){
                try {
                    psInsert.close();
                } catch (SQLException e){
                    e.printStackTrace();
                    e.getCause();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                    e.getCause();
                }
            }
        }
    }

    /*
    * this method gives an IllegalStateException
    * */
    public static void changeSceneToDashboard(ActionEvent event, String fxmlFile, String name,
              String email, String accountId, String password){
        Parent root = null;
        if (name != null && email != null && accountId != null && password != null){
            try{
                FXMLLoader loader = new FXMLLoader(DatabaseConnection.class.getResource(fxmlFile));
                root = loader.load();
                DashboardController setUserCredentials = loader.getController();
                setUserCredentials.setUserCredentials(name, email, accountId, password);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            try{
                root = FXMLLoader.load(Objects.requireNonNull(DatabaseConnection.class.getResource(fxmlFile)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static String getUserName;
    public static boolean isEqual;
    public static void signInUser(ActionEvent event, String fxmlFile, String email, String id_account, String password){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                    "programming");
            psCheckUserExist = connection.prepareStatement("""
                    SELECT user_signup_table.user_email,\s
                    \tuser_signup_table.account_id,\s
                    \tuser_signup_table.user_password,
                        user_signup_table.user_full_name
                    FROM user_signup_table\s
                    WHERE  user_email = ? AND account_id = ? AND user_password = ?""");
            psCheckUserExist.setString(1, email);
            psCheckUserExist.setString(2, id_account);
            psCheckUserExist.setString(3, password);
            resultSet = psCheckUserExist.executeQuery();
            if (!resultSet.isBeforeFirst()){
                /*isEqual = false;
                SignInIBankAccountTextController.isEqualController = false;*/
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect");
                alert.show();
            }else {
                while (resultSet.next()){
                    String retrievedEmail = resultSet.getString("user_email");
                    String retrievedAccountId = resultSet.getString("account_id");
                    String retrievedPassword = resultSet.getString("user_password");
                    String retrievedUserName = resultSet.getString("user_full_name");
                    if (retrievedEmail.equals(email) && retrievedAccountId.equals(id_account) &&
                            retrievedPassword.equals(password)){
                        changeSceneToDashboard(event, fxmlFile, retrievedUserName, retrievedEmail, retrievedAccountId, retrievedPassword);
                        /*isEqual = true;
                        SignInIBankAccountTextController.isEqualController = true;
                        email =retrievedEmail;
                        id_account = retrievedAccountId;
                        password = retrievedPassword;
                        getUserName = retrievedUserName;*/
                        SignInIBankAccountTextController.userName = getUserName;
                    }else {
                        System.out.println("Password,  email or account id did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect");
                        alert.show();
                    }
                }
            }


        }
        catch (SQLException e){
            e.printStackTrace();
            e.getCause();
        } finally {
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                    e.getCause();
                }
            }
            if (psCheckUserExist != null){
                try {
                    psCheckUserExist.close();
                } catch (SQLException e){
                    e.printStackTrace();
                    e.getCause();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                    e.getCause();
                }
            }
        }
    }

}
