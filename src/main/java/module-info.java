module com.example.fms {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fms to javafx.fxml;
    exports com.example.fms;
}