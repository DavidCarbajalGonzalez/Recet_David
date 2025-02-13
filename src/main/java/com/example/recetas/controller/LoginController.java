package com.example.recetas.controller;

import com.example.recetas.constants.Constantes;
import com.example.recetas.modelo.Usuario;
import com.example.recetas.utils.AlertUtils;
import com.example.recetas.utils.PantallaUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField fieldNombre;

    @FXML
    private PasswordField fieldContrasena;

    @FXML
    private Button crearCuenta;

    public LoginController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_LOGIN.getDescripcion(), Constantes.TITULO_PAGINA_LOGIN.getDescripcion(), 550, 400);
        LoginController controller = fxmlLoader.getController();
        return controller;
    }

    @FXML
    void onLoginButtonClick(ActionEvent event) {
        String nombre = fieldNombre.getText();
        String contrasena = fieldContrasena.getText();

        try {
            if (!Usuario.existeUsuario(nombre)) {
                AlertUtils.showAlertaWarning("Usuario no registrado", "Este usuario no está registrado. Por favor, regístrese primero.");
                return;
            }

            // Verificar la contraseña ingresada con la almacenada
            boolean contrasenaCorrecta = Usuario.verificarContrasena(nombre, contrasena);
            if (!contrasenaCorrecta) {
                AlertUtils.showAlertaWarning("Credenciales incorrectas", "El nombre de usuario o la contraseña no son válidos.");
                return;
            }

            // Si la contraseña es correcta
            AlertUtils.showAlertaType("Inicio de sesión exitoso", "Bienvenido de nuevo, " + nombre + "!", Alert.AlertType.INFORMATION);
            Stage stage = (Stage) fieldNombre.getScene().getWindow();
            new Controlador().showEstaPantalla(stage);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            AlertUtils.showAlertaError("Error de conexión", "Hubo un problema al verificar las credenciales.");
        }
    }

    @FXML
    void onRegisterButtonClick(ActionEvent event) {
        try {
            Stage stage = (new PantallaUtils()).cerrarEstaPantalla(this.crearCuenta);
            RegisterController controller = (new RegisterController()).showEstaPantalla(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}