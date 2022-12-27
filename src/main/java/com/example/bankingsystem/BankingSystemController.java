package com.example.bankingsystem;

import com.example.bankingsystem.DatabaseConnectionUtils.TestImageDBConnection;
import io.github.gleidson28.GNAvatarView;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Solomon Einstein MIT Eshun
 * */


public class BankingSystemController implements Initializable {

    @FXML
    private ImageView addImage;
    @FXML
    private ImageView profile;
    @FXML
    private ImageView otherImgView;

    @FXML
    private MFXButton addImaggeBtn;

    @FXML
    private MFXTextField enterNameTextField;

    @FXML
    private MFXTextField nameTextfield;

    @FXML
    private GNAvatarView receiveImage;

    @FXML
    private GNAvatarView retrieveImage;

    @FXML
    private MFXButton retrieveImgBtn;

    @FXML
    private Label retrievedName;

    private static FileInputStream fis;
    private static File f;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            TestImageDBConnection.getImageIntoDbFileChooser(addImage, nameTextfield.getText(), receiveImage, otherImgView);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }


        retrieveImgBtn.setOnAction(e -> {
            try {
                TestImageDBConnection.retrieveImageFromDb(enterNameTextField.getText(), retrievedName, retrieveImage);
                profile.setVisible(false);
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });


    }


    @FXML
    private void handleOnDragOver(DragEvent event){
        if(event.getDragboard().hasFiles()){
            addImage.setVisible(false);
            event.acceptTransferModes(TransferMode.ANY);
        }
    }


    @FXML
    private void handleOnDrop(DragEvent event) throws FileNotFoundException {
        List<File> files = event.getDragboard().getFiles();


        File file = new File(String.valueOf(files.get(0)));
        System.out.println("file = " + file);
        FileInputStream fileInputStream = new FileInputStream(file);
        f = file;
        System.out.println("f = " + f);
        fis = fileInputStream;
        System.out.println(fileInputStream);

        Image image = new Image(fileInputStream);
        receiveImage.setImage(image);
    }

    @FXML
    private void handleOnDragExit(DragEvent event){
        if(event.getDragboard().hasFiles()){
            addImage.setVisible(true);
        }
    }
}