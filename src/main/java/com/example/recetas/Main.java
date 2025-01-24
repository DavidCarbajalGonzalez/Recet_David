package com.example.recetas;

import com.example.recetas.controller.Controlador;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new Controlador().showEstaPantalla(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}