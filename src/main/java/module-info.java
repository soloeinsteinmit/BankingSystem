module com.example.bankingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
//    requires javafx.graphics;
    requires MaterialFX;
//    requires TrayTester;

    requires org.controlsfx.controls;
    requires java.sql;

    opens com.example.bankingsystem to javafx.fxml;
    exports com.example.bankingsystem;
    exports com.example.bankingsystem.Login_SignUp_Classes;
    opens com.example.bankingsystem.Login_SignUp_Classes to javafx.fxml;
}