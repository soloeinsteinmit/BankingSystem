module com.example.bankingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires javafx.graphics;
    requires MaterialFX;
    requires TrayTester;
    requires GNAvatarView;

    requires org.controlsfx.controls;
    requires java.sql;
    requires com.jfoenix;

    opens com.example.bankingsystem to javafx.fxml;
    exports com.example.bankingsystem;
    opens com.example.bankingsystem.OtherClasses to javafx.fxml;
    exports com.example.bankingsystem.OtherClasses;
    opens com.example.bankingsystem.DatabaseConnectionUtils to javafx.fxml;
    exports com.example.bankingsystem.DatabaseConnectionUtils;

}