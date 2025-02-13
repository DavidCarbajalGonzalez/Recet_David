package com.example.recetas.controller;

import com.example.recetas.conexion.DatabaseConnection;
import com.example.recetas.constants.Constantes;
import com.example.recetas.modelo.Receta;
import com.example.recetas.utils.AlertUtils;
import com.example.recetas.utils.PantallaUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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

    // Lista de datos para la tabla
    static ObservableList<Receta> recetaList = FXCollections.observableArrayList();

    @FXML
    private Button agregarReceta;
    @FXML
    private Button cerrarApp;

    public ObservableList<Receta> getRecetaList() {
        return recetaList;
    }

    @FXML
    public void initialize() {
        try {
            List<Receta> recetas = Receta.obtenerRecetas();  // Obtener desde la base de datos
            recetaList.setAll(recetas);  // Actualizar la lista

            // Configurar las columnas de la tabla usando los getters de la clase Receta
            nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            ingredientes.setCellValueFactory(new PropertyValueFactory<>("ingredientes"));
            pasos.setCellValueFactory(new PropertyValueFactory<>("pasos"));
            tiempo.setCellValueFactory(new PropertyValueFactory<>("tiempo"));
            dificultad.setCellValueFactory(new PropertyValueFactory<>("dificultad"));

            // Asignar la lista de recetas a la tabla
            tableView.setItems(recetaList);
            tableView.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtils.showAlertaError("Error al cargar las recetas.", "Hubo un problema al intentar cargar las recetas desde la base de datos.");
        }
    }

    public Controlador showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_INICIAL.getDescripcion(), Constantes.TITULO_PAGINA_INICIAL.getDescripcion(), 560, 600);
        Controlador controller = fxmlLoader.getController();
        return controller;
    }

    public void setDatosReceta(Receta nuevaReceta) {
        recetaList.add(nuevaReceta);  // Agrega la receta directamente a la lista observable
    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) {
        Receta selectedReceta = tableView.getSelectionModel().getSelectedItem();
        if (selectedReceta != null) {
            Alert alert = AlertUtils.showAlertaType(
                    "Confirmación de eliminación",
                    "¿Estás seguro de eliminar esta receta?\nUna vez eliminada, no se podrá recuperar.",
                    Alert.AlertType.CONFIRMATION
            );

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                selectedReceta.eliminar();
                recetaList.remove(selectedReceta);
                AlertUtils.showAlertaType("Receta eliminada", "La receta ha sido eliminada correctamente.", Alert.AlertType.INFORMATION);
            }
        } else {
            AlertUtils.showAlertaWarning(
                    "No se ha seleccionado receta",
                    "Por favor, selecciona una receta para eliminar."
            );
        }
    }

    public void onAddButtonClick(ActionEvent actionEvent) {
        try {
            AddDataController controller = (new AddDataController()).showEstaPantalla(new Stage());
            controller.setControlador(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onCloseButtonClick(ActionEvent event) {
        try {
            Connection conn = DatabaseConnection.connect();
            DatabaseConnection.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtils.showAlertaError("Error al cerrar la base de datos", "Hubo un problema al cerrar la conexión.");
        }
        Platform.exit();
    }
}