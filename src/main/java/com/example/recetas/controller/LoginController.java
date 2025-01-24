package com.example.recetas.controller;

import com.example.recetas.constants.Constantes;
import com.example.recetas.modelo.Usuario;
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

public class LoginController {

    @FXML
    private TextField fieldNombre;

    @FXML
    private PasswordField fieldContrasena;

    @FXML
    private Button crearCuenta;

    // Usuario registrado en memoria
    static Usuario usuarioRegistrado;


    public LoginController showEstaPantalla(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new PantallaUtils().showEstaPantalla(stage, Constantes.PAGINA_LOGIN.getDescripcion(), Constantes.TITULO_PAGINA_LOGIN.getDescripcion(), 550, 400);
        //OBTENER EL CONTROLADOR DE ESTA VENTANA, PARA PODER REFRESCAR DATOS DE COMPONENTES
        LoginController controller = fxmlLoader.getController();

        return controller;
    }

    // Boton para acceder a la aplicacion
    @FXML
    void onLoginButtonClick(ActionEvent event) {
        String nombre = fieldNombre.getText();
        String contrasena = fieldContrasena.getText();

        if (usuarioRegistrado == null) {
            mostrarAlerta("Sin usuario registrado", "Por favor, registre un usuario primero.");
            return;
        }

        if (usuarioRegistrado.getNombre().equals(nombre) && usuarioRegistrado.getContrasena().equals(contrasena)) {
            // Acceder a la aplicación principal
            try {
                Stage stage = (Stage) fieldNombre.getScene().getWindow();
                new Controlador().showEstaPantalla(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Credenciales incorrectas", "El nombre de usuario o la contraseña no son válidos.");
        }
    }

    // Boton de registrarse
    @FXML
    void onRegisterButtonClick(ActionEvent event) {
        try {
            Stage stage = (new PantallaUtils()).cerrarEstaPantalla(this.crearCuenta);
            RegisterController controller = (new RegisterController()).showEstaPantalla(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


}

