package com.example.bankingsystem.DatabaseConnectionUtils;

import javafx.scene.control.Alert;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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

}
