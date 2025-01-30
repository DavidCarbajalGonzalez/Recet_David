package com.example.recetas.controller;

import com.example.recetas.constants.Constantes;
import com.example.recetas.modelo.Receta;
import com.example.recetas.utils.AlertUtils;
import com.example.recetas.utils.PantallaUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Controlador {

    // Tabla y columnas
    @FXML
    private TableView<Receta> tableView;
    @FXML
    private TableColumn<Receta, String> nombre;
    @FXML
    private TableColumn<Receta, String> ingredientes;
    @FXML
    private TableColumn<Receta, String> pasos;
    @FXML
    private TableColumn<Receta, String> tiempo;
    @FXML
    private TableColumn<Receta, String> dificultad;

    // Campos de entrada
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

    // Lista de datos para la tabla
    static ObservableList<Receta> recetaList = FXCollections.observableArrayList();


    @FXML
    private Button agregarReceta;

    @FXML
    public void initialize() {

        // Configurar las columnas
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ingredientes.setCellValueFactory(new PropertyValueFactory<>("ingredientes"));
        pasos.setCellValueFactory(new PropertyValueFactory<>("pasos"));
        tiempo.setCellValueFactory(new PropertyValueFactory<>("tiempo"));
        dificultad.setCellValueFactory(new PropertyValueFactory<>("dificultad"));

        tableView.setItems(recetaList);
        tableView.refresh();

    }


    public Controlador showEstaPantalla(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_INICIAL.getDescripcion(), Constantes.TITULO_PAGINA_INICIAL.getDescripcion(), 550, 400);
        //OBTENER EL CONTROLADOR DE ESTA VENTANA, PARA PODER REFRESCAR DATOS DE COMPONENTES
        Controlador controller = fxmlLoader.getController();

        return controller;
    }
    public void setDatosReceta(Receta nuevaReceta) {
        recetaList.add(nuevaReceta);
    }


    @FXML
    void onDeleteButtonClick(ActionEvent event) {
        // Obtener la receta seleccionada de la tabla
        Receta selectedReceta = tableView.getSelectionModel().getSelectedItem();

        // Verificar si hay una receta seleccionada
        if (selectedReceta != null) {
            // Crear y mostrar la alerta de confirmación
            Alert alert = AlertUtils.showAlertaType(
                    "Confirmación de eliminación",
                    "¿Estás seguro de eliminar esta receta?\nUna vez eliminada, no se podrá recuperar.",
                    Alert.AlertType.CONFIRMATION
            );

            Optional<ButtonType> result = alert.showAndWait(); // Esperar respuesta del usuario

            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Eliminar la receta de la lista
                recetaList.remove(selectedReceta);
            }
        } else {
            // Mostrar alerta si no se ha seleccionado ninguna receta
            AlertUtils.showAlertaWarning(
                    "No se ha seleccionado receta",
                    "Por favor, selecciona una receta para eliminar."
            );
        }
    }



    public void onAddButtonClick(ActionEvent actionEvent) {
        try {
            //Stage stage = (new PantallaUtils()).cerrarEstaPantalla(this.agregarReceta);
            AddDataController controller = (new AddDataController()).showEstaPantalla(new Stage());
            controller.setControlador(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}