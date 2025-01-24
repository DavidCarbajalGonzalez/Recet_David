package com.example.recetas.controller;

import com.example.recetas.constants.Constantes;
import com.example.recetas.modelo.Receta;
import com.example.recetas.utils.PantallaUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_INICIAL.getDescripcion(),Constantes.TITULO_PAGINA_INICIAL.getDescripcion(),550,400);
        //OBTENER EL CONTROLADOR DE ESTA VENTANA, PARA PODER REFRESCAR DATOS DE COMPONENTES
        Controlador controller = fxmlLoader.getController();

        return controller;
    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) {
        // Obtener la receta seleccionada de la tabla
        Receta selectedReceta = tableView.getSelectionModel().getSelectedItem();

        // Verificar si hay una receta seleccionada
        if (selectedReceta != null) {
            // Confirmar con el usuario antes de eliminar
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación de eliminación");
            alert.setHeaderText("¿Estás seguro de eliminar esta receta?");
            alert.setContentText("Una vez eliminada, no se podrá recuperar.");

            // Mostrar alerta y esperar respuesta
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Eliminar la receta de la lista
                recetaList.remove(selectedReceta);
            }
        } else {
            // Mostrar alerta si no se ha seleccionado ninguna receta
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No se ha seleccionado receta");
            alert.setHeaderText("Por favor, selecciona una receta para eliminar.");
            alert.showAndWait();
        }
    }

    public void onAddButtonClick(ActionEvent actionEvent) {
        try {
            Stage stage = (new PantallaUtils()).cerrarEstaPantalla(this.agregarReceta);
            AddDataController controller = (new AddDataController()).showEstaPantalla(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}