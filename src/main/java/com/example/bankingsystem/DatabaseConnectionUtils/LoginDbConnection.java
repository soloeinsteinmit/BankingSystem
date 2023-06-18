package com.example.bankingsystem.DatabaseConnectionUtils;

import com.example.bankingsystem.CreateAccountController;
import com.example.bankingsystem.LoginController;
import com.example.bankingsystem.SignInIBankAccountTextController;
import javafx.scene.control.Alert;

import java.sql.*;

public class LoginDbConnection {

    public static final String dbPassword = "imanage";

    public static boolean gmailIsFound;
    public static void signUpUser(String email){
        Connection connection = null;

        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;


        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                    dbPassword);
            psCheckUserExist = connection.prepareStatement("""
                    SELECT email_address\s
                    FROM user_details
                    WHERE email_address = ?""");
            psCheckUserExist.setString(1, email);
            resultSet = psCheckUserExist.executeQuery();


            if (resultSet.isBeforeFirst()){
                gmailIsFound = true;

            }
            CreateAccountController.isFound = gmailIsFound;
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

    public static String getUserName;
    public static boolean isEqual;


    public static void signInUser( String email, String id_account, String password){
        Connection connection = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                    dbPassword);
            psCheckUserExist = connection.prepareStatement("""
                    SELECT account_id, full_name, email_address, user_password
                    FROM user_details
                    WHERE account_id = ? AND email_address = ? AND user_password = ?""");
            psCheckUserExist.setInt(1, Integer.parseInt(id_account));
            psCheckUserExist.setString(2, email);
            psCheckUserExist.setString(3, password);
            resultSet = psCheckUserExist.executeQuery();
            if (!resultSet.isBeforeFirst()){
                isEqual = false;
                SignInIBankAccountTextController.isEqualController = false;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect");
                alert.show();
            }else {
                while (resultSet.next()){
                    String retrievedEmail = resultSet.getString("email_address");
                    int retrievedAccountId1 = resultSet.getInt("account_id");
                    String retrievedPassword = resultSet.getString("user_password");
                    String retrievedUserName = resultSet.getString("full_name");

                    String retrievedAccountId  = String.valueOf(retrievedAccountId1);

                    if (retrievedEmail.equals(email) && retrievedAccountId.equals(id_account) &&
                            retrievedPassword.equals(password)){
                        isEqual = true;

                        email = retrievedEmail;
                        id_account = retrievedAccountId;
                        password = retrievedPassword;
                        getUserName = retrievedUserName;
                        SignInIBankAccountTextController.userName = getUserName;
                        System.out.println("db username = " + SignInIBankAccountTextController.userName);
                    }else {
                        System.out.println("Password,  email or account id did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect");
                        alert.show();
                    }
                }
            }

            SignInIBankAccountTextController.isEqualController = isEqual;

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

    public static boolean isFound;
    public static void forgotPassword(String email, String password){
        Connection connection = null;
        PreparedStatement psInsertNewPassword = null;
        PreparedStatement psCheckEmailExist = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                    dbPassword);

            psCheckEmailExist = connection.prepareStatement("""
                    SELECT email_address, user_password
                    FROM user_details
                    WHERE email_address = ?""");

            psCheckEmailExist.setString(1, email);
            resultSet = psCheckEmailExist.executeQuery();

            if (!resultSet.isBeforeFirst()){
                isFound = false;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided email not in database");
                alert.show();
            } else {
                while (resultSet.next()){
                    isFound = true;
                    psInsertNewPassword = connection.prepareStatement("""
                            UPDATE user_details
                            SET user_password = ?
                            WHERE email_address = ?""");
                    psInsertNewPassword.setString(1, password);
                    psInsertNewPassword.setString(2, email);
                    psInsertNewPassword.executeUpdate();
                }
            }
            LoginController.isFoundDialog = isFound;

        } catch (SQLException e){
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
            if (psInsertNewPassword != null){
                try {
                    psInsertNewPassword.close();
                } catch (SQLException e){
                    e.printStackTrace();
                    e.getCause();
                }
            }
            if (psCheckEmailExist != null){
                try {
                    psCheckEmailExist.close();
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
