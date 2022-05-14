module com.example.fms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.fms to javafx.fxml;
    opens com.example.fms.controller to javafx.fxml;
    exports com.example.fms;
}