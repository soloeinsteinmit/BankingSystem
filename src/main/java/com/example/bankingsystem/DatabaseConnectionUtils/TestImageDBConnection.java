package com.example.bankingsystem.DatabaseConnectionUtils;

import com.example.bankingsystem.BankingSystemApplication;
import io.github.gleidson28.GNAvatarView;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class TestImageDBConnection {

    public static void getImageIntoDb( String name, FileInputStream fileInputStream)
            throws SQLException, IOException {
        Connection connection;
        PreparedStatement psInsert;
        PreparedStatement psCheckUserExist;
        ResultSet resultSet;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                "programming");
        psCheckUserExist = connection.prepareStatement("""
                SELECT image, full_name\s
                FROM testing_image_table
                WHERE full_name = ?""");
        psCheckUserExist.setString(1, name);
        resultSet = psCheckUserExist.executeQuery();


        if (resultSet.isBeforeFirst()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("User already in database");
            alert.show();
        }else {
            psInsert = connection.prepareStatement("INSERT INTO testing_image_table(image, full_name)" +
                    " VALUES(?, ?)");

            psInsert.setBinaryStream(1, fileInputStream, fileInputStream.available());
            psInsert.setString(2, name);
            psInsert.executeUpdate();
        }


    }

    public static void getImageIntoDbFileChooser(ImageView imageView, String name, GNAvatarView avatarView, ImageView imageView1)
            throws SQLException, IOException {
        Connection connection;
        PreparedStatement psInsert;
        PreparedStatement psCheckUserExist;
        ResultSet resultSet;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                "programming");
        psCheckUserExist = connection.prepareStatement("""
                SELECT image, full_name\s
                FROM testing_image_table
                WHERE full_name = ?""");
        psCheckUserExist.setString(1, name);
        resultSet = psCheckUserExist.executeQuery();


        if (resultSet.isBeforeFirst()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("User already in database");
            alert.show();
        }else {
            psInsert = connection.prepareStatement("INSERT INTO testing_image_table(image, full_name)" +
                    " VALUES(?, ?)");

            PreparedStatement finalPsInsert = psInsert;
            imageView.setOnMouseClicked(event -> {
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(BankingSystemApplication.stg);

                try {
                    FileInputStream fileInputStream = new FileInputStream(file);

                    FileInputStream fileInputStream1 = new FileInputStream(file);
                    Image image = new Image(fileInputStream1);
//                    imageView1.setImage(image);
                    avatarView.setImage(image);


                    finalPsInsert.setBinaryStream(1, fileInputStream, fileInputStream.available());
                    finalPsInsert.setString(2, name);
                    finalPsInsert.execute();

////                    avatarView.setImage(image);
//


                } catch (IOException | SQLException e) {
                    throw new RuntimeException(e);
                }
            });

        }


    }

    public static void retrieveImageFromDb(String name, Label labelName, GNAvatarView avatarView) throws SQLException, IOException {
        Connection connection;
        PreparedStatement psCheckUserExist;
        ResultSet resultSet;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                "programming");
        psCheckUserExist = connection.prepareStatement("""
                SELECT image, full_name
                FROM testing_image_table
                WHERE full_name = ?""");
        psCheckUserExist.setString(1, name);
        resultSet = psCheckUserExist.executeQuery();

        if (!resultSet.isBeforeFirst()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("User not in database");
            alert.show();
        } else {
            while (resultSet.next()){
                String retrievedName = resultSet.getString("full_name");

                if (name.equals(retrievedName)){
                    labelName.setText(name);
                    Blob blob = resultSet.getBlob(1);
                    InputStream inputStream = blob.getBinaryStream();
                    Image image = new Image(inputStream);
                    avatarView.setImage(image);
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Incorrect name");
                    alert.show();
                }

            }
        }
    }

}
