package com.example.recetas.controller;

import com.example.recetas.constants.Constantes;
import com.example.recetas.modelo.Receta;
import com.example.recetas.utils.AlertUtils;
import com.example.recetas.utils.PantallaUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.recetas.controller.Controlador.recetaList;

public class AddDataController {

    private Controlador controlador;  // Variable para almacenar la referencia al controlador principal

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

    // Método para establecer el controlador principal
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public static Receta nuevaReceta;

    public AddDataController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_SEGUNDA_PANTALLA.getDescripcion(), Constantes.TITULO_SEGUNDA_PANTALLA.getDescripcion(), 550, 450);
        AddDataController controller = fxmlLoader.getController();
        return controller;
    }

    public void onAddButtonClick(ActionEvent actionEvent) {
        // Crear una nueva receta con los datos del formulario
        Receta nuevaReceta = new Receta(
                fieldNombre.getText(),
                fieldIngredientes.getText(),
                fieldPasos.getText(),
                fieldTiempo.getText(),
                fieldDificultad.getText()
        );

        // Validar que todos los campos estén completos
        if (!nuevaReceta.validarDatos()) {
            AlertUtils.showAlertaWarning("Campos incompletos", "Por favor, complete todos los campos.");
            return;
        }

        // Guardar la receta en la base de datos
        Receta.guardar(nuevaReceta);

        // Mostrar mensaje de éxito
        AlertUtils.showAlertaType("Receta guardada", "La receta ha sido guardada exitosamente.", Alert.AlertType.INFORMATION);

        // **Actualiza la lista observable en el controlador principal**
        if (controlador != null) {
            controlador.getRecetaList().add(nuevaReceta);
        }

        // Limpiar los campos del formulario
        fieldNombre.clear();
        fieldIngredientes.clear();
        fieldPasos.clear();
        fieldTiempo.clear();
        fieldDificultad.clear();

        // Cerrar la ventana actual
        Stage stage = (Stage) agregar.getScene().getWindow();
        stage.close();
    }
}