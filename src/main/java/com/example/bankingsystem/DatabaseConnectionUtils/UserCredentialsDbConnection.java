package com.example.bankingsystem.DatabaseConnectionUtils;

import com.example.bankingsystem.LoginClass;
import com.example.bankingsystem.UserCredentialsController;
import io.github.gleidson28.GNAvatarView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserCredentialsDbConnection {
    public static File fileMain;
    public static String account_id;
    public static String password;

    public static void getImageIntoDbDragEffect(DragEvent event,
                        GNAvatarView avatarView) throws SQLException, IOException {
        Connection connection;
        PreparedStatement psInsert;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                "programming");

        psInsert = connection.prepareStatement("UPDATE user_details SET user_profile_pic = ? WHERE account_id = "
                + Integer.parseInt(account_id)+ "");

        List<File> files = event.getDragboard().getFiles();

        File file = new File(String.valueOf(files.get(0)));
        fileMain = file;
        System.out.println("fileMain drag = " + fileMain);
        System.out.println("dragEffect file =  " + file);
        FileInputStream fileInputStream = new FileInputStream(file);

        FileInputStream fileInputStream1 = new FileInputStream(file);
        Image image = new Image(fileInputStream1);
        avatarView.setImage(image);

        psInsert.setBinaryStream(1, fileInputStream, fileInputStream.available());
        psInsert.executeUpdate();

    }

    public static void getImageIntoDbFileChooser(GNAvatarView avatarView)
            throws SQLException {
        Connection connection;
        PreparedStatement psInsert;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                "programming");

        psInsert = connection.prepareStatement("UPDATE user_details SET user_profile_pic = ? WHERE account_id = "
                + Integer.parseInt(account_id));

        PreparedStatement finalPsInsert = psInsert;
//        imageView.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(LoginClass.my_stage);
            fileMain = file;
            System.out.println("fileMain fileChooser = " + fileMain);
            System.out.println("fileChooser =  " + file);
            try {
                FileInputStream fileInputStream = new FileInputStream(file);

                FileInputStream fileInputStream1 = new FileInputStream(file);
                Image image = new Image(fileInputStream1);
//                    imageView1.setImage(image);
                avatarView.setImage(image);

                System.out.println("Is not empty");
                finalPsInsert.setBinaryStream(1, fileInputStream, fileInputStream.available());
                finalPsInsert.execute();


            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
//        });


    }

    public static void createContactsDetails(MFXTextField email, MFXTextField name, MFXTextField contact_acc_id,
                 MFXTextField visa_acc_num, String gender) throws SQLException {
        Connection connection;
        PreparedStatement psInsert;
        PreparedStatement psCheckAccIdExist;
        ResultSet resultSet;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                LoginDbConnection.dbPassword);
        psCheckAccIdExist = connection.prepareStatement("""
                SELECT account_id
                FROM user_details
                WHERE account_id = ?\s""");
        psCheckAccIdExist.setInt(1, Integer.parseInt(contact_acc_id.getText()));
        resultSet = psCheckAccIdExist.executeQuery();

        if (!resultSet.isBeforeFirst()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("PLEASE CONTACT ACCOUNT ID IS NOT REGISTERED");
            alert.show();
        }else {
            psInsert = connection.prepareStatement("INSERT INTO contacts_table VALUES(?, ?, ?, ?, ?, " + Integer.parseInt(account_id) + ")");
            psInsert.setInt(1, Integer.parseInt(contact_acc_id.getText()));
            psInsert.setString(2, name.getText());
            psInsert.setString(3, email.getText());
            psInsert.setString(4, gender);
            psInsert.setString(5, visa_acc_num.getText());
            psInsert.executeUpdate();
        }
    }



    public static void userDetails(MFXTextField name, MFXTextField email, MFXTextField visa_num, String dob,
            String gender, String freeTransfer) throws SQLException, IOException {
        Connection connection;
        PreparedStatement psInsert;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                LoginDbConnection.dbPassword);
        psInsert = connection.prepareStatement("INSERT INTO user_details VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        java.util.Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("Y-MM-dd");

        psInsert.setInt(1, Integer.parseInt(account_id));
        psInsert.setString(2, name.getText());
        psInsert.setString(3, email.getText());
        psInsert.setString(4, password);
        psInsert.setString(5, visa_num.getText());
        psInsert.setString(6, dob);
        psInsert.setString(7, gender);

        FileInputStream fileInputStream = new FileInputStream(fileMain);
        System.out.println("userDetails fileMain = " + fileMain);
        psInsert.setBinaryStream(8, fileInputStream, fileInputStream.available());  //profile pic

        psInsert.setString(9, dateFormat.format(date));
        psInsert.setInt(10, Integer.parseInt(freeTransfer));
        psInsert.execute();
    }

    public static void getEmail(MFXTextField email, MFXTextField getAccId) throws SQLException {
        Connection connection;
        PreparedStatement psInsert;
        ResultSet resultSet;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                LoginDbConnection.dbPassword);

        psInsert = connection.prepareStatement("SELECT email_address FROM user_details WHERE account_id = ?");
        psInsert.setInt(1, Integer.parseInt(getAccId.getText()));
        resultSet = psInsert.executeQuery();

        if (!resultSet.isBeforeFirst()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("PLEASE CONTACT ACCOUNT ID IS NOT REGISTERED");
            alert.show();
        }else{
            while(resultSet.next()){
                String email_address = resultSet.getString("email_address");
                email.setText(email_address);
                System.out.println("result set" +  email_address);
            }
        }

    }

    public static void getName(MFXTextField name, MFXTextField getAccId) throws SQLException {
        Connection connection;
        PreparedStatement psInsert;
        ResultSet resultSet;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                LoginDbConnection.dbPassword);

        psInsert = connection.prepareStatement("SELECT full_name FROM user_details WHERE account_id = ?");
        psInsert.setInt(1, Integer.parseInt(getAccId.getText()));
        resultSet = psInsert.executeQuery();

        if (!resultSet.isBeforeFirst()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("PLEASE CONTACT ACCOUNT ID IS NOT REGISTERED");
            alert.show();
        }else{
            while(resultSet.next()){
                String fullName = resultSet.getString("full_name");
                name.setText(fullName);
                System.out.println("result set" +  fullName);
            }
        }



    }

    public static void getVisaNum(MFXTextField visaNum, MFXTextField getAccId) throws SQLException {
        Connection connection;
        PreparedStatement psInsert;
        ResultSet resultSet;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                LoginDbConnection.dbPassword);

        psInsert = connection.prepareStatement("SELECT visa_account_num FROM user_details WHERE account_id = ?");
        psInsert.setInt(1, Integer.parseInt(getAccId.getText()));
        resultSet = psInsert.executeQuery();

        if (!resultSet.isBeforeFirst()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("PLEASE CONTACT ACCOUNT ID IS NOT REGISTERED");
            alert.show();
        }else{
            while(resultSet.next()){
                String visaAccountNum = resultSet.getString("visa_account_num");
                visaNum.setText(visaAccountNum);
                System.out.println("result set" +  visaAccountNum);
            }
        }

    }

    public static void checkEmailExist(MFXTextField email) throws SQLException {
        Connection connection;
        PreparedStatement psCheckUserExist;
        ResultSet resultSet;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                LoginDbConnection.dbPassword);
        psCheckUserExist = connection.prepareStatement("SELECT email_address FROM banking_system_db.user_details" +
                " WHERE account_id = " + Integer.parseInt(account_id));
        resultSet = psCheckUserExist.executeQuery();

        if (!resultSet.isBeforeFirst()){
            UserCredentialsController.exist = false;

        }else {
            UserCredentialsController.exist = true;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("EMAIL ADDRESS NOT IN DATABASE");
            alert.show();
        }
    }

    public static void getUserCredentialsToHome() throws SQLException {
        Connection connection;
        PreparedStatement psCheckUserExist;
        ResultSet resultSet;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                LoginDbConnection.dbPassword);
        psCheckUserExist = connection.prepareStatement("SELECT full_name, email_address, visa_account_num, " +
                "user_profile_pic, free_transfer FROM banking_system_db.user_details WHERE account_id = " + Integer.parseInt(account_id));
        resultSet = psCheckUserExist.executeQuery();

        while (resultSet.next()){
            Blob blob = resultSet.getBlob("user_profile_pic");
            UserCredentialsController.iStream = blob.getBinaryStream();

        }

    }

}
