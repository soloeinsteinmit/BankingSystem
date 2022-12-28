package com.example.bankingsystem.DatabaseConnectionUtils;

import com.example.bankingsystem.BankingSystemApplication;
import io.github.gleidson28.GNAvatarView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class UserCredentialsDbConnection {

    public static void getImageIntoDbDragEffect(DragEvent event,
                        GNAvatarView avatarView) throws SQLException, IOException {
        Connection connection;
        PreparedStatement psInsert;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                "programming");

        psInsert = connection.prepareStatement("UPDATE user_details SET user_profile_pic = ? WHERE account_id = 29050");

        List<File> files = event.getDragboard().getFiles();

        File file = new File(String.valueOf(files.get(0)));
        System.out.println("dragEffect file =  " + file);
        FileInputStream fileInputStream = new FileInputStream(file);

        FileInputStream fileInputStream1 = new FileInputStream(file);
        Image image = new Image(fileInputStream1);
        avatarView.setImage(image);

        psInsert.setBinaryStream(1, fileInputStream, fileInputStream.available());
        psInsert.executeUpdate();

    }

    public static void getImageIntoDbFileChooser(ImageView imageView, GNAvatarView avatarView)
            throws SQLException, IOException {
        Connection connection;
        PreparedStatement psInsert;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                "programming");

        psInsert = connection.prepareStatement("UPDATE testing_image_table SET image = ? WHERE id = 29050");

        PreparedStatement finalPsInsert = psInsert;
        imageView.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(BankingSystemApplication.stg);
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
        });


    }

    public static void userAccountDetails(){

    }

    public static void createContactsDetails(MFXTextField email, MFXTextField name, MFXTextField contact_acc_id,
                 MFXTextField visa_acc_num, String gender) throws SQLException {
        Connection connection;
        PreparedStatement psInsert;
        PreparedStatement psCheckAccIdExist;
        ResultSet resultSet;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                "programming");
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
            psInsert = connection.prepareStatement("INSERT INTO contacts_table VALUES(?, ?, ?, ?, ?, 29050)");
            psInsert.setInt(1, Integer.parseInt(contact_acc_id.getText()));
            psInsert.setString(2, name.getText());
            psInsert.setString(3, email.getText());
            psInsert.setString(4, gender);
            psInsert.setString(5, visa_acc_num.getText());
            psInsert.executeUpdate();
        }
    }

    public static String account_id;

    public static void addEmail(MFXTextField email) throws SQLException {
        Connection connection;
        PreparedStatement psInsert;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                "programming");

        psInsert = connection.prepareStatement("UPDATE user_details SET email_address = ? WHERE account_id = " + account_id);
        psInsert.setString(3, email.getText());
        psInsert.executeUpdate();
        System.out.println("add email");
    }


    public static void addFullName(MFXTextField name) throws SQLException {
        Connection connection;
        PreparedStatement psInsert;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                "programming");

        psInsert = connection.prepareStatement("UPDATE user_details SET full_name = ? WHERE account_id = " + account_id);
        psInsert.setString(2, name.getText());
        psInsert.executeUpdate();
        System.out.println("add name");
    }

    public static void addAccId(MFXTextField acc_id) throws SQLException {
        Connection connection;
        PreparedStatement psInsert;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                "programming");

        psInsert = connection.prepareStatement("UPDATE user_details SET full_name = ? WHERE account_id = " + account_id);
        psInsert.setInt(1, Integer.parseInt(acc_id.getText()));
        psInsert.executeUpdate();
        System.out.println("add acc id");
    }

    public static void addVisaAccNum(MFXTextField visa_num) throws SQLException {
        Connection connection;
        PreparedStatement psInsert;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                "programming");

        psInsert = connection.prepareStatement("UPDATE user_details SET full_name = ? WHERE account_id = " + account_id);
        psInsert.setString(5, visa_num.getText());
        psInsert.executeUpdate();

        System.out.println("add visa");
    }

}
