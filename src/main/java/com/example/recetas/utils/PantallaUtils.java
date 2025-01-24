package com.example.recetas.utils;

import com.example.recetas.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PantallaUtils {

    public FXMLLoader showEstaPantalla(Stage stage, String vista, String titulo, int ancho, int alto) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(vista));
        Scene scene = new Scene(fxmlLoader.load(), ancho, alto);
        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.show();
        return fxmlLoader;
    }

    public Stage cerrarEstaPantalla(Button botonDelAction) {
        //OBTENEMOS EL STAGE DE LA PANTALLA ACTUAL, A PARTIR DEL BOTÓN QUE SE ACCIONA
        Stage stageAhora = (Stage) botonDelAction.getScene().getWindow();

        //SE CIERRA EL STAGE
        stageAhora.close();

        //DEVUELVE EL STAGE PARA PODER SER REUTILIZADO EN OTRA PANTALLA POR EJEMPLO.
        return stageAhora;
    }
}