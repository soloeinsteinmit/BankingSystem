module com.example.bankingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires javafx.graphics;
    requires MaterialFX;
    requires TrayTester;

    requires org.controlsfx.controls;
    requires java.sql;
    requires com.jfoenix;

    opens com.example.bankingsystem to javafx.fxml;
    exports com.example.bankingsystem;
    exports com.example.bankingsystem.OtherClasses;
    opens com.example.bankingsystem.OtherClasses to javafx.fxml;
}