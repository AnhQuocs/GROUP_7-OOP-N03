module com.example.projectfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.projectfx to javafx.fxml;
    exports com.example.projectfx.model;
    exports com.example.projectfx.controller;
    exports com.example.projectfx.database;
    opens com.example.projectfx.model to javafx.fxml;
    opens com.example.projectfx.controller to javafx.fxml;
}