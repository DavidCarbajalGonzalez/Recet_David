package com.example.recetas.modelo;

import com.example.recetas.conexion.DatabaseConnection;
import com.example.recetas.utils.AlertUtils;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Receta {
    private String nombre;
    private String ingredientes;
    private String pasos;
    private String tiempo;
    private String dificultad;

    // Constructor
    public Receta(String nombre, String ingredientes, String pasos, String tiempo, String dificultad) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
        this.tiempo = tiempo;
        this.dificultad = dificultad;
    }

    // Métodos getter para las propiedades
    public String getNombre() {
        return nombre;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public String getPasos() {
        return pasos;
    }

    public String getTiempo() {
        return tiempo;
    }

    public String getDificultad() {
        return dificultad;
    }

    // Método para obtener las recetas de la base de datos
    public static List<Receta> obtenerRecetas() throws SQLException {
        List<Receta> recetas = new ArrayList<>();
        Connection conn = DatabaseConnection.connect();
        String query = "SELECT nombre, ingredientes, pasos, tiempo, dificultad FROM receta";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            recetas.add(new Receta(
                    rs.getString("nombre"),
                    rs.getString("ingredientes"),
                    rs.getString("pasos"),
                    rs.getString("tiempo"),
                    rs.getString("dificultad")
            ));
        }
        conn.close();
        return recetas;
    }

    // Método para guardar una receta en la base de datos
    public static void guardar(Receta receta) {
        String sql = "INSERT INTO receta (nombre, ingredientes, pasos, tiempo, dificultad) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect()) {
            conn.setAutoCommit(false);  // Desactiva el autocommit para manejar la transacción manualmente

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, receta.getNombre());
                pstmt.setString(2, receta.getIngredientes());
                pstmt.setString(3, receta.getPasos());
                pstmt.setString(4, receta.getTiempo());
                pstmt.setString(5, receta.getDificultad());
                pstmt.executeUpdate();

                conn.commit();  // Commitea la transacción
            } catch (SQLException e) {
                conn.rollback();  // Si hay un error, deshace los cambios
                System.out.println("Error al guardar receta: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión o transacción: " + e.getMessage());
        }
    }


    // Método para eliminar una receta de la base de datos
    public void eliminar() {
        String query = "DELETE FROM receta WHERE nombre = ? AND ingredientes = ? AND pasos = ? AND tiempo = ? AND dificultad = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, this.nombre);
            pstmt.setString(2, this.ingredientes);
            pstmt.setString(3, this.pasos);
            pstmt.setString(4, this.tiempo);
            pstmt.setString(5, this.dificultad);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                AlertUtils.showAlertaType("Éxito", "Receta eliminada correctamente.", Alert.AlertType.INFORMATION);
            } else {
                AlertUtils.showAlertaWarning("Receta no encontrada", "La receta no existe en la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            AlertUtils.showAlertaError("Error al eliminar", "Hubo un problema al eliminar la receta.");
        }
    }

    // Método para validar que los datos no estén vacíos
    public boolean validarDatos() {
        return !nombre.trim().isEmpty() &&
                !ingredientes.trim().isEmpty() &&
                !pasos.trim().isEmpty() &&
                !tiempo.trim().isEmpty() &&
                !dificultad.trim().isEmpty();
    }
}
