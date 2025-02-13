package com.example.recetas.utils;

import com.example.recetas.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class PantallaUtils {

    //Metodo que muestra las pantallas
    public FXMLLoader showEstaPantalla(Stage stage, String vista, String titulo, int ancho, int alto) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(vista));
        Scene scene = new Scene(fxmlLoader.load(), ancho, alto);

        // Aplicamos el CSS
        URL cssUrl = Main.class.getResource("src/resources/css/styles.css");
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        }

        stage.setTitle(titulo);
        stage.setScene(scene);

        // CENTRAR LA VENTANA
        stage.centerOnScreen(); // <- Asegura que la ventana se centre en la pantalla

        stage.show();
        return fxmlLoader;
    }


    //Metodo que cierra las pantallas
    public Stage cerrarEstaPantalla(Button botonDelAction) {
        //OBTENEMOS EL STAGE DE LA PANTALLA ACTUAL, A PARTIR DEL BOTÓN QUE SE ACCIONA
        Stage stageAhora = (Stage) botonDelAction.getScene().getWindow();

        //SE CIERRA EL STAGE
        stageAhora.close();

        //DEVUELVE EL STAGE PARA PODER SER REUTILIZADO EN OTRA PANTALLA POR EJEMPLO.
        return stageAhora;
    }
}