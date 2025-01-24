package com.example.recetas.controller;


import com.example.recetas.constants.Constantes;
import com.example.recetas.modelo.Usuario;
import com.example.recetas.utils.PantallaUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField fieldNombre;

    @FXML
    private PasswordField fieldContrasena;

    @FXML
    void onRegisterButtonClick(ActionEvent event) {
        String nombre = fieldNombre.getText();
        String contrasena = fieldContrasena.getText();

        if (nombre.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, complete todos los campos.");
            return;
        }

        // Registrar el usuario en memoria
        LoginController.usuarioRegistrado = new Usuario(nombre, contrasena);

        mostrarAlerta("Registro exitoso", "Usuario registrado correctamente. Ahora puede iniciar sesión.");

        // Volver a la pantalla de inicio de sesión
        try {
            Stage stage = (Stage) fieldNombre.getScene().getWindow();
            new LoginController().showEstaPantalla(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onBackButtonClick(ActionEvent event) {
        try {
            Stage stage = (Stage) fieldNombre.getScene().getWindow();
            new LoginController().showEstaPantalla(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public RegisterController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_REGISTER.getDescripcion(), Constantes.TITULO_PAGINA_REGISTER.getDescripcion(), 550, 400);
        //OBTENER EL CONTROLADOR DE ESTA VENTANA, PARA PODER REFRESCAR DATOS DE COMPONENTES
        RegisterController controller = fxmlLoader.getController();

        return controller;
    }
}

