package com.example.recetas.controller;

import com.example.recetas.constants.Constantes;
import com.example.recetas.modelo.Receta;
import com.example.recetas.utils.PantallaUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddDataController {

    @FXML
    private TextField fieldNombre;

    @FXML
    private TextField fieldIngredientes;

    @FXML
    private TextField fieldPasos;

    @FXML
    private TextField fieldTiempo;

    @FXML
    private TextField fieldDificultad;

    @FXML
    private Button agregar;

    private static Receta nuevaReceta;

    public static Receta getNuevaReceta() {
        return nuevaReceta;
    }

    public AddDataController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_SEGUNDA_PANTALLA.getDescripcion(),Constantes.TITULO_SEGUNDA_PANTALLA.getDescripcion(),550,400);
        //OBTENER EL CONTROLADOR DE ESTA VENTANA, PARA PODER REFRESCAR DATOS DE COMPONENTES
        AddDataController controller = fxmlLoader.getController();

        return controller;
    }


    public void onAddButtonClick(ActionEvent actionEvent) throws IOException {

        // Comprobaciones para verificar que todos los campos están completos
        if (fieldNombre.getText().isEmpty() ||
                fieldIngredientes.getText().isEmpty() ||
                fieldPasos.getText().isEmpty() ||
                fieldTiempo.getText().isEmpty() ||
                fieldDificultad.getText().isEmpty()) {

            // Mostrar alerta si hay campos vacíos
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos vacíos");
            alert.setHeaderText("Por favor, complete todos los campos");
            alert.setContentText("Todos los campos deben tener información para añadir la receta.");
            alert.showAndWait();
        } else {
            // Crear un nuevo objeto Receta con los datos ingresados
            nuevaReceta = new Receta(
                    fieldNombre.getText(),
                    fieldIngredientes.getText(),
                    fieldPasos.getText(),
                    fieldTiempo.getText(),
                    fieldDificultad.getText()
            );

            Controlador.recetaList.add(nuevaReceta);

            Stage stage = new PantallaUtils().cerrarEstaPantalla(agregar);

            //LLAMAMOS A LA PANTALLA INICIAL A LA QUE QUEREMOS VOLVER
            new Controlador().showEstaPantalla(stage);
        }
    }
}