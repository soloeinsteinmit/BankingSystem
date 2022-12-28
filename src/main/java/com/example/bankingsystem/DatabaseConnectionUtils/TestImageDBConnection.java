package com.example.bankingsystem.DatabaseConnectionUtils;

import com.example.bankingsystem.BankingSystemApplication;
import io.github.gleidson28.GNAvatarView;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;

public class TestImageDBConnection {

    public static void getImageIntoDbDragEffect(DragEvent event,
                GNAvatarView avatarView) throws SQLException, IOException {
        Connection connection;
        PreparedStatement psInsert;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                "programming");

        psInsert = connection.prepareStatement("UPDATE testing_image_table SET image = ? WHERE id = 333");

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

    public static void getImageIntoDbFileChooser(ImageView imageView, GNAvatarView avatarView, ImageView imageView1)
            throws SQLException, IOException {
        Connection connection;
        PreparedStatement psInsert;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                "programming");

            psInsert = connection.prepareStatement("UPDATE testing_image_table SET image = ? WHERE id = 333");

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

    public static void insertName(String name) throws SQLException {
        Connection connection;
        PreparedStatement psInsert;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
                "programming");

        psInsert = connection.prepareStatement("UPDATE testing_image_table SET full_name = ? WHERE id = 333");
        if (name.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("please enter your name");
            alert.show();
        }else {
            psInsert.setString(1, name);
            psInsert.execute();
            System.out.println("name = " + name);
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
