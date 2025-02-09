package com.example.recetas.controller;

import com.example.recetas.constants.Constantes;
import com.example.recetas.modelo.Usuario;
import com.example.recetas.utils.AlertUtils;
import com.example.recetas.utils.PantallaUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

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
            AlertUtils.showAlertaWarning("Campos vacíos", "Por favor, complete todos los campos.");
            return;
        }

        Usuario nuevoUsuario = new Usuario(nombre, contrasena);

        try {
            nuevoUsuario.guardar();
            Alert alert = AlertUtils.showAlertaType("Registro exitoso", "Usuario registrado correctamente. Ahora puede iniciar sesión.", Alert.AlertType.INFORMATION);
            alert.showAndWait();
            Stage stage = (Stage) fieldNombre.getScene().getWindow();
            new LoginController().showEstaPantalla(stage);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            AlertUtils.showAlertaError("Error al registrar el usuario: " + e.getMessage(), "Hubo un problema al verificar las credenciales.");
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

    public RegisterController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_REGISTER.getDescripcion(), Constantes.TITULO_PAGINA_REGISTER.getDescripcion(), 550, 400);
        RegisterController controller = fxmlLoader.getController();
        return controller;
    }
}
