module com.example.recetas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.junit.jupiter.api;
    requires org.junit.platform.commons;

    opens com.example.recetas.controller to javafx.fxml;
    opens com.example.recetas.modelo to javafx.base;
    opens com.example.recetas to javafx.base;


    exports com.example.recetas.controller;
    exports com.example.recetas;
}