package com.example.bankingsystem;

import com.example.bankingsystem.DatabaseConnectionUtils.UserCredentialsDbConnection;
import com.example.bankingsystem.OtherClasses.WindowManagement;
import io.github.gleidson28.GNAvatarView;
import io.github.palexdev.materialfx.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserCredentialsController implements Initializable {

    @FXML
    private MFXButton addContactBtn;

    @FXML
    private MFXButton close_white_signUp;

    @FXML
    private MFXTextField contact_acc_id;

    @FXML
    private MFXTextField contact_email;

    @FXML
    private MFXTextField contact_fullName;

    @FXML
    private ToggleGroup contact_gender;

    @FXML
    private MFXRadioButton contact_radioBtn_female;

    @FXML
    private MFXRadioButton contact_radioBtn_male;

    @FXML
    private MFXTextField contact_visa_num;

    @FXML
    private MFXTextField current_amt;

    @FXML
    private MFXTextField deposit_amt;

    @FXML
    private MFXDatePicker dobField;

    @FXML
    private Pane dragAreaPane;

    @FXML
    private MFXCheckbox editEmailCheckBox;

    @FXML
    private MFXCheckbox editNameCheckBox;

    @FXML
    private MFXButton loginBtn;

    @FXML
    private MFXButton minimizeBtn;

    @FXML
    private MFXButton registerBtn;

    @FXML
    private MFXTextField user_acc_id;

    @FXML
    private ImageView user_addImage;

    @FXML
    private MFXTextField user_email_address;

    @FXML
    private MFXTextField user_full_name;

    @FXML
    private ToggleGroup user_gender;

    @FXML
    private Label user_name;

    @FXML
    private GNAvatarView user_profile;

    @FXML
    private MFXRadioButton user_radioBtn_female;

    @FXML
    private MFXRadioButton user_radioBtn_male;

    @FXML
    private MFXTextField user_visa_num;

    public static MFXButton close;
    public static MFXButton minimize;
    public static Pane movablePane;
    public static GNAvatarView user_prof;
    public static ImageView user_addImg;
    public static MFXButton addContact;
    public static MFXButton registerUserBtn;
    public static MFXTextField contactEmail;
    public static MFXTextField contactName;
    public static MFXTextField contactAccId;
    public static MFXTextField contactVisaNum;
    public static MFXTextField uEmail;
    public static MFXTextField uName;
    public static MFXTextField uAccId;
    public static MFXTextField uVisaNum;
    public static MFXRadioButton contactMale;
    public static MFXRadioButton contactFemale;
    public static MFXCheckbox editName;
    public static MFXCheckbox editEmail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registerUserBtn = registerBtn;
        contactEmail = contact_email;       contactName = contact_fullName;
        contactAccId = contact_acc_id;      contactVisaNum = contact_visa_num;

        //initializing radio buttons contact
        contactMale = contact_radioBtn_male;        contactFemale = contact_radioBtn_female;

        //initializing user textFields
        uEmail = user_email_address;        uName = user_full_name;         uAccId = user_acc_id;
        uVisaNum = user_visa_num;

        // initializing checkboxes
        editName = editNameCheckBox;
        editName.setOnAction(actionEvent -> uName.setAllowEdit(editName.isSelected()));
        editEmail = editEmailCheckBox;
        editEmail.setOnAction(actionEvent -> uEmail.setAllowEdit(editEmail.isSelected()));

        addContact = addContactBtn;         user_addImg = user_addImage;        user_prof = user_profile;

        try {
            UserCredentialsDbConnection.getImageIntoDbFileChooser(user_addImg, user_prof);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        addContact.setOnAction(actionEvent -> {
            try {
                if (contactEmail.getText().isBlank() || contactName.getText().isBlank() ||
                        contactAccId.getText().isBlank() || contactVisaNum.getText().isBlank() ||
                        SignInIBankAccountTextController.validateEmail(contactEmail.getText())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("PLEASE FILL IN ALL THE FORMS");
                    alert.show();
                }else {
                    String gender = null;
                    if (contactMale.isSelected()){
                        gender = contactMale.getText();
                    } else if (contactFemale.isSelected()) {
                        gender = contactFemale.getText();
                    }
                    UserCredentialsDbConnection.createContactsDetails(contactEmail, contactName, contactAccId,
                            contactVisaNum, gender);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            contactEmail.clear();            contactName.clear();
            contactAccId.clear();            contactVisaNum.clear();
        });

        //initializing window management buttons        // window management
        close = close_white_signUp;                     close.setOnAction(WindowManagement::closeWindow);
        minimize = minimizeBtn;                         minimize.setOnAction(WindowManagement::minimizeWindow);
        movablePane = dragAreaPane;                     WindowManagement.movablePane(movablePane);

        // register user
        registerUser();
    }

    @FXML
    private void handleOnDragOverUser(DragEvent event){
        if(event.getDragboard().hasFiles()){
            user_addImg.setVisible(false);
            event.acceptTransferModes(TransferMode.ANY);
        }
    }


    @FXML
    private void handleOnDropUser(DragEvent event) throws IOException, SQLException {
        UserCredentialsDbConnection.getImageIntoDbDragEffect(event, user_prof);
    }

    @FXML
    private void handleOnDragExitUser(DragEvent event){
        if(event.getDragboard().hasFiles()){
            user_addImg.setVisible(true);
        }
    }

    public void setTextField(MFXTextField email, MFXTextField name, MFXTextField accId){
        uEmail.setText(email.getText());
        uName.setText(name.getText());
        uAccId.setText(accId.getText());
    }

    private static void registerUser(){
        registerUserBtn.setOnAction(actionEvent -> {

            try {
                UserCredentialsDbConnection.addEmail(uEmail);
                UserCredentialsDbConnection.addAccId(uAccId);
                UserCredentialsDbConnection.addFullName(uName);
                UserCredentialsDbConnection.addVisaAccNum(uVisaNum);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });
    }


}
